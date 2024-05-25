package com.pcBuilder.controllers;

import com.pcBuilder.daos.GraphicsCardDao;
import com.pcBuilder.daos.MotherboardDao;
import com.pcBuilder.models.GraphicsCard;
import com.pcBuilder.models.Motherboard;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("motherboards")
public class MotherboardController {
    private final MotherboardDao motherboardDao;

    public MotherboardController(MotherboardDao motherboardDao){
        this.motherboardDao = motherboardDao;
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<Motherboard> listMobos(){
        return motherboardDao.getAllMobos();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Motherboard createMobo(@RequestBody Motherboard mobo){
        return motherboardDao.createMobo(mobo);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Motherboard updateMobo(@PathVariable int id, @RequestBody Motherboard mobo){
        mobo.setMotherboardId(id);
        return motherboardDao.updateMobo(mobo);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMobo(@PathVariable int id){
        motherboardDao.deleteMobo(id);
    }
}
