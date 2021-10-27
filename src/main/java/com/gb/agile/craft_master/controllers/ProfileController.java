package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.core.enums.StatusCode;
import com.gb.agile.craft_master.model.dtos.*;
import com.gb.agile.craft_master.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userprofiles")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{id}")
    public List<ProfileDto> getProfiles(@PathVariable Long id) {
        return profileService.getUserProfiles(id);
    }

    @GetMapping("/my_profiles")
    public List<GetProfileDto> getUserProfiles() {
        return profileService.getAllUserProfiles();
    }

    @PostMapping("/add_profiles")
    public StatusDto addProfiles(@RequestBody ListAddProfileDto listAddProfileDto) {
        System.out.println("listAddProfileDto::" + listAddProfileDto);
        profileService.addProfiles(listAddProfileDto);
        return new StatusDto(StatusCode.STATUS_OK);
    }

    @PostMapping("/delete_profiles")
    public StatusDto deleteProfiles(@RequestBody DeleteProfilesDto deleteProfilesDto) {
        profileService.deleteProfiles(deleteProfilesDto);
        return new StatusDto(StatusCode.STATUS_OK);
    }

}
