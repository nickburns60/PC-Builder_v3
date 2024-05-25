package com.pcBuilder.controllers;

import com.pcBuilder.daos.MotherboardDao;
import com.pcBuilder.models.Motherboard;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Motherboard controller
 */
@RestController
@RequestMapping("motherboards")
public class MotherboardController {
    /**
     * Motherboard data access object
     */
    private final MotherboardDao motherboardDao;

    /**
     * Constructor
     * @param motherboardDao Motherboard data access object
     */
    public MotherboardController(MotherboardDao motherboardDao){
        this.motherboardDao = motherboardDao;
    }

    /**
     * Get all motherboards
     * @return a list of motherboards
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<Motherboard> listMobos(){
        return motherboardDao.getAllMobos();
    }

    /**
     * Create a motherboard
     * @param mobo Motherboard
     * @return Created motherboard
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Motherboard createMobo(@RequestBody Motherboard mobo){
        return motherboardDao.createMobo(mobo);
    }

    /**
     * Update a motherboard
     * @param id Id of motherboard to update
     * @param mobo Motherboard
     * @return Updated motherboard
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Motherboard updateMobo(@PathVariable int id, @RequestBody Motherboard mobo){
        mobo.setMotherboardId(id);
        return motherboardDao.updateMobo(mobo);
    }

    /**
     * Delete a motherboard
     * @param id Id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMobo(@PathVariable int id){
        motherboardDao.deleteMobo(id);
    }
}
