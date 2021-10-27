package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.model.dtos.*;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.services.OccupationService;
import com.gb.agile.craft_master.services.ProfileService;
import com.gb.agile.craft_master.model.entities.Profile;
import com.gb.agile.craft_master.repositories.ProfileRepository;
import java.util.stream.Collectors;

import com.gb.agile.craft_master.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;
  private final OccupationService occupationService;
  private final UserService userService;

  @Override
  public List<ProfileDto> getUserProfiles(Long userId) {
    return profileRepository.getAllByUserId(userId).stream().map(ProfileDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public List<GetProfileDto> getAllUserProfiles() {
    return profileRepository.findAllByUserId(JwtProvider.getUserId()).stream().map(GetProfileDto::new).collect(Collectors.toList());
  }

  @Override
  public void deleteProfileById(Long id) {

  }

  @Override
  public Profile saveOrUpdate(Profile profile) {
    return null;
  }

  @Override
  public void addProfiles(ListAddProfileDto listAddProfileDto) {
    for (AddProfileDto addProfileDto : listAddProfileDto.getProfiles()) {
      Occupation occupation = occupationService.getOccupationById(addProfileDto.getOccupationId());
      User user = userService.getProxyById(JwtProvider.getUserId());
      Profile profile = new Profile();
      profile.setOccupation(occupation);
      profile.setUser(user);
      profile.setWorkExp(addProfileDto.getWorkExp());
      profile.setDescription(addProfileDto.getDescription());
      profileRepository.save(profile);
    }
  }

  @Override
  public void deleteProfiles(DeleteProfilesDto deleteProfilesDto) {
    profileRepository.deleteByIds(deleteProfilesDto.getIds(), JwtProvider.getUserId());
  }
}
