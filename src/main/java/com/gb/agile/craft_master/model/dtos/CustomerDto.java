package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {

  private Long id;
  private String name;
  private Float rating;

  public CustomerDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.rating = user.getRating();
  }
}
