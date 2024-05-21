package com.pcBuilder.controllers;

import com.pcBuilder.daos.CpuCoolerDao;
import com.pcBuilder.models.CpuCooler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cpuCooler")
public class CpuCoolerController {
    private final CpuCoolerDao cpuCoolerDao;

    public CpuCoolerController(CpuCoolerDao cpuCoolerDao){
        this.cpuCoolerDao = cpuCoolerDao;
    }

    @GetMapping("")
    public List<CpuCooler> listCoolers(){
        return cpuCoolerDao.getAllCoolers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public CpuCooler createCooler(@Valid @RequestBody CpuCooler cpuCooler){
        return cpuCoolerDao.createCpuCooler(cpuCooler);
    }

    @PutMapping("/{id}")
    public CpuCooler updateCooler(@PathVariable int id, @RequestBody CpuCooler cpuCooler){
        cpuCooler.setCpuCoolerId(id);
        return cpuCoolerDao.updateCooler(cpuCooler);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCooler(@PathVariable int id){
        cpuCoolerDao.deleteCooler(id);
    }
}
