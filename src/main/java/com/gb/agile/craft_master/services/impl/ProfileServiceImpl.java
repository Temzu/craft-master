package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.services.ProfileService;
import com.gb.agile.craft_master.model.dtos.ProfileDto;
import com.gb.agile.craft_master.model.entities.Profile;
import com.gb.agile.craft_master.repositories.ProfileRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;

  @Override
  public List<ProfileDto> getUserProfiles(Long userId) {
    return profileRepository.getAllByUserId(userId).stream().map(ProfileDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteProfileById(Long id) {

  }

  @Override
  public Profile saveOrUpdate(Profile profile) {
    return null;
  }
}
