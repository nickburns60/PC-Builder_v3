package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.CaseWithBrandFormFactor;
import com.pcBuilder.models.PcCase;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * Case data access object
 */
@Component
public class PcCaseDao {
    /**
     * JDBC template
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     * @param dataSource data source
     */
    public PcCaseDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * Get all cases compatible with motherboard
     * @param formFactorId form factor from motherboard
     * @return list of compatible motherboards
     */
    public List<CaseWithBrandFormFactor> getCompatibleCases(int formFactorId){
        //Not used in api, will be used in future
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

    /**
     * Get a case using id
     * @param caseId case id
     * @return case requested
     */
    public PcCase getCaseById(int caseId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from pc_case where case_id = ?;", caseId);
        if (results.next()){
            return mapRowToCase(results);
        }
        return null;
    }

    /**
     * Get all cases
     * @return list of cases
     */
    public List<PcCase> getAllCases(){
        List<PcCase> cases = new ArrayList<>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from pc_case");
        while (rowSet.next()){
            cases.add(mapRowToCase(rowSet));
        }
        return cases;
    }

    /**
     * Create a case
     * @param newCase case
     * @return created case
     */
    public PcCase createCase(PcCase newCase){
        try{
            int caseId = jdbcTemplate.queryForObject("insert into pc_case (form_factor_id, brand_id, product_name, model, color, rgb, length_mm, width_mm, num_fans_included, price) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning case_id;", int.class, newCase.getFormFactorId(), newCase.getBrandId(), newCase.getProductName(), newCase.getModel(),
                    newCase.getColor(), newCase.isRgb(), newCase.getLengthInMm(), newCase.getWidthInMm(), newCase.getNumFansIncluded(), newCase.getPrice());
            return getCaseById(caseId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating pc case. ", e);
        }
    }

    /**
     * Update a case
     * @param caseToUpdate case
     * @return updated case
     */
    public PcCase updateCase(PcCase caseToUpdate){
        PcCase pcCase = null;
        try{
            int numOfRows = jdbcTemplate.update("update pc_case set form_factor_id=?, brand_id=?, product_name=?, model=?, color=?, rgb=?, length_mm=?, " +
                            "width_mm=?, num_fans_included=?, price=? where case_id=?;", caseToUpdate.getFormFactorId(), caseToUpdate.getBrandId(), caseToUpdate.getProductName(), caseToUpdate.getModel(),
                   caseToUpdate.getColor(), caseToUpdate.isRgb(), caseToUpdate.getLengthInMm(), caseToUpdate.getWidthInMm(), caseToUpdate.getNumFansIncluded(), caseToUpdate.getPrice(), caseToUpdate.getCaseId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                pcCase = getCaseById(caseToUpdate.getCaseId());
            }
            return pcCase;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating pc case. ", e);
        }
    }

    /**
     * Delete a case
     * @param id id to delete
     */
    public void deleteCase(int id){
        try{
            jdbcTemplate.update("delete from pc_case where case_id = ?;", id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting case. ", e);
        }
    }

    /**
     * Case mapper
     * @param rowSet rowset
     * @return mapped case
     */
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
