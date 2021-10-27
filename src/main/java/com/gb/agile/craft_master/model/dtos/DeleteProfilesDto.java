package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DeleteProfilesDto {
    private List<Long> ids;
}
