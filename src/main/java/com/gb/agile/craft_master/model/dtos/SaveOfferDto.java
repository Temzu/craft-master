package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveOfferDto {

  private String title;
  private String description;
  private Long occupationId;
  private BigDecimal price;
}
