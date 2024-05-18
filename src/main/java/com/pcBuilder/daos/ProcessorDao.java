package com.pcBuilder.daos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.ProcessorWithBrandSocketRam;
import com.pcBuilder.models.Processor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessorDao {
    private final JdbcTemplate jdbcTemplate;

    public ProcessorDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Processor> getAllProcessors(){
        List<Processor> processors = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from processor;");
        while(results.next()){
            processors.add(mapRowToProcessor(results));
        }
        return processors;
    }

    public List<ProcessorWithBrandSocketRam> getAllProcessorsWithFullInfoDisplayed(){
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

    public Processor getProcessorByProcessorId(int id){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from processor where processor_id = ?", id);
        if(results.next()){
            return mapRowToProcessor(results);
        }
        return null;
    }

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
