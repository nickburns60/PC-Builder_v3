package com.pcBuilder.daos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.CaseWithBrandFormFactor;
import com.pcBuilder.models.PcCase;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class PcCaseDao {
    private final JdbcTemplate jdbcTemplate;

    public PcCaseDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<CaseWithBrandFormFactor> getCompatibleCases(int formFactorId){
        List<CaseWithBrandFormFactor> cases = new ArrayList<>();
        String sql = "select case_id, brand_name, product_name, form_factor_name, color, rgb, length_mm, width_mm, num_fans_included, price, pc_case.form_factor_id as form_factor_num\n" +
                "from pc_case\n" +
                "join brand on brand.brand_id = pc_case.brand_id\n" +
                "join form_factor on form_factor.form_factor_id = pc_case.form_factor_id\n" +
                "where form_factor.form_factor_id >= ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, formFactorId);
        while (rowSet.next()){
            CaseWithBrandFormFactor pcCase = new CaseWithBrandFormFactor();
            pcCase.setCaseId(rowSet.getInt("case_id"));
            pcCase.setBrandName(rowSet.getString("brand_name"));
            pcCase.setProductName(rowSet.getString("product_name"));
            pcCase.setFormFactorName(rowSet.getString("form_factor_name"));
            pcCase.setColor(rowSet.getString("color"));
            pcCase.setRgb(rowSet.getBoolean("rgb"));
            pcCase.setLengthInMm(rowSet.getInt("length_mm"));
            pcCase.setWidthInMm(rowSet.getInt("width_mm"));
            pcCase.setNumFansIncluded(rowSet.getInt("num_fans_included"));
            pcCase.setPrice(rowSet.getBigDecimal("price"));
            pcCase.setFormFactorNum(rowSet.getInt("form_factor_num"));
            cases.add(pcCase);
        }
        return cases;
    }

    public PcCase getCaseById(int caseId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from pc_case where case_id = ?;", caseId);
        if (results.next()){
            return mapRowToCase(results);
        }
        return null;
    }

    public PcCase mapRowToCase(SqlRowSet rowSet){
        PcCase pcCase = new PcCase();
        pcCase.setCaseId(rowSet.getInt("case_id"));
        pcCase.setBrandId(rowSet.getInt("brand_id"));
        pcCase.setProductName(rowSet.getString("product_name"));
        pcCase.setModel(rowSet.getString("model"));
        pcCase.setFormFactorId(rowSet.getInt("form_factor_id"));
        pcCase.setColor(rowSet.getString("color"));
        pcCase.setRgb(rowSet.getBoolean("rgb"));
        pcCase.setLengthInMm(rowSet.getInt("length_mm"));
        pcCase.setWidthInMm(rowSet.getInt("width_mm"));
        pcCase.setNumFansIncluded(rowSet.getInt("num_fans_included"));
        pcCase.setPrice(rowSet.getBigDecimal("price"));
        return pcCase;
    }
}
