package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ListAddProfileDto {
    private List<AddProfileDto> profiles;
}
