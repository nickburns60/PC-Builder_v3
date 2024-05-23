package com.pcBuilder.controllers;

import com.pcBuilder.daos.StorageDriveDao;
import com.pcBuilder.models.StorageDrive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("storageDrives")
public class StorageDriveController {
    private final StorageDriveDao storageDriveDao;

    public StorageDriveController(StorageDriveDao storageDriveDao){
        this.storageDriveDao = storageDriveDao;
    }

    @GetMapping("")
    public List<StorageDrive> listStorageDrive(){
        return storageDriveDao.getAllStorageDrives();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public StorageDrive createStorageDrive(@RequestBody StorageDrive storageDrive){
        return storageDriveDao.createStorageDrive(storageDrive);
    }

    @PutMapping("/{id}")
    public StorageDrive updateStorageDrive(@PathVariable int id, @RequestBody StorageDrive storageDrive){
        storageDrive.setStorageDriveId(id);
        return storageDriveDao.updateStorageDrive(storageDrive);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStorageDrive(@PathVariable int id){
        storageDriveDao.deleteStorageDrive(id);
    }
}
