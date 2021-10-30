package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.config.JwtProvider;
import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.model.dtos.BidUserDto;
import com.gb.agile.craft_master.model.entities.Offer;
import com.gb.agile.craft_master.services.BidService;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.repositories.BidRepository;
import com.gb.agile.craft_master.services.OfferService;
import com.gb.agile.craft_master.services.UserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ModelMapper modelMapper;
    private final OfferService offerService;
    private final UserService userService;

    @Override
    public List<BidDto> getAll() {
        return bidRepository.findAll()
                .stream()
                .map(bid -> (modelMapper.map(bid, BidDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public Bid getById(Long id) {
        checkId(id);
        return bidRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Bid.class, id));
    }

    @Override
    public void deleteById(Long id) {
        checkId(id);
        try {
            bidRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Bid.class, id);
        }
    }

    @Override
    public Bid saveOrUpdate(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public List<BidDto> getByOfferId(Long offerId) {
        return bidRepository
                .getByOfferId(offerId)
                .stream().map(bid -> (modelMapper.map(bid, BidDto.class)))
                .collect(Collectors.toList());
    }

    private void checkId(Long id) {
        if (id <= 0) throw new EntityBadIdException(Bid.class, id);
    }

    @Override
    @Transactional
    public BidDto createByOfferAndUser(Long offerId, String userLogin) {
        Offer offer = offerService.getOfferById(offerId);
        Bid bid = new Bid();
        bid.setOffer(offer);
        bid.setPrice(offer.getPrice());  // пока что цену предложения ставим, как в заявке
        bid.setUser(userService.getByLogin(userLogin));
        bidRepository.save(bid);
        return modelMapper.map(bid, BidDto.class);
    }

    @Override
    public List<BidUserDto> getUserOfferBids() {
        return bidRepository.getUserOfferBids(userService.getUserById(JwtProvider.getUserId()))
                .stream()
                .map(BidUserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BidDto acceptBid(Long bidId) {
        BidDto bidDto = null;
        Bid bid = bidRepository.getById(bidId);
        if (Objects.equals(bid.getOffer().getCreator().getId(), JwtProvider.getUserId())) {
            Offer offer = bid.getOffer();
            offer.setAcceptedBid(bid);
            offerService.saveOrUpdate(offer);
            bidDto = new BidDto(bid);
        }
        return bidDto;
    }
}
