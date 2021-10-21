package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.model.entities.Offer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sun.xml.bind.v2.TODO;
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
    private LocalDateTime createdAt;
    private UserDto creator;
    private BigDecimal price;

    public OfferDto(Offer offer) {
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.description = offer.getDescription();
        this.occupationId = offer.getOccupation().getId();
        this.offerStatusValue = offer.getOfferStatusValue();
        this.offerStatus = OfferStatus.of(offerStatusValue);
        this.createdAt = offer.getCreatedAt();
        // TODO: просмотреть зависимости и исправить
        //this.creator = offer.getCreator();
    }

    public OfferDto(SaveOfferDto saveOfferDto) {
        this.title = saveOfferDto.getTitle();
        this.description = saveOfferDto.getDescription();
        this.occupationId = saveOfferDto.getOccupationId();
        this.creator = saveOfferDto.getUser();
        this.price = saveOfferDto.getPrice();
    }

    public OfferDto(UpdateOfferDto updateOfferDto) {
        this.id = updateOfferDto.getId();
        this.title = updateOfferDto.getTitle();
        this.description = updateOfferDto.getDescription();
        this.occupationId = updateOfferDto.getOccupationId();
    }
}
