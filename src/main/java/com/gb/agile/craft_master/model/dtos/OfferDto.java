package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.services.OccupationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfferDto {

    private final OccupationServiceImpl occupationService;

    private Long id;
    private String title;
    private String description;
    private Long occupationId;

}
