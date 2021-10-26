package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.model.dtos.BidUserDto;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.services.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @GetMapping()
    public List<Bid> getAllBids() {
        return bidService.getAll();
    }

    @GetMapping("/{id}")
    public Bid getOrderById(@PathVariable Long id) {
        return bidService.getById(id);
    }

    @PostMapping
    public Bid updateOrder(@RequestBody Bid bid) {
        return bidService.saveOrUpdate(bid);
    }

    @PutMapping
    public Bid saveOrder(@RequestBody Bid bid) {
        return bidService.saveOrUpdate(bid);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id) {
        bidService.deleteById(id);
    }

    @GetMapping("/byoffer/{id}")
    public List<BidDto> getByOfferId(@PathVariable Long id) {
        return bidService.getByOfferId(id);
    }

    @GetMapping("/create")
    public BidDto createByOfferAndUser(
            @RequestParam(name = "offerid") String offerId,
            @RequestParam(name = "userlogin")  String userLogin) {
        return bidService.createByOfferAndUser(Long.valueOf(offerId), userLogin);
    }

    @GetMapping("/userofferbids")
    public List<BidUserDto> getUserOfferBids() {
        return bidService.getUserOfferBids();
    }
}
