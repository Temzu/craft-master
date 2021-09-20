package com.gb.agile.craft_master.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDto {
    private Integer userId;
    private String code;
    private String value;
    private String name;
}
