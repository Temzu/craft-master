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
public class BidUserDto {

    private Long id;
    private String title;
    private String description;
    private Long occupationId;
    private ZonedDateTime begDate;
    private ZonedDateTime endDate;
    private BigDecimal price;
    private Long offerId;

    public BidUserDto(Bid bid) {
        this.id = bid.getId();
        this.price = bid.getPrice();
        this.begDate = bid.getDateBeg();
        this.endDate = bid.getDateEnd();
        this.offerId = bid.getOffer().getId();
        this.title = bid.getOffer().getTitle();
        this.description = bid.getOffer().getDescription();
        this.occupationId = bid.getOffer().getOccupation().getId();
    }
}
