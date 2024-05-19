package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.CpuCoolerWithBrand;
import com.pcBuilder.models.CpuCooler;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class CpuCoolerDao {
    private JdbcTemplate jdbcTemplate;

    public CpuCoolerDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
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

    public List<CpuCooler> getAllCoolers(){
        List<CpuCooler> coolers = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from cpu_cooler;");
        while (results.next()){
            coolers.add(mapRowToCase(results));
        }
        return coolers;
    }

    public CpuCooler getCoolerById(int coolerId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from cpu_cooler where cpu_cooler_id = ?;", coolerId);
        if (results.next()){
            return mapRowToCase(results);
        }
        return null;
    }

    public CpuCooler createCpuCooler(CpuCooler newCooler){
        try{
            int coolerId = jdbcTemplate.queryForObject("insert into cpu_cooler (brand_id, product_name, model, cooler_type, " +
                    "size_mm, color, rgb, price) values" +
                    "(?,?,?,?,?,?,?,?) returning cpu_cooler_id", int.class, newCooler.getBrandId(), newCooler.getProductName(), newCooler.getModel(),
                    newCooler.getCoolerType(), newCooler.getSizeMm(), newCooler.getColor(), newCooler.isRgb(), newCooler.getPrice());
            return getCoolerById(coolerId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating brand", e);
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
            throw new DaoException("Error updating brand. ", e);
        }
    }
    public int deleteCooler(int coolerId){
        int numRows = 0;
        try{
            numRows = jdbcTemplate.update("delete from cpu_cooler where cpu_cooler_id = ?;", coolerId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting brand. ", e);
        }
        return numRows;
    }

    public CpuCooler mapRowToCase(SqlRowSet rowSet){
        CpuCooler cooler = new CpuCooler();
        cooler.setCpuCoolerId(rowSet.getInt("cpu_cooler_id"));
        cooler.setBrandId(rowSet.getInt("brand_id"));
        cooler.setProductName(rowSet.getString("product_name"));
        cooler.setModel(rowSet.getString("model"));
        cooler.setCoolerType(rowSet.getString("cooler_type"));
        cooler.setColor(rowSet.getString("color"));
        cooler.setRgb(rowSet.getBoolean("rgb"));
        cooler.setSizeMm(rowSet.getInt("size_mm"));
        cooler.setPrice(rowSet.getBigDecimal("price"));
        return cooler;
    }
}