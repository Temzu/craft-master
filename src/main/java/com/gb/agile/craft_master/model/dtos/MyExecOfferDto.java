package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.model.entities.Offer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class MyExecOfferDto {

    private Long id;
    private String title;
    private String description;
    private String occupationName;
    private ZonedDateTime createdAt;
    private Integer status;
    private CustomerDto customer;
    private GetBidDto acceptedBid;

    public MyExecOfferDto(Offer offer) {
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.description = offer.getDescription();
        this.occupationName = offer.getOccupation().getName();
        this.createdAt = offer.getCreatedAt();
        this.status = offer.getOfferStatusValue();
        this.customer = new CustomerDto(offer.getCreator());
        Bid acceptedBid = offer.getAcceptedBid();
        if (acceptedBid != null)
            this.acceptedBid = new GetBidDto(acceptedBid);
    }

}
