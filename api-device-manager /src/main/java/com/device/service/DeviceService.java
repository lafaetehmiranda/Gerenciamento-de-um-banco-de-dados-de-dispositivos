package com.device.manager.service;

import com.example.devicemanager.model.Device;
import com.example.devicemanager.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> searchByBrand(String brand) {
        return deviceRepository.findByBrand(brand);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }
}

