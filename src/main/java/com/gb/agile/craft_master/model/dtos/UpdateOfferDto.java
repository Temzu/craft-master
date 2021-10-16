package com.gb.agile.craft_master.model.dtos;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class UpdateOfferDto {

  @NonNull
  private Long id;
  private String title;
  private String description;
  private Long occupationId;
}
