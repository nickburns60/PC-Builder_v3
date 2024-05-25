package com.pcBuilder.daos;

import com.pcBuilder.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserDao(DataSource dataSource, PasswordEncoder passwordEncoder){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String username){
        try{
            return jdbcTemplate.queryForObject("select * from users where username = ?", this::mapRowToUser, username);

        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from users order by username", this::mapRowToUser);
    }


    public User createUser(User user){
        try{
            jdbcTemplate.update("insert into users (username, password, email) values (?, ?, ?)",
                    user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getEmail());
            return getUser(user.getUsername());
        }catch (Exception e){
            return null;
        }


    }

    public User updateUser(User user, boolean updatePassword){
        if(updatePassword){
            jdbcTemplate.update("update users set password=?, email=? where username=?", passwordEncoder.encode(user.getPassword()), user.getEmail(), user.getUsername());
        }else {
            jdbcTemplate.update("update users set email=? where username=?", user.getEmail(), user.getUsername());
        }
        return getUser(user.getUsername());
    }

    public void deleteUser(String username){
        jdbcTemplate.update("delete from users where username = ?", username);
    }

    public List<String> getRolesForUser(String username){
        return jdbcTemplate.queryForList("select role from roles where username = ?", String.class, username);
    }

    public void addRoleToUser(String username,String role){
        try{
            jdbcTemplate.update("insert into roles (username, role) values (?, ?)", username, role);
        }catch (Exception e){
            //ignore
        }
    }

    public void removeRoleFromUser(String username, String role){
        jdbcTemplate.update("delete from roles where username = ? and role = ?", username, role);
    }

    public boolean checkUsernamePassword(String username, String password){
        User user = getUser(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    private User mapRowToUser(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setUsername(row.getString("username"));
        user.setPassword(row.getString("password"));
        user.setEmail(row.getString("email"));
        return user;
    }
}
