package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.exceptions.DataAccessFailedException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.dtos.AddBidDto;
import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.model.dtos.SetRatingDto;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.model.entities.User;
import com.gb.agile.craft_master.services.BidService;
import com.gb.agile.craft_master.repositories.BidRepository;
import com.gb.agile.craft_master.services.OfferService;
import com.gb.agile.craft_master.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final UserService userService;
    private final OfferService offerService;

    @Override
    @Transactional
    public void addBid(AddBidDto addBidDto) {
        User user = userService.getProxyById(JwtProvider.getUserId());
        Offer offer = offerService.getProxyById(addBidDto.getOfferId());
        Bid bid = new Bid(addBidDto);
        bid.setUser(user);
        bid.setOffer(offer);
        bidRepository.save(bid);
    }

    @Override
    @Transactional
    public void acceptBid(Long bidId) {
        Bid bid = bidRepository.findById(bidId).orElseThrow(() -> new EntityNotFoundException(Bid.class, bidId));
        offerService.acceptBid(bid);
    }

    @Override
    public List<BidDto> getOfferBids(Long offerId) {
        offerService.checkAccess(offerId, JwtProvider.getUserId());
        Offer offer = offerService.getProxyById(offerId);
        return bidRepository.findAllByOffer(offer).stream().map(BidDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setRating(SetRatingDto setRatingDto) {
        Bid bid = bidRepository.findById(setRatingDto.getBidId()).orElseThrow(() -> new EntityNotFoundException(Bid.class, setRatingDto.getBidId()));
        if (bid.getRating() != null || !bid.getOffer().getCreator().getId().equals(JwtProvider.getUserId())) {
            throw new DataAccessFailedException(Bid.class);
        }
        bid.setRating(setRatingDto.getRating());
        bidRepository.save(bid);
        userService.updateUserRating(bid.getUser(), bid.getRating());
    }
}
