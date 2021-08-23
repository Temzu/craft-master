package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.services.interfaces.OfferService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
public class OfferController {

  private final OfferService offerService;

  @GetMapping()
  public List<Offer> getAllOffers() {
    return offerService.getAllOffers();
  }

  @GetMapping("/{id}")
  public Offer getOfferById(@PathVariable Long id) {
    return offerService.getOfferById(id);
  }

  @PostMapping
  public Offer updateOffer(@RequestBody Offer offer) {
    return offerService.saveOrUpdate(offer);
  }

  @PutMapping
  public Offer saveOffer(@RequestBody Offer offer) {
    offer.setId(null);
    return offerService.saveOrUpdate(offer);
  }

  @DeleteMapping("/{id}")
  public void deleteOfferById(@PathVariable Long id) {
    offerService.deleteOfferById(id);
  }
}
