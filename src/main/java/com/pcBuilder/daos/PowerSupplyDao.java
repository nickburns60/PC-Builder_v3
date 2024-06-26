package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.PowerSupplyWithBrandWattage;
import com.pcBuilder.models.PowerSupply;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * Power supply data access object
 */
@Component
public class PowerSupplyDao {
    /**
     * JDBC template
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     * @param dataSource data source
     */
    public PowerSupplyDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Get all power supplies compatible with graphics card
     * @param wattageId wattage requirement of graphics card
     * @return list of compatible power supplies
     */
    public List<PowerSupplyWithBrandWattage> getCompatiblePsuByWattage(int wattageId) {
        //Not used in api, will be used in future
        List<PowerSupplyWithBrandWattage> compatiblePsus = new ArrayList<>();
        String sql = "select psu_id, brand_name, product_name, wattage, cable_type, energy_efficiency, price, psu.psu_wattage_id as psu_wattage_num\n" +
                "from psu\n" +
                "join brand on brand.brand_id = psu.brand_id\n" +
                "join psu_wattage on psu_wattage.psu_wattage_id = psu.psu_wattage_id\n" +
                "where wattage >= ?\n" +
                "order by psu_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wattageId);
        while (results.next()) {
            PowerSupplyWithBrandWattage psu = new PowerSupplyWithBrandWattage();
            psu.setPsuId(results.getInt("psu_id"));
            psu.setBrandName(results.getString("brand_name"));
            psu.setProductName(results.getString("product_name"));
            psu.setWattage(results.getInt("wattage"));
            psu.setCableType(results.getString("cable_type"));
            psu.setEnergyEfficiency(results.getString("energy_efficiency"));
            psu.setPrice(results.getBigDecimal("price"));
            psu.setWattageId(results.getInt("psu_wattage_num"));
            compatiblePsus.add(psu);
        }
        return compatiblePsus;
    }

    /**
     * Get a power supply using id
     * @param psuId power supply id
     * @return power supply requested
     */
    public PowerSupply getPowerSupplyById(int psuId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from psu where psu_id = ?;", psuId);
        if (results.next()){
            return mapRowToPowerSupply(results);
        }
        return null;
    }

    /**
     * Get all power supplies
     * @return list of power supplies
     */
    public List<PowerSupply> getAllPsus(){
        List<PowerSupply> powerSupplies = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from psu");
        while (rowSet.next()){
            powerSupplies.add(mapRowToPowerSupply(rowSet));
        }
        return powerSupplies;
    }

    /**
     * Create a power supply
     * @param newPsu power supply
     * @return created power supply
     */
    public PowerSupply createPsu(PowerSupply newPsu){
        try{
            int psuId = jdbcTemplate.queryForObject("insert into psu (psu_wattage_id, brand_id, product_name, model, cable_type, energy_efficiency, price) " +
                            "values (?, ?, ?, ?, ?, ?, ?) returning psu_id;", int.class, newPsu.getPsuWattageId(), newPsu.getBrandId(), newPsu.getProductName(),
                    newPsu.getModel(), newPsu.getCableType(), newPsu.getEnergyEfficiency(), newPsu.getPrice());
            return getPowerSupplyById(psuId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating power supply. ", e);
        }
    }

    /**
     * Update a power supply
     * @param psuToUpdate power supply
     * @return updated power supply
     */
    public PowerSupply updatePsu(PowerSupply psuToUpdate){
        PowerSupply psu = null;
        try{
            int numOfRows = jdbcTemplate.update("update psu set psu_wattage_id=?, brand_id=?, product_name=?, model=?, cable_type=?, " +
                            "energy_efficiency=?, price=? where psu_id=?;", psuToUpdate.getPsuWattageId(), psuToUpdate.getBrandId(), psuToUpdate.getProductName(), psuToUpdate.getModel(),
                    psuToUpdate.getCableType(), psuToUpdate.getEnergyEfficiency(), psuToUpdate.getPrice(), psuToUpdate.getPsuId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                psu = getPowerSupplyById(psuToUpdate.getPsuId());
            }
            return psu;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating power supply. ", e);
        }
    }

    /**
     * Delete a power supply
     * @param id id to delete
     */
    public void deletePsu(int id){
        try{
            jdbcTemplate.update("delete from psu where psu_id = ?;", id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting power supply. ", e);
        }
    }

    /**
     * Power supply mapper
     * @param rowSet rowset
     * @return mapped power supply
     */
    public PowerSupply mapRowToPowerSupply(SqlRowSet rowSet){
        PowerSupply powerSupply = new PowerSupply();
        powerSupply.setPsuId(rowSet.getInt("psu_id"));
        powerSupply.setBrandId(rowSet.getInt("brand_id"));
        powerSupply.setProductName(rowSet.getString("product_name"));
        powerSupply.setModel(rowSet.getString("model"));
        powerSupply.setPsuWattageId(rowSet.getInt("psu_wattage_id"));
        powerSupply.setCableType(rowSet.getString("cable_type"));
        powerSupply.setEnergyEfficiency(rowSet.getString("energy_efficiency"));
        powerSupply.setPrice(rowSet.getBigDecimal("price"));
        return powerSupply;
    }
}
