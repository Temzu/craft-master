package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.core.enums.StatusCode;
import com.gb.agile.craft_master.model.dtos.AddBidDto;
import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.model.dtos.SetRatingDto;
import com.gb.agile.craft_master.model.dtos.StatusDto;
import com.gb.agile.craft_master.services.BidService;
import com.gb.agile.craft_master.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @GetMapping("/offer_bids/{offerId}")
    public List<BidDto> getOfferBids(@PathVariable Long offerId){
        return bidService.getOfferBids(offerId);
    }

    @PostMapping("/add_bid")
    public StatusDto addBid(@RequestBody AddBidDto addBidDto) {
        bidService.addBid(addBidDto);
        return new StatusDto(StatusCode.STATUS_OK);
    }

    @PostMapping("/accept_bid/{id}")
    public StatusDto acceptBid(@PathVariable Long id) {
        bidService.acceptBid(id);
        return new StatusDto(StatusCode.STATUS_OK);
    }

    @PostMapping("/set_rating")
    public StatusDto setRatingToBid(@RequestBody SetRatingDto setRatingDto) {
        bidService.setRating(setRatingDto);
        return new StatusDto(1);
    }

}