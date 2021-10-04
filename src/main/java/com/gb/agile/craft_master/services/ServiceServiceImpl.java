package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.ServiceService;
import com.gb.agile.craft_master.model.dtos.ServiceDto;
import com.gb.agile.craft_master.model.entities.Service;
import com.gb.agile.craft_master.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public Service getById(Integer id) {
        return serviceRepository.getById(id);
    }

    @Override
    public List<ServiceDto> getAll() {
        //ToDo: закешировать результат
        List<Service> serviceList = serviceRepository.findAll();
        HashMap<Integer, ServiceDto> mapServiceDto = new HashMap<>();
        List<ServiceDto> resList = new ArrayList<>();

        for (Service service : serviceList) {
            ServiceDto serviceDto;
            if (mapServiceDto.containsKey(service.getId())) {
                serviceDto = mapServiceDto.get(service.getId());
                serviceDto.setName(service.getName());
            } else {
                serviceDto = new ServiceDto(service);
                mapServiceDto.put(service.getId(), serviceDto);
            }
            if (service.getParentId() == null) {
                resList.add(serviceDto);
            } else {
                ServiceDto parent;
                if (mapServiceDto.containsKey(service.getParentId())) {
                    parent = mapServiceDto.get(service.getParentId());
                } else {
                    parent = new ServiceDto(service.getParentId());
                    mapServiceDto.put(parent.getId(), parent);
                }
                parent.addChild(serviceDto);
            }
        }
        return resList;
    }
}
