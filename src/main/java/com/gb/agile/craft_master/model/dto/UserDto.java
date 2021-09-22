package com.gb.agile.craft_master.model.dto;

import com.gb.agile.craft_master.model.Role;
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
}
