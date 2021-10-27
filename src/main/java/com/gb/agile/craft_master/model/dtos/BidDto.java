package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.model.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class BidDto {

    private Long id;
    private ExecutorDto executor;
    private Long dateBeg;
    private Long dateEnd;
    private BigDecimal price;
    private Float rating;

    public BidDto(Bid bid) {
        this.id = bid.getId();
        this.executor = new ExecutorDto(bid.getUser());
        this.price = bid.getPrice();
        this.dateBeg = bid.getDateBeg().toInstant().toEpochMilli();
        this.dateEnd = bid.getDateEnd().toInstant().toEpochMilli();
        this.rating = bid.getRating();
    }
}
