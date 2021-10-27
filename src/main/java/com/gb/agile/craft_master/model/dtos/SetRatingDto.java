package com.gb.agile.craft_master.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetRatingDto {
    private Long bidId;
    private Float rating;
}
