package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

@Data
public class SaveOfferDto {

  private String title;
  private String description;
  private Long occupationId;
}