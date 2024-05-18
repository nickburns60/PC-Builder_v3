package com.pcBuilder.daos;

import com.pcBuilder.viewmodels.RamWithBrandRamType;
import com.pcBuilder.models.Ram;
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
