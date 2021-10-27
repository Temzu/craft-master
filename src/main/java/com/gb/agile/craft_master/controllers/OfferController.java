package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.core.enums.StatusCode;
import com.gb.agile.craft_master.exceptions.InvalidPageException;
import com.gb.agile.craft_master.model.dtos.*;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.repositories.specifications.OfferSpecifications;
import com.gb.agile.craft_master.services.OfferService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
public class OfferController {

  private final OfferService offerService;

//  private static List<MyExecOfferDto> myExecTaskDtos = new ArrayList<>(Arrays.asList(
//          new MyExecOfferDto(100L, "task 1", "task 1111", "Ремонт мебели", "2021-10-16T15:00:48.731339",
//                  OfferStatus.ASSIGNED.getCode(), new CustomerDto(2L, "Иванов Иван", 4f),
//                  new GetBidDto(25L, null, BigDecimal.valueOf(3550.00), new Date().getTime(), new Date().getTime())),
//          new MyExecOfferDto(101L, "task 2", "task 2222", "Ремонт мебели", "2021-10-16T16:00:48.731339",
//                  OfferStatus.CLOSED.getCode(), new CustomerDto(2L, "Иванов Иван", 4f),
//                  new GetBidDto(26L, null, BigDecimal.valueOf(450.00), new Date().getTime(), new Date().getTime())),
//          new MyExecOfferDto(102L, "task 3", "task 2222", "Ремонт мебели", "2021-10-16T16:00:48.731339",
//                  OfferStatus.ASSIGNED.getCode(), new CustomerDto(2L, "Иванов Иван", 4f),
//                  new GetBidDto(27L, null, BigDecimal.valueOf(5556.00), new Date().getTime(), new Date().getTime()))
//  ));

  @GetMapping
  public Page<OfferDto> getAll(
      @RequestParam MultiValueMap<String, String> params,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "id") String[] sort,
      @RequestParam(defaultValue = "ASC") String dir) {
    checkPage(page);
    return offerService.getAllOffers(
        OfferSpecifications.build(params), page - 1, size, sort, dir.toUpperCase());
  }

  @GetMapping("/nonpaged")
  public List<Offer> getAllOffers() {
    return offerService.getAllOffersNonPaged();
  }

  @GetMapping("/my_offers")
  public List<MyOfferDto> getAllMyOffers(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "DESC") String dir,
      @RequestParam(defaultValue = "createdAt") String[] sort
  ) {
    return offerService.getAllOffersByCurrentUser(page - 1, size, dir, sort);
  }

  @GetMapping("/suitable")
  public Page<FindOfferDto> getAllOffersForMe(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size
  ) {
    checkPage(page);
    return offerService.getAllOffersForCurrentUser(page - 1, size);
  }

//  @GetMapping("/get_assigned_to_me")
//  public List<MyExecOfferDto> getAllAssignedToMe() {
//    return myExecTaskDtos;
//  }
//
//  @PostMapping("/set_assigned_to_me")
//  public StatusDto setAssignedToMe(@RequestBody MyExecOfferDto myExecTaskDto) {
//    myExecTaskDtos.add(myExecTaskDto);
//    return new StatusDto(1);
//  }

  @GetMapping("/{id}")
  public Offer getOfferById(@PathVariable Long id) {
    return offerService.getOfferById(id);
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public StatusDto saveOffer(@RequestBody SaveOfferDto saveOfferDto) {
    OfferDto offerDto = new OfferDto(saveOfferDto);
    offerService.saveOrUpdate(offerDto);
    // ToDo: добавить проверки, если нужны(на размер текста, может), и вернуть соответствующий
    // статус
    return new StatusDto(1);
  }

  @PutMapping
  @PreAuthorize("isAuthenticated()")
  public OfferDto updateOffer(@RequestBody UpdateOfferDto updateOfferDto) {
    return new OfferDto(offerService.saveOrUpdate(new OfferDto(updateOfferDto)));
  }

//  @PutMapping("/add_executor")
//  @PreAuthorize("isAuthenticated()")
//  public MyOfferDto addExecutorToOffer(@RequestBody UpdateOfferExecutorDto offerExecutorDto) {
//    return offerService.updateExecutor(offerExecutorDto);
//  }

  @PutMapping("/update_status")
  @PreAuthorize("isAuthenticated()")
  public StatusDto updateStatus(@RequestBody UpdateOfferStatusDto offerStatusDto) {
    offerService.updateStatus(offerStatusDto);
    return new StatusDto(1);
  }

  @PutMapping("/set_status_done")
  @PreAuthorize("isAuthenticated()")
  public StatusDto setStatusDone(@RequestBody UpdateOfferStatusDto offerStatusDto) {
    offerService.setStatusDone(offerStatusDto);
    return new StatusDto(StatusCode.STATUS_OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteOfferById(@PathVariable Long id) {
    offerService.deleteOfferById(id);
  }

  private void checkPage(Integer page) {
    if (page < 1) {
      throw new InvalidPageException(page.toString());
    }
  }

  @GetMapping("/accepteduserbids")
  public List<MyExecOfferDto> getAcceptedUserBidsOffers() {
    return offerService.getAllAcceptedBidsByCurrentUserMyExecOfferDto();
  }
}
