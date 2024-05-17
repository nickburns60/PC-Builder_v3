package com.pcBuilder.controllers;

import com.pcBuilder.daos.UserPcBuildDao;
import com.pcBuilder.models.UserPcBuild;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pcBuilds")
public class UserPcBuildController {
    private UserPcBuildDao userPcBuildDao;

    public UserPcBuildController(UserPcBuildDao userPcBuildDao){
        this.userPcBuildDao = userPcBuildDao;
    }

    @GetMapping("")
    public List<UserPcBuild> listBuilds(){
        return userPcBuildDao.getAllUserPcBuilds();
    }
}
