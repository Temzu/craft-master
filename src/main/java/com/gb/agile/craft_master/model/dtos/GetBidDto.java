package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Bid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class GetBidDto {

    private Long id;
    private ExecutorDto executor;
    private ZonedDateTime begDate;
    private ZonedDateTime endDate;
    private BigDecimal price;

    public GetBidDto (Bid bid) {
        this.id = bid.getId();
        this.executor = new ExecutorDto(bid.getUser());
        this.begDate = bid.getDateBeg();
        this.endDate = bid.getDateEnd();
        this.price = bid.getPrice();
    }

}
