package com.pcBuilder.controllers;

import com.pcBuilder.daos.RamDao;
import com.pcBuilder.models.Ram;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("ram")
public class RamController {
    private final RamDao ramDao;

    public RamController(RamDao ramDao){
        this.ramDao = ramDao;
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<Ram> listRam(){
        return ramDao.getAllRam();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Ram createRam(@RequestBody Ram ram){
        return ramDao.createRam(ram);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Ram updateRam(@PathVariable int id, @RequestBody Ram ram){
        ram.setRamId(id);
        return ramDao.updateRam(ram);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCpu(@PathVariable int id){
        ramDao.deleteRam(id);
    }
}
