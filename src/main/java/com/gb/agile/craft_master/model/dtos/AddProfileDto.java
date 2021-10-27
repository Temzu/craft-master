package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

@Data
public class AddProfileDto {
    private Long occupationId;
    private Integer workExp;
    private String description;
}
