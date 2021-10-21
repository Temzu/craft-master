package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.services.ProfileService;
import com.gb.agile.craft_master.exceptions.DataAccessFailedException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.dtos.FindOfferDto;
import com.gb.agile.craft_master.model.dtos.MyOfferDto;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.ProfileDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferExecutorDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferStatusDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.repositories.OfferRepository;
import com.gb.agile.craft_master.services.OccupationService;
import com.gb.agile.craft_master.services.BidService;
import com.gb.agile.craft_master.services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BidServiceImpl implements BidService {

  private final OfferRepository offerRepository;
  private final UserService userService;
  private final OccupationService occupationService;
  private final ProfileService profileService;

  @Override
  public List<Offer> getAllOffersNonPaged() {
    return offerRepository.findAll();
  }

  @Override
  public Offer getOfferById(Long id) {
    checkId(id);
    return offerRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Offer.class, id));
  }

  @Override
  public void deleteOfferById(Long id) {
    checkId(id);
    checkAccess(id, JwtProvider.getUserId());
    try {
      offerRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(Offer.class, id);
    }
  }

  @Override
  public Offer saveOrUpdate(OfferDto offerDto) {
    Occupation occupation = occupationService.getOccupationById(offerDto.getOccupationId());

    Offer offer;
    if (offerDto.getId() == null) {
      User creator = userService.getProxyById(JwtProvider.getUserId());
      offer = new Offer(offerDto);
      offer.setCreator(creator);
      offer.setOccupation(occupation);
      return offerRepository.save(offer);
    } else {
      checkAccess(offerDto.getId(), JwtProvider.getUserId());
      offer = offerRepository.getById(offerDto.getId());
      offer.setTitle(offerDto.getTitle());
      offer.setDescription(offerDto.getDescription());
      offer.setOccupation(occupation);
      return offer;
    }
  }

  @Override
  public Page<OfferDto> getAllOffers(
      Specification<Offer> spec, Integer page, Integer pageSize, String[] sort, String dir) {

    Pageable pageable = getPageable(page, pageSize, sort, dir);

    Page<Offer> products = offerRepository.findAll(spec, pageable);
    int totalPages = products.getTotalPages();
    if (totalPages <= page) {
      throw new EntityNotFoundException(Page.class, page.longValue() + 1);
    }
    return products.map(OfferDto::new);
  }

  @Override
  public List<MyOfferDto> getAllOffersByCurrentUser(Integer page, Integer size, String dir,
      String[] sort) {
    Pageable pageable = getPageable(page, size, sort, dir);

    return offerRepository.findAllByCreatorId(JwtProvider.getUserId(), pageable)
        .stream().map(MyOfferDto::new).collect(Collectors.toList());
  }

  @Override
  public Page<FindOfferDto> getAllOffersForCurrentUser(Integer page, Integer pageSize) {
    Long executorId = JwtProvider.getUserId();
    List<Long> occupationsIds = profileService.getUserProfiles(executorId).stream()
        .map(ProfileDto::getOccupationId).collect(
            Collectors.toList());

    return offerRepository.findAllByOfferStatusValueAndCreatorIsNotAndOccupationIdIn(
        OfferStatus.CREATED.getCode(),
        userService.getUserById(executorId),
        occupationsIds,
        PageRequest.of(page, pageSize)).map(FindOfferDto::new);
  }

  @Override
  public MyOfferDto updateExecutor(UpdateOfferExecutorDto updateOfferExecutorDto) {
    Long offerId = updateOfferExecutorDto.getId();
    User executor = userService.getUserById(updateOfferExecutorDto.getNewExecutorId());

    checkAccess(offerId, JwtProvider.getUserId());

    Offer offer = offerRepository.getById(offerId);
    offer.setExecutor(executor);
    return new MyOfferDto(offer);
  }

  @Override
  public void updateStatus(UpdateOfferStatusDto offerStatusDto) {
    Long offerId = offerStatusDto.getId();

    checkAccess(offerId, JwtProvider.getUserId());
    checkStatus(offerStatusDto.getNewStatus());

    Offer offer = offerRepository.getById(offerId);
    offer.setOfferStatusValue(offerStatusDto.getNewStatus());
  }

  private Pageable getPageable(Integer page, Integer pageSize, String[] sort, String dir) {
    Sort sortFields = Sort.by(sort);
    return PageRequest.of(
        page,
        pageSize,
        dir.equals(Sort.Direction.ASC.toString())
            ? sortFields.ascending()
            : sortFields.descending());
  }

  private void checkAccess(Long offerId, Long userCreatorId) {
    if (!offerRepository.existsByIdAndCreator(offerId, userService.getUserById(userCreatorId))) {
      throw new DataAccessFailedException(Offer.class);
    }
  }

  private void checkStatus(int offerStatusValue) {
    if (!OfferStatus.contains(offerStatusValue)) {
      throw new EntityNotFoundException(OfferStatus.class, (long) offerStatusValue);
    }
  }

  private void checkId(Long id) {
    if (id <= 0) {
      throw new EntityBadIdException(Offer.class, id);
    }
  }
}
