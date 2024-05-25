package com.pcBuilder.controllers;

import com.pcBuilder.daos.FanDao;
import com.pcBuilder.models.Fans;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Fan controller
 */
@RestController
@RequestMapping("/fans")
public class FanController {
    /**
     * Fan data access object
     */
    private final FanDao fanDao;

    /**
     * Constructor
     * @param fanDao Fan data access object
     */
    public FanController(FanDao fanDao){
        this.fanDao = fanDao;
    }

    /**
     * Get all fans
     * @return List of fans
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<Fans> listFans(){
        return fanDao.getFansNoBrandName();
    }

    /**
     * Create a fan
     * @param fan fan
     * @return Created fan
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Fans createFan(@RequestBody Fans fan){
        return fanDao.createFan(fan);
    }

    /**
     * Update a fan
     * @param id id to update
     * @param fan fan
     * @return Updated fan
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Fans updateFan(@PathVariable int id, @RequestBody Fans fan){
        fan.setFanId(id);
        return fanDao.updateFan(fan);
    }

    /**
     * Delete a fan
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFan(@PathVariable int id){
        fanDao.deleteFan(id);
    }
}
