package com.pcBuilder.controllers;

import com.pcBuilder.daos.UserPcBuildDao;
import com.pcBuilder.models.UserPcBuild;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Pc build controller
 */
@RestController
@RequestMapping("/pcBuilds")
public class UserPcBuildController {
    /**
     * Pc build data access object
     */
    private final UserPcBuildDao userPcBuildDao;

    /**
     * Constructor
     * @param userPcBuildDao pc build data access object
     */
    public UserPcBuildController(UserPcBuildDao userPcBuildDao){
        this.userPcBuildDao = userPcBuildDao;
    }

    /**
     * Get all pc builds
     * @return list of pc builds
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<UserPcBuild> listBuilds(){
        return userPcBuildDao.getAllUserPcBuilds();
    }

    /**
     * Create a pc build
     * @param build pc build
     * @return created pc build
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public UserPcBuild createBuild(@Valid @RequestBody UserPcBuild build){
        return userPcBuildDao.createUserPcBuild(build);
    }

    /**
     * Update a pc build
     * @param id id to update
     * @param build pc build
     * @return updated build
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UserPcBuild updateBuild(@PathVariable int id, @Valid @RequestBody UserPcBuild build){
        build.setPcId(id);
        return userPcBuildDao.updateUserPcBuild(build);
    }

    /**
     * Delete a pc build
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBuild(@PathVariable int id){
        userPcBuildDao.deleteUserPcBuild(id);
    }
}
