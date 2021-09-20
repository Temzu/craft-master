package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.entities.Offer;
import java.util.List;

public interface OfferService {

  List<Offer> getAllOffers();

  Offer getOfferById(Long id);

  void deleteOfferById(Long id);

  Offer saveOrUpdate(Offer offer);
}
