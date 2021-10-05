package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.dtos.ServiceDto;
import com.gb.agile.craft_master.model.entities.Service;

import java.util.List;

public interface ServiceService {
    Service getById(Integer id);

    List<ServiceDto> getAll();
}
