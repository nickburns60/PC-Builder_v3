package com.pcBuilder.controllers;

import com.pcBuilder.daos.UserPcBuildDao;
import com.pcBuilder.models.UserPcBuild;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  May attempt to add compatibility logic at a later time
/**
 * Primary goals:
 * <p>
 * Maintain compatibility checks by making sure all selections added to create method are compatible with each other; do this by somehow adding validations for this in UserPcBuild class
 * or find a way to integrate original checks from command line version of this app, such as the getCompatible...etc. methods that used compatible specific sql and the viewmodel classes.
 * Find a way to get the JSON to show the full set of info for selected parts instead of just the serial id of each part listed on a build.
 */

@RestController
@RequestMapping("/pcBuilds")
public class UserPcBuildController {
    private final UserPcBuildDao userPcBuildDao;

    public UserPcBuildController(UserPcBuildDao userPcBuildDao){
        this.userPcBuildDao = userPcBuildDao;
    }

    @GetMapping("")
    public List<UserPcBuild> listBuilds(){
        return userPcBuildDao.getAllUserPcBuilds();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public UserPcBuild createBuild(@Valid @RequestBody UserPcBuild build){
        return userPcBuildDao.createUserPcBuild(build);
    }
    @PutMapping("/{id}")
    public UserPcBuild updateBuild(@PathVariable int id, @Valid @RequestBody UserPcBuild build){
        build.setPcId(id);
        return userPcBuildDao.updateUserPcBuild(build);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBuild(@PathVariable int id){
        userPcBuildDao.deleteUserPcBuild(id);
    }
}
