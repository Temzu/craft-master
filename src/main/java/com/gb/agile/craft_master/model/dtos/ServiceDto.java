package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private Integer id;
    private String name;
    private List<ServiceDto> child;

    public ServiceDto(Service service) {
        this.id = service.getId();
        this.name = service.getName();
        this.child = new ArrayList<>();
    }

    public ServiceDto(Integer id) {
        this.id = id;
        this.child = new ArrayList<>();
    }

    public void addChild(ServiceDto c) {
        this.child.add(c);
    }
}
