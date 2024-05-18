package com.pcBuilder.daos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.PowerSupplyWithBrandWattage;
import com.pcBuilder.models.PowerSupply;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class PowerSupplyDao {
    private final JdbcTemplate jdbcTemplate;

    public PowerSupplyDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<PowerSupplyWithBrandWattage> getCompatiblePsuByWattage(int wattageId) {
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

    public PowerSupply getPowerSupplyById(int psuId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from psu where psu_id = ?;", psuId);
        if (results.next()){
            return mapRowToPowerSupply(results);
        }
        return null;
    }

    public PowerSupply mapRowToPowerSupply(SqlRowSet rowSet){
        PowerSupply powerSupply = new PowerSupply();
        powerSupply.setPsuId(rowSet.getInt("psu_id"));
        powerSupply.setBrandId(rowSet.getInt("brand_id"));
        powerSupply.setProductName(rowSet.getString("product_name"));
        powerSupply.setModel(rowSet.getString("model"));
        powerSupply.setPsuId(rowSet.getInt("psu_wattage_id"));
        powerSupply.setCableType(rowSet.getString("cable_type"));
        powerSupply.setEnergyEfficiency(rowSet.getString("energy_efficiency"));
        powerSupply.setPrice(rowSet.getBigDecimal("price"));
        return powerSupply;
    }
}
