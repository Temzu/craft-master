package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.entities.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    List<Offer> getAllOffersNonPaged();

    Offer getOfferById(Long id);

    void deleteOfferById(Long id);

    Offer saveOrUpdate(OfferDto offerDto, Integer userId);

    Page<OfferDto> getAllOffers(Specification<Offer> spec, Integer page,
                                Integer size,
                                Optional<String[]> sort);
}
