package com.pcBuilder.controllers;


import com.pcBuilder.daos.GraphicsCardDao;
import com.pcBuilder.models.GraphicsCard;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Graphics card controller
 */
@RestController
@RequestMapping("graphicsCards")
public class GraphicsCardController {
    /**
     * Graphics card data access object
     */
    private final GraphicsCardDao graphicsCardDao;

    /**
     * Constructor
     * @param graphicsCardDao graphics card data access object
     */
    public GraphicsCardController(GraphicsCardDao graphicsCardDao){
        this.graphicsCardDao = graphicsCardDao;
    }

    /**
     * Get all graphics cards
     * @return a list of graphics cards
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<GraphicsCard> listGpus(){
        return graphicsCardDao.getAllGpus();
    }

    /**
     * Create a graphics card
     * @param gpu graphics card
     * @return Created graphics card
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public GraphicsCard createGpu(@RequestBody GraphicsCard gpu){
        return graphicsCardDao.createGpu(gpu);
    }

    /**
     * Update a graphics card
     * @param id Id of graphics card to update
     * @param gpu graphics card
     * @return Updated graphics card
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public GraphicsCard updateGpu(@PathVariable int id, @RequestBody GraphicsCard gpu){
        gpu.setGraphicsCardId(id);
        return graphicsCardDao.updateGpu(gpu);
    }

    /**
     * Delete a graphics card
     * @param id Id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGpu(@PathVariable int id){
        graphicsCardDao.deleteGpu(id);
    }
}
