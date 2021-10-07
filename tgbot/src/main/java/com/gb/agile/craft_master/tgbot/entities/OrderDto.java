package com.gb.agile.craft_master.tgbot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
//  private Long id;
  private String name;

  @Override
  public String toString() {
    return String.format("[%s]",name);
  }
}
