package com.pcBuilder.controllers;

import com.pcBuilder.daos.PowerSupplyDao;
import com.pcBuilder.daos.ProcessorDao;
import com.pcBuilder.models.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("processors")
public class ProcessorController {
    private final ProcessorDao processorDao;

    public ProcessorController(ProcessorDao processorDao){
        this.processorDao = processorDao;
    }

    @GetMapping("")
    public List<Processor> listCpus(){
        return processorDao.getAllCpus();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Processor createCpu(@RequestBody Processor cpu){
        return processorDao.createCpu(cpu);
    }

    @PutMapping("/{id}")
    public Processor updateCpu(@PathVariable int id, @RequestBody Processor cpu){
        cpu.setProcessorId(id);
        return processorDao.updateCpu(cpu);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCpu(@PathVariable int id){
        processorDao.deleteCpu(id);
    }
}
