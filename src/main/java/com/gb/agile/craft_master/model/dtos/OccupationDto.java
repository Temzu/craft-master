package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Occupation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OccupationDto {
    private Long id;
    private String name;
    private List<OccupationDto> child;

    public OccupationDto(Occupation occupation) {
        this.id = occupation.getId();
        this.name = occupation.getName();
        this.child = new ArrayList<>();
    }

    public OccupationDto(Long id) {
        this.id = id;
        this.child = new ArrayList<>();
    }

    public void addChild(OccupationDto c) {
        this.child.add(c);
    }
}
