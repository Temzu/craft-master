package com.gb.agile.craft_master.model.dtos;

import com.gb.agile.craft_master.core.enums.OfferStatus;
import com.gb.agile.craft_master.model.entities.Offer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private Long creatorId;
    private BigDecimal price;

    public OfferDto(Offer offer) {
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.description = offer.getDescription();
        this.occupationId = offer.getOccupation().getId();
        this.offerStatusValue = offer.getOfferStatusValue();
        this.offerStatus = OfferStatus.of(offerStatusValue);
        this.createdAt = offer.getCreatedAt();
        this.creatorId = offer.getCreator().getId();
        this.price = offer.getPrice();
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
