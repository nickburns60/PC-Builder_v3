package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.FanWithBrand;
import com.pcBuilder.models.Fans;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * Fan data access object
 */
@Component
public class FanDao {
    /**
     * JDBC template
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     * @param dataSource data source
     */
    public FanDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Get all fans with brand name
     * @return list of fans with brand names
     */
    public List<FanWithBrand> getFans(){
        //Not used in api, will be used in future
        List<FanWithBrand> fans = new ArrayList<>();
        String sql = "select fan_id, brand_name, product_name, num_of_fans, color, rgb, size_mm, price\n" +
                "from fans\n" +
                "join brand on brand.brand_id = fans.brand_id\n";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()){
            FanWithBrand fan = new FanWithBrand();
            fan.setFanId(rowSet.getInt("fan_id"));
            fan.setBrandName(rowSet.getString("brand_name"));
            fan.setProductName(rowSet.getString("product_name"));
            fan.setNumOfFans(rowSet.getInt("num_of_fans"));
            fan.setColor(rowSet.getString("color"));
            fan.setRgb(rowSet.getBoolean("rgb"));
            fan.setSizeInMm(rowSet.getInt("size_mm"));
            fan.setPrice(rowSet.getBigDecimal("price"));
            fans.add(fan);
        }
        return fans;
    }

    /**
     * Get all fans
     * @return list of fans
     */
    public List<Fans> getFansNoBrandName(){
        List<Fans> fans = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from fans");
        while(rowSet.next()){
            Fans fan = new Fans();
            fan.setFanId(rowSet.getInt("fan_id"));
            fan.setBrandId(rowSet.getInt("brand_id"));
            fan.setProductName(rowSet.getString("product_name"));
            fan.setModel(rowSet.getString("model"));
            fan.setSizeInMm(rowSet.getInt("size_mm"));
            fan.setNumOfFans(rowSet.getInt("num_of_fans"));
            fan.setColor(rowSet.getString("color"));
            fan.setRgb(rowSet.getBoolean("rgb"));
            fan.setPrice(rowSet.getBigDecimal("price"));
            fans.add(fan);
        }
        return fans;
    }

    /**
     * Get a fan using id
     * @param fanId fan id
     * @return fan requested
     */
    public Fans getFanById(int fanId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from fans where fan_id = ?;", fanId);
        if (results.next()){
            return mapRowToFan(results);
        }
        return null;
    }


    /**
     * Create a fan
     * @param newFan fan
     * @return created fan
     */
    public Fans createFan(Fans newFan){
        try{
            int fanId = jdbcTemplate.queryForObject("insert into fans (brand_id, product_name, model, size_mm, " +
                            "num_of_fans, color, rgb, price) values" +
                            "(?,?,?,?,?,?,?,?) returning fan_id;", int.class, newFan.getBrandId(), newFan.getProductName(), newFan.getModel(),
                    newFan.getSizeInMm(), newFan.getNumOfFans(), newFan.getColor(), newFan.isRgb(), newFan.getPrice());
            return getFanById(fanId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating brand", e);
        }
    }

    /**
     * Update a fan
     * @param fanToUpdate fan
     * @return updated fan
     */
    public Fans updateFan(Fans fanToUpdate){
        Fans fan = null;
        try{
            int numOfRows = jdbcTemplate.update("update fans set brand_id=?, product_name=?, model=?, size_mm=?, num_of_fans=?, color=?, rgb=?, price=? where fan_id=?;",
                    fanToUpdate.getBrandId(), fanToUpdate.getProductName(), fanToUpdate.getModel(), fanToUpdate.getSizeInMm(),
                    fanToUpdate.getNumOfFans(), fanToUpdate.getColor(), fanToUpdate.isRgb(), fanToUpdate.getPrice(), fanToUpdate.getFanId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                fan = getFanById(fanToUpdate.getFanId());
            }
            return fan;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating brand. ", e);
        }
    }

    /**
     * Delete a fan
     * @param fanId id to delete
     * @return deleted fan
     */
    public int deleteFan(int fanId){
        int numRows = 0;
        try{
            numRows = jdbcTemplate.update("delete from fans where fan_id = ?;", fanId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting brand. ", e);
        }
        return numRows;
    }

    /**
     * Fan mapper
     * @param rowSet rowset
     * @return mapped fan
     */
    public Fans mapRowToFan(SqlRowSet rowSet){
        Fans fan = new Fans();
        fan.setFanId(rowSet.getInt("fan_id"));
        fan.setBrandId(rowSet.getInt("brand_id"));
        fan.setProductName(rowSet.getString("product_name"));
        fan.setModel(rowSet.getString("model"));
        fan.setSizeInMm(rowSet.getInt("size_mm"));
        fan.setNumOfFans(rowSet.getInt("num_of_fans"));
        fan.setColor(rowSet.getString("color"));
        fan.setRgb(rowSet.getBoolean("rgb"));
        fan.setPrice(rowSet.getBigDecimal("price"));
        return fan;
    }
}
