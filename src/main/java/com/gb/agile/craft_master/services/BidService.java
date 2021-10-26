package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.dtos.BidDto;
import com.gb.agile.craft_master.model.dtos.BidUserDto;
import com.gb.agile.craft_master.model.dtos.MyExecOfferDto;
import com.gb.agile.craft_master.model.dtos.OfferDto;
import com.gb.agile.craft_master.model.entities.Bid;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BidService {

  List<Bid> getAll();

  Bid getById(Long id);

  void deleteById(Long id);

  Bid saveOrUpdate(Bid bid);

  List<BidDto> getByOfferId(Long id);

  BidDto createByOfferAndUser(Long offerId, String userLogin);

  List<BidUserDto> getUserOfferBids();
}
