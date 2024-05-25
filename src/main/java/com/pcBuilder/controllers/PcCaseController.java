package com.pcBuilder.controllers;

import com.pcBuilder.daos.PcCaseDao;
import com.pcBuilder.models.PcCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Case controller
 */
@RestController
@RequestMapping("pcCases")
public class PcCaseController {
    /**
     * Case data access object
     */
    private final PcCaseDao pcCaseDao;

    /**
     * Constructor
     * @param pcCaseDao case data access object
     */
    public PcCaseController(PcCaseDao pcCaseDao){
        this.pcCaseDao = pcCaseDao;
    }

    /**
     * Get all cases
     * @return list of cases
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<PcCase> listCases(){
        return pcCaseDao.getAllCases();
    }

    /**
     * Create a case
     * @param pcCase case
     * @return created case
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public PcCase createCase(@RequestBody PcCase pcCase){
        return pcCaseDao.createCase(pcCase);
    }

    /**
     * Update a case
     * @param id id to update
     * @param pcCase case
     * @return updated case
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public PcCase updateCase(@PathVariable int id, @RequestBody PcCase pcCase){
        pcCase.setCaseId(id);
        return pcCaseDao.updateCase(pcCase);
    }

    /**
     * Delete a case
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable int id){
        pcCaseDao.deleteCase(id);
    }
}
