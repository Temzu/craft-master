package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.model.entities.Offer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.gb.agile.craft_master.model.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OfferDto {

    private Long id;
    private String title;
    private String description;
    private Long occupationId;
    private Integer offerStatusValue;
    private OfferStatus offerStatus;
    private ZonedDateTime createdAt;
    private User creator;
    private BigDecimal price;
    private Long acceptedBidId;

    public OfferDto(Offer offer) {
        Bid acceptedBid = offer.getAcceptedBid();
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.description = offer.getDescription();
        this.occupationId = offer.getOccupation().getId();
        this.offerStatusValue = offer.getOfferStatusValue();
        this.offerStatus = OfferStatus.of(offerStatusValue);
        this.createdAt = offer.getCreatedAt();
        this.creator = offer.getCreator();
        this.price = offer.getPrice();
        if (acceptedBid != null)
            this.acceptedBidId = acceptedBid.getId();
    }

    public OfferDto(SaveOfferDto saveOfferDto) {
        this.title = saveOfferDto.getTitle();
        this.description = saveOfferDto.getDescription();
        this.occupationId = saveOfferDto.getOccupationId();
        if (saveOfferDto.getPrice() != null) {
            this.price = saveOfferDto.getPrice();
        } else {
            this.price = BigDecimal.ZERO;
        }
    }

    public OfferDto(UpdateOfferDto updateOfferDto) {
        this.id = updateOfferDto.getId();
        this.title = updateOfferDto.getTitle();
        this.description = updateOfferDto.getDescription();
        this.occupationId = updateOfferDto.getOccupationId();
    }
}
