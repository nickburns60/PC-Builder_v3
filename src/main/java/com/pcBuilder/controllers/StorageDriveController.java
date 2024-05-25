package com.pcBuilder.controllers;

import com.pcBuilder.daos.StorageDriveDao;
import com.pcBuilder.models.StorageDrive;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("storageDrives")
public class StorageDriveController {
    private final StorageDriveDao storageDriveDao;

    public StorageDriveController(StorageDriveDao storageDriveDao){
        this.storageDriveDao = storageDriveDao;
    }

    @PreAuthorize("isAuthenticated")
    @GetMapping("")
    public List<StorageDrive> listStorageDrive(){
        return storageDriveDao.getAllStorageDrives();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public StorageDrive createStorageDrive(@RequestBody StorageDrive storageDrive){
        return storageDriveDao.createStorageDrive(storageDrive);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public StorageDrive updateStorageDrive(@PathVariable int id, @RequestBody StorageDrive storageDrive){
        storageDrive.setStorageDriveId(id);
        return storageDriveDao.updateStorageDrive(storageDrive);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStorageDrive(@PathVariable int id){
        storageDriveDao.deleteStorageDrive(id);
    }
}
