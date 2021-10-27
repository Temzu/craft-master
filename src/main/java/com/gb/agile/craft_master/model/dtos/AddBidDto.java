package com.gb.agile.craft_master.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddBidDto {
    private Long offerId;
    private Long dateBeg;
    private Long dateEnd;
    private BigDecimal price;
}
