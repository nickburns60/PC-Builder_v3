package com.pcBuilder.controllers;


import com.pcBuilder.daos.GraphicsCardDao;
import com.pcBuilder.models.GraphicsCard;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("graphicsCards")
public class GraphicsCardController {
    private final GraphicsCardDao graphicsCardDao;

    public GraphicsCardController(GraphicsCardDao graphicsCardDao){
        this.graphicsCardDao = graphicsCardDao;
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<GraphicsCard> listGpus(){
        return graphicsCardDao.getAllGpus();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public GraphicsCard createGpu(@RequestBody GraphicsCard gpu){
        return graphicsCardDao.createGpu(gpu);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public GraphicsCard updateGpu(@PathVariable int id, @RequestBody GraphicsCard gpu){
        gpu.setGraphicsCardId(id);
        return graphicsCardDao.updateGpu(gpu);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGpu(@PathVariable int id){
        graphicsCardDao.deleteGpu(id);
    }
}
