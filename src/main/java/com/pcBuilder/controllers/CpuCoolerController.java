package com.pcBuilder.controllers;

import com.pcBuilder.daos.CpuCoolerDao;
import com.pcBuilder.models.CpuCooler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Cpu cooler controller
 */
@RestController
@RequestMapping("/cpuCooler")
public class CpuCoolerController {
    /**
     * cpu cooler data access object
     */
    private final CpuCoolerDao cpuCoolerDao;

    /**
     * Constructor
     * @param cpuCoolerDao cpu cooler data access object
     */
    public CpuCoolerController(CpuCoolerDao cpuCoolerDao){
        this.cpuCoolerDao = cpuCoolerDao;
    }

    /**
     * Get all cpu coolers
     * @return list of cpu coolers
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<CpuCooler> listCoolers(){
        return cpuCoolerDao.getAllCoolers();
    }

    /**
     * Create a cpu cooler
     * @param cpuCooler cpu cooler
     * @return created cpu cooler
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public CpuCooler createCooler(@Valid @RequestBody CpuCooler cpuCooler){
        return cpuCoolerDao.createCpuCooler(cpuCooler);
    }

    /**
     * Update a cpu cooler
     * @param id id to update
     * @param cpuCooler cpu cooler
     * @return updated cpu cooler
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public CpuCooler updateCooler(@PathVariable int id, @RequestBody CpuCooler cpuCooler){
        cpuCooler.setCpuCoolerId(id);
        return cpuCoolerDao.updateCooler(cpuCooler);
    }

    /**
     * Delete a cpu cooler
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCooler(@PathVariable int id){
        cpuCoolerDao.deleteCooler(id);
    }
}
