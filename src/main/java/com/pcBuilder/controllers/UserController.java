package com.pcBuilder.controllers;

import com.pcBuilder.daos.UserDao;
import com.pcBuilder.models.User;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * User controller
 */
@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * User data access object
     */
    private UserDao userDao;

    /**
     * Constructor
     * @param userDao user data access object
     */
    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    /**
     * Get all users
     * @return a list of users
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    //Want to make it so a user can get their own user info
    /**
     * Get a user by username
     * @param username username
     * @return user searched for
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userDao.getUser(username);
    }

    /**
     * Create a new user
     * @param user User to be created
     * @return Created user
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User createUser(@Valid @RequestBody User user){
        return userDao.createUser(user);
    }

    /**
     * Update a user
     * @param username Username to update
     * @param user User to update
     * @return Updated user
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @Valid @RequestBody User user){
        return userDao.updateUser(user, false);
    }

    /**
     * Delete a user
     * @param username Username to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username){
        userDao.deleteUser(username);
    }

    /**
     * Get all roles of given user
     * @param username Username to search roles of
     * @return List of the users roles
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}/roles")
    public List<String> getUserRoles(@PathVariable String username){
        return userDao.getRolesForUser(username);
    }

    /**
     * Create a role for a user
     * @param username User receiving new role
     * @param role Role to create
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{username}/roles")
    public void addUserRole(@PathVariable String username, @RequestBody String role){
        userDao.addRoleToUser(username, role);
    }

    /**
     * Delete a role from a user
     * @param username User losing a role
     * @param role Role to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}/roles/{role}")
    public void removeUserRole(@PathVariable String username, @PathVariable String role){
        userDao.removeRoleFromUser(username, role);
    }
}
