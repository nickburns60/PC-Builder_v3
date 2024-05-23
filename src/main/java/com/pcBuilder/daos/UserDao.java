package com.pcBuilder.daos;

import com.pcBuilder.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getUser(String username){
        return jdbcTemplate.queryForObject("select * from users where username = ?", this::mapRowToUser);
    }

    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from users", this::mapRowToUser);
    }

    public void createUser(User user){
        jdbcTemplate.update("insert into users (username, password, email) values (?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getEmail());
    }

    public void updateUser(User user){
        jdbcTemplate.update("update users set password=?, email=? where username=?", user.getPassword(), user.getEmail(), user.getUsername());
    }

    public void deleteUser(String username){
        jdbcTemplate.update("delete from users where username = ?", username);
    }

    public List<String> getRoleForUser(String username){
        return jdbcTemplate.queryForList("select role from roles where username = ?", String.class, username);
    }

    public void addRoleToUser(String username,String role){
        jdbcTemplate.update("insert into roles (username, role) values (?, ?)", username, role);
    }

    public void removeRoleFromUser(String username, String role){
        jdbcTemplate.update("delete from roles where username = ? and role = ?", username, role);
    }

    public boolean checkUsernamePassword(String username, String password){
        return jdbcTemplate.queryForObject("select count(*) from users where username = ? and password = ?", Integer.class, username, password) > 0;
    }

    private User mapRowToUser(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setUsername(row.getString("username"));
        user.setPassword(row.getString("password"));
        user.setEmail(row.getString("email"));
        return user;
    }
}
