package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.dtos.*;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.model.entities.Profile;

import java.util.List;

public interface ProfileService {

    List<ProfileDto> getUserProfiles(Long userId);

    List<GetProfileDto> getAllUserProfiles();

    void deleteProfileById(Long id);

    Profile saveOrUpdate(Profile profile);

    void addProfiles(ListAddProfileDto listAddProfileDto);

    void deleteProfiles(DeleteProfilesDto deleteProfilesDto);
}
