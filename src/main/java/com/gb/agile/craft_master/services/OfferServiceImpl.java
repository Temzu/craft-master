package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.exceptions.OfferException;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.repositories.OfferRepository;
import com.gb.agile.craft_master.services.interfaces.OfferService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    return offerRepository.findById(id).orElseThrow(() -> OfferException.offerNotFound(id));
  }

  @Override
  public void deleteOfferById(Long id) {
    offerRepository.deleteById(id);
  }

  @Override
  public Offer saveOrUpdate(Offer offer) {
    return offerRepository.save(offer);
  }
}
