package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String password;
}