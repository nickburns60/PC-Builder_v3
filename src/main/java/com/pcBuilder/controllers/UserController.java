package com.pcBuilder.controllers;

import com.pcBuilder.daos.UserDao;
import com.pcBuilder.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    //Want to make it so a user can get their own user info
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userDao.getUser(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User createUser(@RequestBody User user){
        return userDao.createUser(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user){
        return userDao.updateUser(user, false);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username){
        userDao.deleteUser(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}/roles")
    public List<String> getUserRoles(@PathVariable String username){
        return userDao.getRolesForUser(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{username}/roles")
    public void addUserRole(@PathVariable String username, @RequestBody String role){
        userDao.addRoleToUser(username, role);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}/roles/{role}")
    public void removeUserRole(@PathVariable String username, @PathVariable String role){
        userDao.removeRoleFromUser(username, role);
    }
}
