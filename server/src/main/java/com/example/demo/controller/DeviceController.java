package com.example.demo.controller;

import com.example.demo.model.domains.Device;
import com.example.demo.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<Device>> getDevices() {
        return new ResponseEntity<>(deviceService.getDevices(), HttpStatus.OK);
    }
}
