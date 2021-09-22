package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OfferService;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  @Override
  public List<Offer> getAllOffers() {
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
  public Offer saveOrUpdate(Offer offer) {
    return offerRepository.save(offer);
  }

  private void checkId(Long id) {
    if (id <= 0) throw new EntityBadIdException(Offer.class, id);
  }
}
