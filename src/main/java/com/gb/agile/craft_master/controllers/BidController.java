package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.exceptions.InvalidPageException;
import com.gb.agile.craft_master.model.dtos.FindOfferDto;
import com.gb.agile.craft_master.model.dtos.MyOfferDto;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.dtos.SaveOfferDto;
import com.gb.agile.craft_master.model.dtos.StatusDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferExecutorDto;
import com.gb.agile.craft_master.model.dtos.UpdateOfferStatusDto;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.repositories.specifications.OfferSpecifications;
import com.gb.agile.craft_master.services.BidService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
public class BidController {

  private final BidService bidService;

  @GetMapping
  public Page<OfferDto> getAll(
      @RequestParam MultiValueMap<String, String> params,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "id") String[] sort,
      @RequestParam(defaultValue = "ASC") String dir) {
    checkPage(page);
    return bidService.getAllOffers(
        OfferSpecifications.build(params), page - 1, size, sort, dir.toUpperCase());
  }

  @GetMapping("/nonpaged")
  public List<Offer> getAllOffers() {
    return bidService.getAllOffersNonPaged();
  }

  @GetMapping("/my_offers")
  public List<MyOfferDto> getAllMyOffers(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "DESC") String dir,
      @RequestParam(defaultValue = "createdAt") String[] sort
  ) {
    return bidService.getAllOffersByCurrentUser(page - 1, size, dir, sort);
  }

  @GetMapping("/suitable")
  public Page<FindOfferDto> getAllOffersForMe(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size
  ) {
    checkPage(page);
    return bidService.getAllOffersForCurrentUser(page - 1, size);
  }

  @GetMapping("/{id}")
  public Offer getOfferById(@PathVariable Long id) {
    return bidService.getOfferById(id);
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public StatusDto saveOffer(@RequestBody SaveOfferDto saveOfferDto) {
    OfferDto offerDto = new OfferDto(saveOfferDto);
    bidService.saveOrUpdate(offerDto);
    // ToDo: добавить проверки, если нужны(на размер текста, может), и вернуть соответствующий
    // статус
    return new StatusDto(1);
  }

  @PutMapping
  @PreAuthorize("isAuthenticated()")
  public OfferDto updateOffer(@RequestBody UpdateOfferDto updateOfferDto) {
    return new OfferDto(bidService.saveOrUpdate(new OfferDto(updateOfferDto)));
  }

  @PutMapping("/add_executor")
  @PreAuthorize("isAuthenticated()")
  public MyOfferDto addExecutorToOffer(@RequestBody UpdateOfferExecutorDto offerExecutorDto) {
    return bidService.updateExecutor(offerExecutorDto);
  }

  @PutMapping("/update_status")
  @PreAuthorize("isAuthenticated()")
  public StatusDto updateStatus(@RequestBody UpdateOfferStatusDto offerStatusDto) {
    bidService.updateStatus(offerStatusDto);
    return new StatusDto(1);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteOfferById(@PathVariable Long id) {
    bidService.deleteOfferById(id);
  }

  private void checkPage(Integer page) {
    if (page < 1) {
      throw new InvalidPageException(page.toString());
    }
  }
}