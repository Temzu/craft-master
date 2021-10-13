package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Offer;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MyOfferDto {

  private Long id;
  private String title;
  private String description;
  private String occupationName;
  private Integer status;
  private LocalDateTime createdAt;
  private ExecutorDto executor;

  public MyOfferDto(Offer offer) {
    this.id = offer.getId();
    this.title = offer.getTitle();
    this.description = offer.getDescription();
    this.occupationName = offer.getOccupation().getName();
    this.status = offer.getOfferStatusValue();
    this.createdAt = offer.getCreatedAt();
    if (offer.getExecutor() != null) {
      this.executor = new ExecutorDto(offer.getExecutor());
    }
  }
}
