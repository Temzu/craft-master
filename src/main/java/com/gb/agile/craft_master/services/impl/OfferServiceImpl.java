package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.core.interfaces.ProfileService;
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
import com.gb.agile.craft_master.services.OfferService;
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
public class OfferServiceImpl implements OfferService {

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
    try {
      offerRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(Offer.class, id);
    }
  }

  @Override
  public Offer saveOrUpdate(OfferDto offerDto) {
    Long userId = JwtProvider.getUserId();
    Occupation occupation = occupationService.getOccupationById(offerDto.getOccupationId());
    User creator = userService.getProxyById(userId);
    int offerStatusValue = offerDto.getOfferStatusValue();

    checkStatus(offerStatusValue);

    Offer offer = new Offer();
    offer.setId(offerDto.getId());
    offer.setTitle(offerDto.getTitle());
    offer.setDescription(offerDto.getDescription());
    offer.setCreator(creator);
    offer.setOccupation(occupation);
    offer.setOfferStatusValue(offerStatusValue);

    return offerRepository.save(offer);
  }

  @Override
  public Page<OfferDto> getAllOffers(
      Specification<Offer> spec, Integer page, Integer pageSize, String[] sort, String dir) {

    Sort sortFields = Sort.by(sort);
    Pageable pageable =
        PageRequest.of(
            page,
            pageSize,
            dir.equals(Sort.Direction.ASC.toString())
                ? sortFields.ascending()
                : sortFields.descending());

    Page<Offer> products = offerRepository.findAll(spec, pageable);
    int totalPages = products.getTotalPages();
    if (totalPages <= page) {
      throw new EntityNotFoundException(Page.class, page.longValue() + 1);
    }
    return products.map(OfferDto::new);
  }

  @Override
  public List<MyOfferDto> getAllOffersByCurrentUser() {
    User creator = userService.getUserById(JwtProvider.getUserId());
    return offerRepository.getAllByCreator(creator).stream().map(MyOfferDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public Page<FindOfferDto> getAllOffersForCurrentUser(Integer page, Integer pageSize) {
    Long executorId = JwtProvider.getUserId();
    List<Long> occupations = profileService.getUserProfiles(executorId).stream()
        .map(ProfileDto::getOccupationId).collect(
            Collectors.toList());
    System.out.println(occupations);

    List<Occupation> curUserOccupations = occupationService.getAllByOccupationId(occupations);

    System.out.println(curUserOccupations);

    return offerRepository.findAllByOccupationIn(curUserOccupations,
        PageRequest.of(page, pageSize)).map(FindOfferDto::new);
  }

  @Override
  public MyOfferDto updateExecutor(UpdateOfferExecutorDto updateOfferExecutorDto) {
    Long creatorId = JwtProvider.getUserId();
    Long offerId = updateOfferExecutorDto.getId();
    User executor = userService.getUserById(updateOfferExecutorDto.getNewExecutorId());

    checkAccess(offerId, creatorId);

    Offer offer = offerRepository.getById(offerId);
    offer.setExecutor(executor);
    return new MyOfferDto(offer);
  }

  @Override
  public void updateStatus(UpdateOfferStatusDto offerStatusDto) {
    Long creatorId = JwtProvider.getUserId();
    Long offerId = offerStatusDto.getId();

    checkAccess(offerId, creatorId);
    checkStatus(offerStatusDto.getNewStatus());

    Offer offer = offerRepository.getById(offerId);
    offer.setOfferStatusValue(offerStatusDto.getNewStatus());
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
