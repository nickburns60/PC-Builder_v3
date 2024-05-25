package com.pcBuilder.controllers;

import com.pcBuilder.daos.FanDao;
import com.pcBuilder.models.Fans;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fans")
public class FanController {
    private final FanDao fanDao;

    public FanController(FanDao fanDao){
        this.fanDao = fanDao;
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<Fans> listFans(){
        return fanDao.getFansNoBrandName();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Fans createFan(@RequestBody Fans fan){
        return fanDao.createFan(fan);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Fans updateFan(@PathVariable int id, @RequestBody Fans fan){
        fan.setFanId(id);
        return fanDao.updateFan(fan);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFan(@PathVariable int id){
        fanDao.deleteFan(id);
    }
}
