package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class BidDto {

    private Long id;
    private String title;
    private String description;
    private Long occupationId;
    private User user;
    private ZonedDateTime begDate;
    private ZonedDateTime endDate;
    private BigDecimal price;
    private Offer offer;

    public BidDto(Bid bid) {
        this.id = bid.getId();
        this.user = bid.getUser();
        this.price = bid.getPrice();
        this.begDate = bid.getDateBeg();
        this.endDate = bid.getDateEnd();
        this.offer = bid.getOffer();
    }
}
