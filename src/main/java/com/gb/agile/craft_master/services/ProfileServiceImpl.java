package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OccupationService;
import com.gb.agile.craft_master.core.interfaces.OfferService;
import com.gb.agile.craft_master.core.interfaces.ProfileService;
import com.gb.agile.craft_master.core.interfaces.UserService;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.ProfileDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.Profile;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.repositories.OccupationRepository;
import com.gb.agile.craft_master.repositories.OfferRepository;
import com.gb.agile.craft_master.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;

  @Override
  public List<ProfileDto> getUserProfiles(Long userId) {
    return profileRepository.getAllByUserId(userId);
  }

  @Override
  public void deleteProfileById(

          Long id) {

  }

  @Override
  public Profile saveOrUpdate(Profile profile) {
    return null;
  }
}
