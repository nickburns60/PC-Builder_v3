package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import com.pcBuilder.models.Processor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.ProcessorWithBrandSocketRam;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * Processor data access object
 */
@Component
public class ProcessorDao {
    /**
     * JDBC template
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     * @param dataSource data source
     */
    public ProcessorDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Get all processors
     * @return list of processors
     */
    public List<Processor> getAllProcessors(){
        List<Processor> processors = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from processor;");
        while(results.next()){
            processors.add(mapRowToProcessor(results));
        }
        return processors;
    }

    /**
     * Get all processors with names of compatibility parts
     * @return list of processors with named parts
     */
    public List<ProcessorWithBrandSocketRam> getAllProcessorsWithFullInfoDisplayed(){
        //Not used in api, will be used in future
        List<ProcessorWithBrandSocketRam> processors = new ArrayList<>();
        String sql = "select processor_id, brand_name, processor.product_name, socket_type, ram_type_name, processor.price\n" +
                "from processor\n" +
                "join brand on brand.brand_id = processor.brand_id\n" +
                "join socket on socket.socket_id = processor.socket_id\n" +
                "join ram_type on ram_type.ram_type_id = processor.ram_type_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            ProcessorWithBrandSocketRam processor = new ProcessorWithBrandSocketRam();
            processor.setProcessorId(results.getInt("processor_id"));
            processor.setBrandName(results.getString("brand_name"));
            processor.setProductName(results.getString("product_name"));
            processor.setSocketType(results.getString("socket_type"));
            processor.setRamTypeName(results.getString("ram_type_name"));
            processor.setPrice(results.getBigDecimal("price"));
            processors.add(processor);
        }
        return processors;
    }

    /**
     * Get a processor using id
     * @param id processor id
     * @return processor requested
     */
    public Processor getProcessorByProcessorId(int id){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from processor where processor_id = ?", id);
        if(results.next()){
            return mapRowToProcessor(results);
        }
        return null;
    }


    /**
     * Create a processor
     * @param newCpu processor
     * @return created processor
     */
    public Processor createCpu(Processor newCpu){
        try{
            int cpuId = jdbcTemplate.queryForObject("insert into processor (brand_id, socket_id, ram_type_id, product_name, model, price) " +
                            "values (?, ?, ?, ?, ?, ?) returning processor_id;", int.class, newCpu.getBrandId(), newCpu.getSocketId(), newCpu.getRamTypeId(),
                    newCpu.getProductName(), newCpu.getModel(), newCpu.getPrice());
            return getProcessorByProcessorId(cpuId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating processor. ", e);
        }
    }

    /**
     * Update a processor
     * @param cpuToUpdate processor
     * @return updated processor
     */
    public Processor updateCpu(Processor cpuToUpdate){
        Processor cpu = null;
        try{
            int numOfRows = jdbcTemplate.update("update processor set brand_id=?, socket_id=?, ram_type_id=?, product_name=?, model=?, price=? where processor_id=?;", cpuToUpdate.getBrandId(), cpuToUpdate.getSocketId(),
                    cpuToUpdate.getRamTypeId(), cpuToUpdate.getProductName(), cpuToUpdate.getModel(), cpuToUpdate.getPrice(), cpuToUpdate.getProcessorId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                cpu = getProcessorByProcessorId(cpuToUpdate.getProcessorId());
            }
            return cpu;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating processor. ", e);
        }
    }

    /**
     * Delete a processor
     * @param id id to delete
     */
    public void deleteCpu(int id){
        try{
            jdbcTemplate.update("delete from processor where processor_id = ?;", id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting processor. ", e);
        }
    }

    /**
     * Processor mapper
     * @param rowSet rowset
     * @return mapped processor
     */
    public Processor mapRowToProcessor(SqlRowSet rowSet){
        Processor processor = new Processor();
        processor.setProcessorId(rowSet.getInt("processor_id"));
        processor.setBrandId(rowSet.getInt("brand_id"));
        processor.setSocketId(rowSet.getInt("socket_id"));
        processor.setRamTypeId(rowSet.getInt("ram_type_id"));
        processor.setProductName(rowSet.getString("product_name"));
        processor.setModel(rowSet.getString("model"));
        processor.setPrice(rowSet.getBigDecimal("price"));
        return processor;
    }
}
