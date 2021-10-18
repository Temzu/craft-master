package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.User;
import lombok.Data;

@Data
public class ExecutorDto {

  private Long id;
  private String name;
  private Float rating;

  public ExecutorDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.rating = user.getRating();
  }
}
