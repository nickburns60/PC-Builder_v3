package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import com.pcBuilder.models.GraphicsCard;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.MoboWithSocketFormRamBrand;
import com.pcBuilder.models.Motherboard;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MotherboardDao {
    private final JdbcTemplate jdbcTemplate;

    public MotherboardDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<MoboWithSocketFormRamBrand> getCompatibleMobosBySocketId(int socketId){
        List<MoboWithSocketFormRamBrand> mobos = new ArrayList<>();
        String sql = "select motherboard_id, brand_name, product_name, socket_type, form_factor_name, ram_type_name, price, motherboard.socket_id as socket_num\n" +
                "from motherboard\n" +
                "join brand on brand.brand_id = motherboard.brand_id\n" +
                "join socket on socket.socket_id = motherboard.socket_id\n" +
                "join form_factor on form_factor.form_factor_id = motherboard.form_factor_id\n" +
                "join ram_type on ram_type.ram_type_id = motherboard.ram_type_id\n" +
                "where socket.socket_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, socketId);
        while (results.next()){
            MoboWithSocketFormRamBrand mobo = new MoboWithSocketFormRamBrand();
            mobo.setMotherboardId(results.getInt("motherboard_id"));
            mobo.setBrandName(results.getString("brand_name"));
            mobo.setProductName(results.getString("product_name"));
            mobo.setSocketType(results.getString("socket_type"));
            mobo.setFormFactorName(results.getString("form_factor_name"));
            mobo.setRamTypeName(results.getString("ram_type_name"));
            mobo.setPrice(results.getBigDecimal("price"));
            mobo.setSocketId(results.getInt("socket_num"));
            mobos.add(mobo);
        }
        return mobos;
    }

    public Motherboard getMotherboardById(int moboId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from motherboard where motherboard_id = ?;", moboId);
        if (results.next()){
            return mapRowToMotherboard(results);
        }
        return null;
    }

    public List<Motherboard> getAllMobos(){
        List<Motherboard> mobos = new ArrayList<>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from motherboard");
        while (rowSet.next()){
            mobos.add(mapRowToMotherboard(rowSet));
        }
        return mobos;
    }

    public Motherboard createMobo(Motherboard newMobo){
        try{
            int moboId = jdbcTemplate.queryForObject("insert into motherboard (socket_id, form_factor_id, ram_type_id, brand_id, product_name, model, price) " +
                    "values (?, ?, ?, ?, ?, ?, ?) returning motherboard_id;", int.class, newMobo.getSocketId(), newMobo.getFormFactorId(), newMobo.getRamTypeId(),
                    newMobo.getBrandId(), newMobo.getProductName(), newMobo.getModel(), newMobo.getPrice());
            return getMotherboardById(moboId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating motherboard. ", e);
        }
    }

    public Motherboard updateMobo(Motherboard moboToUpdate){
        Motherboard mobo = null;
        try{
            int numOfRows = jdbcTemplate.update("update motherboard set socket_id=?, form_factor_id=?, ram_type_id=?, brand_id=?, product_name=?, " +
                            "model=?, price=? where motherboard_id=?;", moboToUpdate.getSocketId(), moboToUpdate.getFormFactorId(), moboToUpdate.getRamTypeId(), moboToUpdate.getBrandId(),
                    moboToUpdate.getProductName(), moboToUpdate.getModel(), moboToUpdate.getPrice(), moboToUpdate.getMotherboardId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                mobo = getMotherboardById(moboToUpdate.getMotherboardId());
            }
            return mobo;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating Motherboard. ", e);
        }
    }

    public void deleteMobo(int id){
        try{
            jdbcTemplate.update("delete from motherboard where motherboard_id = ?;", id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting motherboard. ", e);
        }
    }

    public Motherboard mapRowToMotherboard(SqlRowSet rowSet){
        Motherboard motherboard = new Motherboard();
        motherboard.setMotherboardId(rowSet.getInt("motherboard_id"));
        motherboard.setBrandId(rowSet.getInt("brand_id"));
        motherboard.setProductName(rowSet.getString("product_name"));
        motherboard.setModel(rowSet.getString("model"));
        motherboard.setSocketId(rowSet.getInt("socket_id"));
        motherboard.setFormFactorId(rowSet.getInt("form_factor_id"));
        motherboard.setRamTypeId(rowSet.getInt("ram_type_id"));
        motherboard.setPrice(rowSet.getBigDecimal("price"));
        return motherboard;
    }
}
