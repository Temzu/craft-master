package com.gb.agile.craft_master.model.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String password;
}