package com.gb.agile.craft_master.model.dto;

import com.gb.agile.craft_master.model.User;
import lombok.Data;

@Data
public class UserInfoDto {
    private String login;
    private String name;

    public UserInfoDto(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
    }
}
