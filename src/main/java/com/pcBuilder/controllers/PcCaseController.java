package com.pcBuilder.controllers;

import com.pcBuilder.daos.MotherboardDao;
import com.pcBuilder.daos.PcCaseDao;
import com.pcBuilder.models.Motherboard;
import com.pcBuilder.models.PcCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pcCases")
public class PcCaseController {
    private final PcCaseDao pcCaseDao;

    public PcCaseController(PcCaseDao pcCaseDao){
        this.pcCaseDao = pcCaseDao;
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<PcCase> listCases(){
        return pcCaseDao.getAllCases();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public PcCase createCase(@RequestBody PcCase pcCase){
        return pcCaseDao.createCase(pcCase);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public PcCase updateCase(@PathVariable int id, @RequestBody PcCase pcCase){
        pcCase.setCaseId(id);
        return pcCaseDao.updateCase(pcCase);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable int id){
        pcCaseDao.deleteCase(id);
    }
}
