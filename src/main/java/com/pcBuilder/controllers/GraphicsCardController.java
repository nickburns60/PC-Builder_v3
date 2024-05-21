package com.pcBuilder.controllers;


import com.pcBuilder.daos.GraphicsCardDao;
import com.pcBuilder.models.GraphicsCard;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("graphicsCards")
public class GraphicsCardController {
    private final GraphicsCardDao graphicsCardDao;

    public GraphicsCardController(GraphicsCardDao graphicsCardDao){
        this.graphicsCardDao = graphicsCardDao;
    }

    @GetMapping("")
    public List<GraphicsCard> listGpus(){
        return graphicsCardDao.getAllGpus();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public GraphicsCard createGpu(@RequestBody GraphicsCard gpu){
        return graphicsCardDao.createGpu(gpu);
    }

    @PutMapping("/{id}")
    public GraphicsCard updateGpu(@PathVariable int id, @RequestBody GraphicsCard gpu){
        gpu.setGraphicsCardId(id);
        return graphicsCardDao.updateGpu(gpu);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGpu(@PathVariable int id){
        graphicsCardDao.deleteGpu(id);
    }
}
