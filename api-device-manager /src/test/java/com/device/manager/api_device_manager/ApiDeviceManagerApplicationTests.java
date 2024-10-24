package com.device.manager.api_device_manager;


import com.example.devicemanager.model.Device;
import com.example.devicemanager.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @Autowired
    private ObjectMapper objectMapper;

    private Device device;

    @BeforeEach
    public void setup() {
        device = new Device("Device 1", "Brand A");
        device.setId(1L);
    }

    @Test
    public void addDeviceTest() throws Exception {
        Mockito.when(deviceService.addDevice(any(Device.class))).thenReturn(device);

        mockMvc.perform(post("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.brand").value(device.getBrand()));
    }


    @Test
    public void getDeviceByIdTest() throws Exception {
        Mockito.when(deviceService.getDeviceById(anyLong())).thenReturn(Optional.of(device));

        mockMvc.perform(get("/devices/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.brand").value(device.getBrand()));
    }

    
    @Test
    public void getDeviceByIdNotFoundTest() throws Exception {
        Mockito.when(deviceService.getDeviceById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/devices/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

   
    @Test
    public void getAllDevicesTest() throws Exception {
        Device device2 = new Device("Device 2", "Brand B");
        device2.setId(2L);

        Mockito.when(deviceService.getAllDevices()).thenReturn(Arrays.asList(device, device2));

        mockMvc.perform(get("/devices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(device.getName()))
                .andExpect(jsonPath("$[1].name").value(device2.getName()));
    }

  
    @Test
    public void updateDeviceTest() throws Exception {
        Device updatedDevice = new Device("Updated Device", "Updated Brand");
        updatedDevice.setId(1L);

        Mockito.when(deviceService.getDeviceById(anyLong())).thenReturn(Optional.of(device));
        Mockito.when(deviceService.updateDevice(any(Device.class))).thenReturn(updatedDevice);

        mockMvc.perform(put("/devices/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDevice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedDevice.getName()))
                .andExpect(jsonPath("$.brand").value(updatedDevice.getBrand()));
    }

   
    @Test
    public void deleteDeviceTest() throws Exception {
        Mockito.when(deviceService.getDeviceById(anyLong())).thenReturn(Optional.of(device));
        Mockito.doNothing().when(deviceService).deleteDevice(anyLong());

        mockMvc.perform(delete("/devices/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void searchDeviceByBrandTest() throws Exception {
        Mockito.when(deviceService.searchByBrand(eq("Brand A"))).thenReturn(Arrays.asList(device));

        mockMvc.perform(get("/devices/search")
                .param("brand", "Brand A")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(device.getName()))
                .andExpect(jsonPath("$[0].brand").value(device.getBrand()));
    }
}
