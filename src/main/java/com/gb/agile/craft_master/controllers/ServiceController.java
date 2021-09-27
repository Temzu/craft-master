package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.core.interfaces.ServiceService;
import com.gb.agile.craft_master.model.dtos.ServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/")
    public List<ServiceDto> getAll() {
        return serviceService.getAll();
    }
}
