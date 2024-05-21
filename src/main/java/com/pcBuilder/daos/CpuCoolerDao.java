package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.CpuCoolerWithBrand;
import com.pcBuilder.models.CpuCooler;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CpuCoolerDao {
    private final JdbcTemplate jdbcTemplate;

    public CpuCoolerDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CpuCooler getCoolerById(int coolerId){
        try{
            return jdbcTemplate.queryForObject("select * from cpu_cooler where cpu_cooler_id = ?;", this::mapRowToCooler, coolerId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
//        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from cpu_cooler where cpu_cooler_id = ?;", coolerId);
//        if (results.next()){
//            return mapRowToCooler(results);
//        }
//        return null;
    }

    public List<CpuCooler> getAllCoolers(){
        return jdbcTemplate.query("select * from cpu_cooler;", this::mapRowToCooler);
//        List<CpuCooler> coolers = new ArrayList<>();
//        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from cpu_cooler;");
//        while (results.next()){
//            coolers.add(mapRowToCooler(results));
//        }
//        return coolers;
    }

    public List<CpuCoolerWithBrand> getCompatibleCoolers(int caseLength, int caseWidth){
        List<CpuCoolerWithBrand> coolers = new ArrayList<>();
        String sql = "select cpu_cooler_id, brand_name, product_name, cooler_type, color, rgb, size_mm, price\n" +
                "from cpu_cooler\n" +
                "join brand on brand.brand_id = cpu_cooler.brand_id\n" +
                "where size_mm < ? or size_mm < ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, caseLength, caseWidth);
        while (rowSet.next()){
            CpuCoolerWithBrand cooler = new CpuCoolerWithBrand();
            cooler.setCpuCoolerId(rowSet.getInt("cpu_cooler_id"));
            cooler.setBrandName(rowSet.getString("brand_name"));
            cooler.setProductName(rowSet.getString("product_name"));
            cooler.setCoolerType(rowSet.getString("cooler_type"));
            cooler.setColor(rowSet.getString("color"));
            cooler.setRgb(rowSet.getBoolean("rgb"));
            cooler.setSizeMm(rowSet.getInt("size_mm"));
            cooler.setPrice(rowSet.getBigDecimal("price"));
            coolers.add(cooler);
        }
        return coolers;
    }

    public CpuCooler createCpuCooler(CpuCooler newCooler){
        try{
            int coolerId = jdbcTemplate.queryForObject("insert into cpu_cooler (brand_id, product_name, model, cooler_type, " +
                    "size_mm, color, rgb, price) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?) returning cpu_cooler_id;", int.class, newCooler.getBrandId(), newCooler.getProductName(), newCooler.getModel(),
                    newCooler.getCoolerType(), newCooler.getSizeMm(), newCooler.getColor(), newCooler.isRgb(), newCooler.getPrice());
            return getCoolerById(coolerId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating cooler", e);
        }
    }
    public CpuCooler updateCooler(CpuCooler cpuCooler){
        CpuCooler cooler = null;
        try{
            int numOfRows = jdbcTemplate.update("update cpu_cooler set brand_id=?, product_name=?, model=?, cooler_type=?, size_mm=?, color=?, rgb=?, price=? where cpu_cooler_id=?;",
                    cpuCooler.getBrandId(), cpuCooler.getProductName(), cpuCooler.getModel(), cpuCooler.getCoolerType(),
                    cpuCooler.getSizeMm(), cpuCooler.getColor(), cpuCooler.isRgb(), cpuCooler.getPrice(), cpuCooler.getCpuCoolerId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                cooler = getCoolerById(cpuCooler.getCpuCoolerId());
            }
            return cooler;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating cooler. ", e);
        }
    }
    public int deleteCooler(int coolerId){
        int numRows = 0;
        try{
            numRows = jdbcTemplate.update("delete from cpu_cooler where cpu_cooler_id = ?;", coolerId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting cooler. ", e);
        }
        return numRows;
    }

    public CpuCooler mapRowToCooler(ResultSet row, int rowNumber) throws SQLException {
        CpuCooler cooler = new CpuCooler();
        cooler.setCpuCoolerId(row.getInt("cpu_cooler_id"));
        cooler.setBrandId(row.getInt("brand_id"));
        cooler.setProductName(row.getString("product_name"));
        cooler.setModel(row.getString("model"));
        cooler.setCoolerType(row.getString("cooler_type"));
        cooler.setSizeMm(row.getInt("size_mm"));
        cooler.setColor(row.getString("color"));
        cooler.setRgb(row.getBoolean("rgb"));
        cooler.setPrice(row.getBigDecimal("price"));
        return cooler;
    }
}