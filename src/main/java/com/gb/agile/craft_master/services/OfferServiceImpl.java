package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OfferService;
import com.gb.agile.craft_master.core.interfaces.ServiceService;
import com.gb.agile.craft_master.core.interfaces.UserService;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final UserService userService;
  private final ServiceService serviceService;

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
  public Offer saveOrUpdate(OfferDto offerDto, Integer userId) {
    com.gb.agile.craft_master.model.entities.Service service = serviceService.getById(offerDto.getServiceId());
    User user = userService.getProxyById(userId);
    Offer offer = new Offer();
    offer.setId(offerDto.getId());
    offer.setTitle(offerDto.getTitle());
    offer.setDescription(offerDto.getDescription());
    offer.setUser(user);
    offer.setService(service);
    return offerRepository.save(offer);
  }

  private void checkId(Long id) {
    if (id <= 0) throw new EntityBadIdException(Offer.class, id);
  }

  public Page<OfferDto> getAllOffers(Specification<Offer> spec, Integer page,
                                     Integer size,
                                     Optional<String[]> sort) {
    Page<Offer> offers;
    if (sort.isPresent()) {
      List o = new ArrayList<Sort.Order>();
      for (int i = 0; i < sort.get().length; i++) {
        String[] s = sort.get()[i].split(",");
        if (s.length >= 2) {
          o.add(new Sort.Order(Sort.Direction.fromString(s[1]), s[0]));
        } else o.add(new Sort.Order(Sort.DEFAULT_DIRECTION, s[0]));
      }
      offers = offerRepository.findAll(spec, PageRequest.of(page, size, Sort.by(o)));
    } else
      offers = offerRepository.findAll(PageRequest.of(page, size));
    if (offers.getContent().size() > 0)
      return offers.map(OfferDto::new);
    else
      throw new EntityNotFoundException(Offer.class,"");
  }
}
