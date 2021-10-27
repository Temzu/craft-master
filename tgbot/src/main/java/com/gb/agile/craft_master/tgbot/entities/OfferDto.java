package com.gb.agile.craft_master.tgbot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

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
  @JsonProperty("description")
  private String description;
  @JsonProperty("creator")
  private UserDto creator;
  @JsonProperty("price")
  private BigDecimal price;
  @JsonProperty("occupation_id")
  private Long occupationId;
  @Override
  public String toString() {
    return String.format("%s(%.2f)",title,price);
  }
}
