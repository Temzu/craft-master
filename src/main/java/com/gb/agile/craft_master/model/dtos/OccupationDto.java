package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Occupation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OccupationDto {
    private Integer id;
    private String name;
    private List<OccupationDto> child;

    public OccupationDto(Occupation category) {
        this.id = category.getId();
        this.name = category.getName();
        this.child = new ArrayList<>();
    }

    public OccupationDto(Integer id) {
        this.id = id;
        this.child = new ArrayList<>();
    }

    public void addChild(OccupationDto c) {
        this.child.add(c);
    }
}
