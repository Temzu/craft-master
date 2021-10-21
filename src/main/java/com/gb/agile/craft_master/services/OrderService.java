package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.model.entities.Bid;

import java.util.List;

public interface OrderService {

    List<Bid> getAllOrders();

    Bid getOrderById(Long id);

    void deleteOrderById(Long id);

    Bid saveOrUpdate(Bid offer);

    Bid createByOfferAndUser(Long offerId, String userLogin);
}
