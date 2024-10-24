package com.device.manager.controller;


import com.example.devicemanager.model.Device;
import com.example.devicemanager.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        Device newDevice = deviceService.addDevice(device);
        return ResponseEntity.status(201).body(newDevice);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Optional<Device> device = deviceService.getDeviceById(id);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

  
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device updatedDevice) {
        Optional<Device> optionalDevice = deviceService.getDeviceById(id);
        if (optionalDevice.isPresent()) {
            Device existingDevice = optionalDevice.get();
            existingDevice.setName(updatedDevice.getName());
            existingDevice.setBrand(updatedDevice.getBrand());
            Device savedDevice = deviceService.updateDevice(existingDevice);
            return ResponseEntity.ok(savedDevice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        Optional<Device> optionalDevice = deviceService.getDeviceById(id);
        if (optionalDevice.isPresent()) {
            deviceService.deleteDevice(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Device>> searchByBrand(@RequestParam String brand) {
        List<Device> devices = deviceService.searchByBrand(brand);
        return ResponseEntity.ok(devices);
    }
}
