package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.exceptions.DataAccessFailedException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.dtos.MyOfferDto;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferExecutorDto;
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

    if (!OfferStatus.contains(offerStatusValue)) {
      throw new EntityNotFoundException(OfferStatus.class, (long) offerStatusValue);
    }

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
      Specification<Offer> spec, Integer page, Integer size, String[] sort, String dir) {

    Sort sortFields = Sort.by(sort);
    Pageable pageable =
        PageRequest.of(
            page,
            size,
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
  public List<MyOfferDto> getAllOfferByCreator() {
    User creator = userService.getUserById(JwtProvider.getUserId());
    return offerRepository.getAllByCreator(creator).stream().map(MyOfferDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public MyOfferDto updateExecutor(UpdateOfferExecutorDto updateOfferExecutorDto) {
    Long userCreatorId = JwtProvider.getUserId();
    User executor = userService.getUserById(updateOfferExecutorDto.getNewExecutorId());
    if (!offerRepository.existsByCreator(userService.getUserById(userCreatorId))) {
      throw new DataAccessFailedException(Offer.class);
    }
    Offer offer = offerRepository.getById(updateOfferExecutorDto.getId());
    offer.setExecutor(executor);
    return new MyOfferDto(offer);
  }

  private void checkId(Long id) {
    if (id <= 0) {
      throw new EntityBadIdException(Offer.class, id);
    }
  }
}
