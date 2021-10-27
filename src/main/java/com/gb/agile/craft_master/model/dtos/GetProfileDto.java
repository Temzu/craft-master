package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Profile;
import lombok.Data;

@Data
public class GetProfileDto {
    private Long id;
    private OccupationNameDto occupation;
    private Integer workExp;
    private String description;

    public GetProfileDto(Profile profile) {
        this.id = profile.getId();
        this.occupation = new OccupationNameDto(profile.getOccupation().getId(), profile.getOccupation().getName());
        this.workExp = profile.getWorkExp();
        this.description = profile.getDescription();
    }
}
