package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Role;
import com.gb.agile.craft_master.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private String login;
  private String name;
  private String password;
  private Role role;

  public UserDto(User user) {
    this.login = user.getLogin();
    this.name = user.getName();
    this.password = password;
    this.role = role;
  }
}
