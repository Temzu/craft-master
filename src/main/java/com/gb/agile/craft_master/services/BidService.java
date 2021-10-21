package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.entities.Bid;

import java.util.List;

public interface BidService {

  List<Bid> getAll();

  Bid getById(Long id);

  void deleteById(Long id);

  Bid saveOrUpdate(Bid offer);
}
