package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import com.pcBuilder.models.Ram;
import com.pcBuilder.viewmodels.RamWithBrandRamType;
import com.pcBuilder.models.Ram;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class RamDao {
    private final JdbcTemplate jdbcTemplate;

    public RamDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RamWithBrandRamType> getCompatibleRamByRamTypeId(int ramTypeId){
        List<RamWithBrandRamType> compatibleRam = new ArrayList<>();
        String sql = "select ram_id, brand_name, product_name, ram_type_name, price, ram.ram_type_id as type_id\n" +
                "from ram\n" +
                "join brand on brand.brand_id = ram.brand_id\n" +
                "join ram_type on ram_type.ram_type_id = ram.ram_type_id\n" +
                "where ram.ram_type_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ramTypeId);
        while (results.next()){
            RamWithBrandRamType ramWithBrandRamType = new RamWithBrandRamType();
            ramWithBrandRamType.setRamId(results.getInt("ram_id"));
            ramWithBrandRamType.setBrandName(results.getString("brand_name"));
            ramWithBrandRamType.setProductName(results.getString("product_name"));
            ramWithBrandRamType.setRamTypeName(results.getString("ram_type_name"));
            ramWithBrandRamType.setPrice(results.getBigDecimal("price"));
            ramWithBrandRamType.setRamTypeId(results.getInt("type_id"));
            compatibleRam.add(ramWithBrandRamType);
        }
        return compatibleRam;
    }

    public Ram getRamById(int ramId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from ram where ram_Id = ?", ramId);
        if (results.next()){
            return mapRowToRam(results);
        }
        return null;
    }

    public List<Ram> getAllRam(){
        List<Ram> ram = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from ram");
        while (rowSet.next()){
            ram.add(mapRowToRam(rowSet));
        }
        return ram;
    }


    public Ram createRam(Ram newRam){
        try{
            int ramId = jdbcTemplate.queryForObject("insert into ram (ram_type_id, brand_id, product_name, model, capacity_gb, num_of_sticks, rgb, price) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?) returning ram_id;", int.class, newRam.getRamTypeId(), newRam.getBrandId(), newRam.getProductName(), newRam.getModel(),
                    newRam.getCapacityGb(), newRam.getNumOfSticks(), newRam.isRgb(), newRam.getPrice());
            return getRamById(ramId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating ram. ", e);
        }
    }

    public Ram updateRam(Ram ramToUpdate){
        Ram ram = null;
        try{
            int numOfRows = jdbcTemplate.update("update ram set ram_type_id=?, brand_id=?, product_name=?, model=?, capacity_gb=?, num_of_sticks=?, rgb=?, price=? where ram_id=?;", ramToUpdate.getRamTypeId(), ramToUpdate.getBrandId(),
                    ramToUpdate.getProductName(), ramToUpdate.getModel(), ramToUpdate.getCapacityGb(), ramToUpdate.getNumOfSticks(), ramToUpdate.isRgb(), ramToUpdate.getPrice(), ramToUpdate.getRamId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                ram = getRamById(ramToUpdate.getRamId());
            }
            return ram;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating ram. ", e);
        }
    }

    public void deleteRam(int id){
        try{
            jdbcTemplate.update("delete from ram where ram_id = ?;", id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting ram. ", e);
        }
    }

    public Ram mapRowToRam(SqlRowSet rowSet){
        Ram ram = new Ram();
        ram.setRamId(rowSet.getInt("ram_id"));
        ram.setBrandId(rowSet.getInt("brand_id"));
        ram.setProductName(rowSet.getString("product_name"));
        ram.setModel(rowSet.getString("model"));
        ram.setRamTypeId(rowSet.getInt("ram_type_id"));
        ram.setCapacityGb(rowSet.getInt("capacity_gb"));
        ram.setNumOfSticks(rowSet.getInt("num_of_sticks"));
        ram.setRgb(rowSet.getBoolean("rgb"));
        ram.setPrice(rowSet.getBigDecimal("price"));
        return ram;
    }
}
