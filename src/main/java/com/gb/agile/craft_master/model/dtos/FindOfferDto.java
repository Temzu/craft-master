package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Offer;
import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class FindOfferDto {

  private Long id;
  private String title;
  private String description;
  private String occupationName;
  private ZonedDateTime createdAt;
  private CustomerDto customer;

  public FindOfferDto(Offer offer) {
    this.id = offer.getId();
    this.title = offer.getTitle();
    this.description = offer.getDescription();
    this.occupationName = offer.getOccupation().getName();
    this.createdAt = offer.getCreatedAt();
    this.customer = new CustomerDto(offer.getCreator());
  }
}
