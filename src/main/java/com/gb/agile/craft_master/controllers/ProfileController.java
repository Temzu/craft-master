package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.core.interfaces.ProfileService;
import com.gb.agile.craft_master.model.dtos.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
