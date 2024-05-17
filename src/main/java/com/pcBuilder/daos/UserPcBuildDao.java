package com.pcBuilder.daos;

import com.pcBuilder.DaoException;
import com.pcBuilder.models.UserPcBuild;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserPcBuildDao {
    private JdbcTemplate jdbcTemplate;

    public UserPcBuildDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserPcBuild getUserPcBuildByPcId(int id){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from user_pc_build where pc_id = ?;", id);
        if(results.next()){
            return mapRowToUserPcBuild(results);
        }
        return null;
    }
    public List<UserPcBuild> getAllUserPcBuilds(){
        List<UserPcBuild> userPcBuilds = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from user_pc_build;");
        while (results.next()){
            userPcBuilds.add(mapRowToUserPcBuild(results));
        }
        return userPcBuilds;
    }

    public UserPcBuild createUserPcBuild(UserPcBuild newBuild){
        try{
            int pcId = jdbcTemplate.queryForObject("insert into user_pc_build (processor_id, graphics_card_id, " +
                            "motherboard_id, ram_id, psu_id, storage_drive_id, case_id, cpu_cooler_id, fan_id, total_cost) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning pc_id;", int.class, newBuild.getProcessorId(), newBuild.getGraphicsCardId(),
                    newBuild.getMotherboardId(), newBuild.getRamId(), newBuild.getPsuId(), newBuild.getStorageDriveId(), newBuild.getCaseId(),
                    newBuild.getCpuCoolerId(), newBuild.getFanId(), newBuild.getTotalCost());
            return getUserPcBuildByPcId(pcId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating UserPcBuild. ", e);
        }
    }

    public UserPcBuild updateUserPcBuild(UserPcBuild userPcBuild){
        UserPcBuild pcBuild = null;
        try{
            int numOfRows = jdbcTemplate.update("update user_pc_build set processor_id=?, graphics_card_id=?, motherboard_id=?, ram_id=?, psu_id=?, storage_drive_id=?, case_id=?, cpu_cooler_id=?, " +
                            "fan_id=?, total_cost=? where pc_id=?;", userPcBuild.getProcessorId(), userPcBuild.getGraphicsCardId(), userPcBuild.getMotherboardId(), userPcBuild.getRamId(), userPcBuild.getPsuId(),
                    userPcBuild.getStorageDriveId(), userPcBuild.getCaseId(), userPcBuild.getCpuCoolerId(), userPcBuild.getFanId(), userPcBuild.getTotalCost(), userPcBuild.getPcId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                pcBuild = getUserPcBuildByPcId(userPcBuild.getPcId());
            }
            return pcBuild;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating brand. ", e);
        }
    }

    public int deleteUserPcBuild(int pcId){
        int numRows = 0;
        try{
            numRows = jdbcTemplate.update("delete from user_pc_build where pc_id = ?;", pcId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting brand. ", e);
        }
        return numRows;
    }

    public UserPcBuild mapRowToUserPcBuild(SqlRowSet rowSet){
        UserPcBuild userPcBuild = new UserPcBuild();
        userPcBuild.setPcId(rowSet.getInt("pc_id"));
        userPcBuild.setProcessorId(rowSet.getInt("processor_id"));
        userPcBuild.setGraphicsCardId(rowSet.getInt("graphics_card_id"));
        userPcBuild.setMotherboardId(rowSet.getInt("motherboard_id"));
        userPcBuild.setRamId(rowSet.getInt("ram_id"));
        userPcBuild.setPsuId(rowSet.getInt("psu_id"));
        userPcBuild.setStorageDriveId(rowSet.getInt("storage_drive_id"));
        userPcBuild.setCaseId(rowSet.getInt("case_id"));
        userPcBuild.setFanId(rowSet.getInt("fan_id"));
        userPcBuild.setCpuCoolerId(rowSet.getInt("cpu_cooler_id"));
        userPcBuild.setTotalCost(rowSet.getBigDecimal("total_cost"));
        return userPcBuild;
    }
}
