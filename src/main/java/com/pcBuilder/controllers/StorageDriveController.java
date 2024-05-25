package com.pcBuilder.controllers;

import com.pcBuilder.daos.StorageDriveDao;
import com.pcBuilder.models.StorageDrive;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Storage drive controller
 */
@RestController
@RequestMapping("storageDrives")
public class StorageDriveController {
    /**
     * Storage drive data access object
     */
    private final StorageDriveDao storageDriveDao;

    /**
     * Constructor
     * @param storageDriveDao storage drive data access object
     */
    public StorageDriveController(StorageDriveDao storageDriveDao){
        this.storageDriveDao = storageDriveDao;
    }

    /**
     * Get all storage drives
     * @return list of storage drives
     */
    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<StorageDrive> listStorageDrive(){
        return storageDriveDao.getAllStorageDrives();
    }

    /**
     * Create a storage drive
     * @param storageDrive storage drive
     * @return created storage drive
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public StorageDrive createStorageDrive(@RequestBody StorageDrive storageDrive){
        return storageDriveDao.createStorageDrive(storageDrive);
    }

    /**
     * Update a storage drive
     * @param id id to update
     * @param storageDrive storage drive
     * @return updated storage drive
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public StorageDrive updateStorageDrive(@PathVariable int id, @RequestBody StorageDrive storageDrive){
        storageDrive.setStorageDriveId(id);
        return storageDriveDao.updateStorageDrive(storageDrive);
    }

    /**
     * Delete a storage drive
     * @param id id to delete
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStorageDrive(@PathVariable int id){
        storageDriveDao.deleteStorageDrive(id);
    }
}
