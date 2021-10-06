package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OccupationService;
import com.gb.agile.craft_master.core.interfaces.OfferService;
import com.gb.agile.craft_master.core.interfaces.UserService;
import com.gb.agile.craft_master.exceptions.InvalidPageException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.entities.Occupation;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.repositories.OfferRepository;
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
  public Offer saveOrUpdate(OfferDto offerDto, Long userId) {
    Occupation occupation = occupationService.getOccupationById(offerDto.getOccupationId());
    User user = userService.getProxyById(userId);
    Offer offer = new Offer();
    offer.setId(offerDto.getId());
    offer.setTitle(offerDto.getTitle());
    offer.setDescription(offerDto.getDescription());
    offer.setUser(user);
    offer.setOccupation(occupation);
    return offerRepository.save(offer);
  }

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
    if (totalPages <= page) throw new EntityNotFoundException(Page.class, page.longValue() + 1);
    return products.map(OfferDto::new);
  }

  private void checkId(Long id) {
    if (id <= 0) throw new EntityBadIdException(Offer.class, id);
  }
}
