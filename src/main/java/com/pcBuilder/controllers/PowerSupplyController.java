package com.pcBuilder.controllers;

import com.pcBuilder.daos.PowerSupplyDao;
import com.pcBuilder.models.PowerSupply;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Power supply controller
 */
@RestController
@RequestMapping("powerSupplies")
public class PowerSupplyController {
    /**
     * Power supply data access object
     */
    private final PowerSupplyDao powerSupplyDao;

    /**
     * Constructor
     * @param powerSupplyDao power supply data access object
     */
    public PowerSupplyController(PowerSupplyDao powerSupplyDao){
        this.powerSupplyDao = powerSupplyDao;
    }

    /**
     * Get all power supplies
     * @return list of power supplies
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<PowerSupply> listPsus(){
        return powerSupplyDao.getAllPsus();
    }

    /**
     * Create a power supply
     * @param psu power supply
     * @return created power supply
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public PowerSupply createPsu(@RequestBody PowerSupply psu){
        return powerSupplyDao.createPsu(psu);
    }

    /**
     * Update a power supply
     * @param id id to update
     * @param psu power supply
     * @return updated power supply
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public PowerSupply updatePsu(@PathVariable int id, @RequestBody PowerSupply psu){
        psu.setPsuId(id);
        return powerSupplyDao.updatePsu(psu);
    }

    /**
     * Delete a power supply
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePsu(@PathVariable int id){
        powerSupplyDao.deletePsu(id);
    }
}
