package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.enums.StatusCode;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.StatusDto;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.core.interfaces.OfferService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

  @GetMapping
  public List<Offer> getAllOffers() {
    return offerService.getAllOffers();
  }

  @GetMapping("/{id}")
  public Offer getOfferById(@PathVariable Long id) {
    return offerService.getOfferById(id);
  }

  @PostMapping
  public Offer updateOffer(@RequestBody OfferDto offerDto) {
    return offerService.saveOrUpdate(offerDto, JwtProvider.getUserId());
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping
  public StatusDto saveOffer(@RequestBody OfferDto offerDto) {
    offerDto.setId(null);
    offerService.saveOrUpdate(offerDto, JwtProvider.getUserId());
    //ToDo: добавить проверки, если нужны(на размер текста, может), и вернуть соответствующий статус
    return new StatusDto(StatusCode.STATUS_OK);
  }

  @DeleteMapping("/{id}")
  public void deleteOfferById(@PathVariable Long id) {
    offerService.deleteOfferById(id);
  }
}
