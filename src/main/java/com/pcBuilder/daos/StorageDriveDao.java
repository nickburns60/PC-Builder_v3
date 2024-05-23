package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import com.pcBuilder.models.StorageDrive;
import com.pcBuilder.viewmodels.StorageDriveWithBrand;
import com.pcBuilder.models.StorageDrive;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class StorageDriveDao {
    private final JdbcTemplate jdbcTemplate;

    public StorageDriveDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<StorageDriveWithBrand> getStorageDrivesWithBrands(){
        List<StorageDriveWithBrand> storageDrives = new ArrayList<>();
        String sql = "select storage_drive_id, brand_name, product_name, capacity_gb, form_factor, price\n" +
                "from storage_drive\n" +
                "join brand on brand.brand_id = storage_drive.brand_id;\n";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()){
            StorageDriveWithBrand storageDrive = new StorageDriveWithBrand();
            storageDrive.setStorageDriveId(rowSet.getInt("storage_drive_id"));
            storageDrive.setBrandName(rowSet.getString("brand_name"));
            storageDrive.setProductName(rowSet.getString("product_name"));
            storageDrive.setCapacityGb(rowSet.getInt("capacity_gb"));
            storageDrive.setFormFactor(rowSet.getString("form_factor"));
            storageDrive.setPrice(rowSet.getBigDecimal("price"));
            storageDrives.add(storageDrive);
        }
        return storageDrives;
    }

    public StorageDrive getStorageDriveById(int storageDriveId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from storage_drive where storage_drive_id = ?;", storageDriveId);
        if (results.next()){
            return mapRowToStorageDrive(results);
        }
        return null;
    }

    public List<StorageDrive> getAllStorageDrives(){
        List<StorageDrive> storageDrives = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from storage_drive");
        while (rowSet.next()){
            storageDrives.add(mapRowToStorageDrive(rowSet));
        }
        return storageDrives;
    }


    public StorageDrive createStorageDrive(StorageDrive newStoragedrive){
        try{
            int storageDriveId = jdbcTemplate.queryForObject("insert into storage_drive (brand_id, product_name, model, capacity_gb, form_factor, price) " +
                            "values (?, ?, ?, ?, ?, ?) returning storage_drive_id;", int.class, newStoragedrive.getBrandId(), newStoragedrive.getProductName(), newStoragedrive.getModel(),
                    newStoragedrive.getCapacityGb(), newStoragedrive.getFormFactor(), newStoragedrive.getPrice());
            return getStorageDriveById(storageDriveId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating storage drive. ", e);
        }
    }

    public StorageDrive updateStorageDrive(StorageDrive storageDriveToUpdate){
        StorageDrive storageDrive = null;
        try{
            int numOfRows = jdbcTemplate.update("update storage_drive set brand_id=?, product_name=?, model=?, capacity_gb=?, form_factor=?, price=? where storage_drive_id=?;", storageDriveToUpdate.getBrandId(),
                    storageDriveToUpdate.getProductName(), storageDriveToUpdate.getModel(), storageDriveToUpdate.getCapacityGb(), storageDriveToUpdate.getFormFactor(), storageDriveToUpdate.getPrice(), storageDriveToUpdate.getStorageDriveId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                storageDrive = getStorageDriveById(storageDriveToUpdate.getStorageDriveId());
            }
            return storageDrive;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating storage drive. ", e);
        }
    }

    public void deleteStorageDrive(int id){
        try{
            jdbcTemplate.update("delete from storage_drive where storage_drive_id = ?;", id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting storage drive. ", e);
        }
    }

    public StorageDrive mapRowToStorageDrive(SqlRowSet rowSet){
        StorageDrive storageDrive = new StorageDrive();
        storageDrive.setStorageDriveId(rowSet.getInt("storage_drive_id"));
        storageDrive.setBrandId(rowSet.getInt("brand_id"));
        storageDrive.setProductName(rowSet.getString("product_name"));
        storageDrive.setModel(rowSet.getString("model"));
        storageDrive.setCapacityGb(rowSet.getInt("capacity_gb"));
        storageDrive.setFormFactor(rowSet.getString("form_factor"));
        storageDrive.setPrice(rowSet.getBigDecimal("price"));
        return storageDrive;
    }
}
