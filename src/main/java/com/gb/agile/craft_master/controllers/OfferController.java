package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.exceptions.InvalidPageException;
import com.gb.agile.craft_master.model.dtos.MyOfferDto;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferExecutorDto;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.repositories.specifications.OfferSpecifications;
import com.gb.agile.craft_master.services.OfferService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    return offerService.getAllOffers(
        OfferSpecifications.build(params), page - 1, size, sort, dir.toUpperCase());
  }

  @GetMapping("/nonpaged")
  public List<Offer> getAllOffers() {
    return offerService.getAllOffersNonPaged();
  }

  @GetMapping("/{id}")
  public Offer getOfferById(@PathVariable Long id) {
    return offerService.getOfferById(id);
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<HttpStatus> saveOffer(@RequestBody OfferDto offerDto) {
    offerDto.setId(null);
    offerService.saveOrUpdate(offerDto);
    // ToDo: добавить проверки, если нужны(на размер текста, может), и вернуть соответствующий
    // статус
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  @PreAuthorize("isAuthenticated()")
  public OfferDto updateOffer(@RequestBody OfferDto offerDto) {
    return new OfferDto(offerService.saveOrUpdate(offerDto));
  }

  @PutMapping("/add_executor")
  @PreAuthorize("isAuthenticated()")
  public MyOfferDto addExecutorToOffer(@RequestBody UpdateOfferExecutorDto offerDto) {
    return offerService.updateExecutor(offerDto);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteOfferById(@PathVariable Long id) {
    offerService.deleteOfferById(id);
  }
}
