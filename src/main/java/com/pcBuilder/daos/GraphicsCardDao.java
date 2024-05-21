package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import com.pcBuilder.models.UserPcBuild;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.pcBuilder.viewmodels.GPUWithBrandWattage;
import com.pcBuilder.models.GraphicsCard;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class GraphicsCardDao {
    private final JdbcTemplate jdbcTemplate;

    public GraphicsCardDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GraphicsCard> getAllGpus(){
        List<GraphicsCard> graphicsCards = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from graphics_card");
        while(results.next()){
            graphicsCards.add(mapRowToGpu(results));
        }
        return graphicsCards;
    }

    public List<GPUWithBrandWattage> getAllGpusWithFullInfoDisplayed(){
        List<GPUWithBrandWattage> gpuWithBrandWattages = new ArrayList<>();
        String sql = "select graphics_card_id, brand_name, graphics_card.product_name, wattage, graphics_card.price\n" +
                "from graphics_card\n" +
                "join brand on brand.brand_id = graphics_card.brand_id\n" +
                "join psu_wattage on psu_wattage.psu_wattage_id = graphics_card.psu_wattage_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            GPUWithBrandWattage gpu = new GPUWithBrandWattage();
            gpu.setGraphicsCardId(results.getInt("graphics_card_id"));
            gpu.setBrandName(results.getString("brand_name"));
            gpu.setProductName(results.getString("product_name"));
            gpu.setWattage(results.getInt("wattage"));
            gpu.setPrice(results.getBigDecimal("price"));
            gpuWithBrandWattages.add(gpu);
        }
        return gpuWithBrandWattages;
    }

    public GraphicsCard getGraphicsCardById(int gpuId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from graphics_card where graphics_card_id = ?;", gpuId);
        if(results.next()){
            return mapRowToGpu(results);
        }
        return null;
    }

    public GraphicsCard createGpu(GraphicsCard newGpu){
        try{
            int gpuId = jdbcTemplate.queryForObject("insert into graphics_card (brand_id, product_name, model, psu_wattage_id, price) " +
                    "values (?, ?, ?, ?, ?) returning graphics_card_id;", int.class, newGpu.getBrandId(), newGpu.getProductName(), newGpu.getModel(), newGpu.getPsuWattageId(), newGpu.getPrice());
            return getGraphicsCardById(gpuId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating graphics card. ", e);
        }
    }

    public GraphicsCard updateGpu(GraphicsCard gpuToUpdate){
        GraphicsCard gpu = null;
        try{
            int numOfRows = jdbcTemplate.update("update graphics_card set brand_id=?, product_name=?, model=?, psu_wattage_id=?, price=? where graphics_card_id=?;", gpuToUpdate.getBrandId(),
                    gpuToUpdate.getProductName(), gpuToUpdate.getModel(), gpuToUpdate.getPsuWattageId(), gpuToUpdate.getPrice(), gpuToUpdate.getGraphicsCardId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                gpu = getGraphicsCardById(gpuToUpdate.getGraphicsCardId());
            }
            return gpu;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating graphics card. ", e);
        }
    }

    public void deleteGpu(int id){
        int numRows = 0;
        try{
            numRows = jdbcTemplate.update("delete from graphics_card where graphics_card_id = ?;", id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting graphics card. ", e);
        }
    }

    public GraphicsCard mapRowToGpu(SqlRowSet rowSet){
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setGraphicsCardId(rowSet.getInt("graphics_card_id"));
        graphicsCard.setBrandId(rowSet.getInt("brand_id"));
        graphicsCard.setProductName(rowSet.getString("product_name"));
        graphicsCard.setModel(rowSet.getString("model"));
        graphicsCard.setPsuWattageId(rowSet.getInt("psu_wattage_id"));
        graphicsCard.setPrice(rowSet.getBigDecimal("price"));
        return graphicsCard;
    }

}
