package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.services.BidService;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.repositories.BidRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

  private final BidRepository bidRepository;
  private final ModelMapper modelMapper;

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
}
