package com.pcBuilder.controllers;

import com.pcBuilder.daos.RamDao;
import com.pcBuilder.models.Ram;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Ram controller
 */
@RestController
@RequestMapping("ram")
public class RamController {
    /**
     * Ram data access object
     */
    private final RamDao ramDao;

    /**
     * Constructor
     * @param ramDao ram data access object
     */
    public RamController(RamDao ramDao){
        this.ramDao = ramDao;
    }

    /**
     * Get all ram
     * @return list of ram
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<Ram> listRam(){
        return ramDao.getAllRam();
    }

    /**
     * Create ram
     * @param ram ram
     * @return created ram
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Ram createRam(@RequestBody Ram ram){
        return ramDao.createRam(ram);
    }

    /**
     * Update ram
     * @param id id to update
     * @param ram ram
     * @return updated ram
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Ram updateRam(@PathVariable int id, @RequestBody Ram ram){
        ram.setRamId(id);
        return ramDao.updateRam(ram);
    }

    /**
     * Delete ram
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCpu(@PathVariable int id){
        ramDao.deleteRam(id);
    }
}
