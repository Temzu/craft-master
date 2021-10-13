package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.model.entities.Profile;
import com.gb.agile.craft_master.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private User user;
    private Long occupationId;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.user = profile.getUser();
        this.occupationId = profile.getOccupationId();
    }

}
