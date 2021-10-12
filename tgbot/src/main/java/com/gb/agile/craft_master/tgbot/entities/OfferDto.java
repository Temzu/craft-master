package com.gb.agile.craft_master.tgbot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
  @JsonProperty("id")
  private long id;
  @JsonProperty("title")
  private String title;
//  @JsonProperty("description")
//  private String description;
//  private UserDto user;
//  private ServiceDto service;
  @Override
  public String toString() {
    return String.format("[%s]",title);
  }
}
