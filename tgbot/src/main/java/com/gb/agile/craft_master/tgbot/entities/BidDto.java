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
public class BidDto {
//  private Long id;
  @JsonProperty("title")
  private String title;
  @JsonProperty("description")
  private String description;
  @JsonProperty("price")
  private BigDecimal price;
  @Override
  public String toString() {
    return String.format("%s[%s]:%.2f",title,description,price);
  }
}
