package com.pcBuilder.controllers;

import com.pcBuilder.daos.MotherboardDao;
import com.pcBuilder.daos.PowerSupplyDao;
import com.pcBuilder.models.Motherboard;
import com.pcBuilder.models.PowerSupply;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("powerSupplies")
public class PowerSupplyController {
    private final PowerSupplyDao powerSupplyDao;

    public PowerSupplyController(PowerSupplyDao powerSupplyDao){
        this.powerSupplyDao = powerSupplyDao;
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<PowerSupply> listPsus(){
        return powerSupplyDao.getAllPsus();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public PowerSupply createPsu(@RequestBody PowerSupply psu){
        return powerSupplyDao.createPsu(psu);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public PowerSupply updatePsu(@PathVariable int id, @RequestBody PowerSupply psu){
        psu.setPsuId(id);
        return powerSupplyDao.updatePsu(psu);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePsu(@PathVariable int id){
        powerSupplyDao.deletePsu(id);
    }
}
