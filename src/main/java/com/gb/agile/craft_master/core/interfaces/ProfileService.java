package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.dtos.ProfileDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.model.entities.Profile;

import java.util.List;

public interface ProfileService {

    List<ProfileDto> getUserProfiles(Long userId);

    void deleteProfileById(Long id);

    Profile saveOrUpdate(Profile profile);

}
