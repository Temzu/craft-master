package com.gb.agile.craft_master.tgbot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
    private String login;
    private String name;
    private String password;
    private RoleDto role;
}
