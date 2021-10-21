package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.services.BidService;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.repositories.BidRepository;
import com.gb.agile.craft_master.services.OfferService;
import com.gb.agile.craft_master.services.UserService;
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
  public List<Bid> getAll() {
    return bidRepository.findAll();
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
    Bid bid = new Bid();
    bid.setOfferId(offerId);
    bid.setPrice(offerService.getOfferById(offerId).getPrice());
    bid.setUser(userService.getByLogin(userLogin));
    bidRepository.save(bid);
    return modelMapper.map(bid, BidDto.class);
  }
}
