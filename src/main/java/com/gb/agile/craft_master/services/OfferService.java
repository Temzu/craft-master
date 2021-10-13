package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.dtos.MyOfferDto;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferExecutorDto;
import com.gb.agile.craft_master.model.entities.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OfferService {

  List<Offer> getAllOffersNonPaged();

  Offer getOfferById(Long id);

  void deleteOfferById(Long id);

  Offer saveOrUpdate(OfferDto offerDto);

  Page<OfferDto> getAllOffers(
      Specification<Offer> spec, Integer page, Integer size, String[] sort, String dir);

  MyOfferDto updateExecutor(UpdateOfferExecutorDto updateOfferExecutorDto);
}
