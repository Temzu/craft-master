package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.core.enums.StatusCode;
import com.gb.agile.craft_master.services.interfaces.OfferService;
import com.gb.agile.craft_master.exceptions.InvalidPageException;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.StatusDto;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.repositories.specifications.OfferSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
public class OfferController {

  private final OfferService offerService;

  @GetMapping
  public Page<OfferDto> getAll(
      @RequestParam MultiValueMap<String, String> params,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "id") String[] sort,
      @RequestParam(defaultValue = "ASC") String dir) {
    if (page < 1) throw new InvalidPageException(page.toString());
    System.out.println(dir);
    return offerService.getAllOffers(
        OfferSpecifications.build(params), page - 1, size, sort, dir.toUpperCase());
  }

  @GetMapping("/nonpaged/")
  public List<Offer> getAllOffers() {
    return offerService.getAllOffersNonPaged();
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
    // ToDo: добавить проверки, если нужны(на размер текста, может), и вернуть соответствующий
    // статус
    return new StatusDto(StatusCode.STATUS_OK);
  }

  @DeleteMapping("/{id}")
  public void deleteOfferById(@PathVariable Long id) {
    offerService.deleteOfferById(id);
  }
}
