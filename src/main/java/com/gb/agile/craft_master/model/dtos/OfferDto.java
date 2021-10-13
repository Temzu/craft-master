package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.model.entities.Offer;
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

    public OfferDto(Offer offer) {
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.description = offer.getDescription();
        this.occupationId = offer.getOccupation().getId();
        this.offerStatusValue = offer.getOfferStatusValue();
        this.offerStatus = OfferStatus.of(offerStatusValue);
    }
}
