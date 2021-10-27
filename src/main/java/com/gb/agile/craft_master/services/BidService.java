package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.dtos.AddBidDto;
import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.model.dtos.SetRatingDto;

import java.util.List;

public interface BidService {

    void addBid(AddBidDto addBidDto);

    void acceptBid(Long bidId);

    List<BidDto> getOfferBids(Long offerId);

    void setRating(SetRatingDto setRatingDto);
}
