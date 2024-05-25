package com.pcBuilder.controllers;

import com.pcBuilder.daos.PowerSupplyDao;
import com.pcBuilder.daos.ProcessorDao;
import com.pcBuilder.models.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Processor controller
 */
@RestController
@RequestMapping("processors")
public class ProcessorController {
    /**
     * Processor data access object
     */
    private final ProcessorDao processorDao;

    /**
     * Constructor
     * @param processorDao processor data access object
     */
    public ProcessorController(ProcessorDao processorDao){
        this.processorDao = processorDao;
    }

    /**
     * Get all processors
     * @return list of processors
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<Processor> listCpus(){
        return processorDao.getAllProcessors();
    }

    /**
     * Create a processor
     * @param cpu processor
     * @return created processor
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Processor createCpu(@RequestBody Processor cpu){
        return processorDao.createCpu(cpu);
    }

    /**
     * Update a processor
     * @param id id to update
     * @param cpu processor
     * @return updated processor
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Processor updateCpu(@PathVariable int id, @RequestBody Processor cpu){
        cpu.setProcessorId(id);
        return processorDao.updateCpu(cpu);
    }

    /**
     * Delete a processor
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCpu(@PathVariable int id){
        processorDao.deleteCpu(id);
    }
}
