package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RegistrUserDto {
    private String login;
    private String name;
    private String password;
    private List<CredentialDto> credentialDtos;
}
