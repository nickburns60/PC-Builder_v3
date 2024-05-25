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


/**
 * User data access object
 */
@Component
public class UserDao {
    /**
     * JDBC template
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * Password encoder
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor
     * @param dataSource data source
     * @param passwordEncoder password encoder
     */
    public UserDao(DataSource dataSource, PasswordEncoder passwordEncoder){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *Get a user by username
     * @param username username
     * @return user
     */
    public User getUser(String username){
        try{
            return jdbcTemplate.queryForObject("select * from users where username = ?", this::mapRowToUser, username);

        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from users order by username", this::mapRowToUser);
    }


    /**
     * Create a user
     * @param user user
     * @return Created user
     */
    public User createUser(User user){
        try{
            jdbcTemplate.update("insert into users (username, password, email) values (?, ?, ?)",
                    user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getEmail());
            return getUser(user.getUsername());
        }catch (Exception e){
            return null;
        }


    }

    /**
     * Update a user
     * @param user user
     * @param updatePassword Checks if password is updated
     * @return Updated user
     */
    public User updateUser(User user, boolean updatePassword){
        if(updatePassword){
            jdbcTemplate.update("update users set password=?, email=? where username=?", passwordEncoder.encode(user.getPassword()), user.getEmail(), user.getUsername());
        }else {
            jdbcTemplate.update("update users set email=? where username=?", user.getEmail(), user.getUsername());
        }
        return getUser(user.getUsername());
    }

    /**
     * Delete a user
     * @param username username
     */
    public void deleteUser(String username){
        jdbcTemplate.update("delete from users where username = ?", username);
    }

    /**
     * Get all roles user
     * @param username username
     * @return List of users roles
     */
    public List<String> getRolesForUser(String username){
        return jdbcTemplate.queryForList("select role from roles where username = ?", String.class, username);
    }

    /**
     * Create a role
     * @param username username
     * @param role role
     */
    public void addRoleToUser(String username,String role){
        try{
            jdbcTemplate.update("insert into roles (username, role) values (?, ?)", username, role);
        }catch (Exception e){
            //ignore
        }
    }

    /**
     * Delete a role
     * @param username username
     * @param role role
     */
    public void removeRoleFromUser(String username, String role){
        jdbcTemplate.update("delete from roles where username = ? and role = ?", username, role);
    }

    /**
     * User mapper
     * @param row row
     * @param rowNum row number
     * @return Mapped user
     * @throws SQLException sql exception
     */
    private User mapRowToUser(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setUsername(row.getString("username"));
        user.setPassword(row.getString("password"));
        user.setEmail(row.getString("email"));
        return user;
    }
}
