package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.services.interfaces.OrderService;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public List<Bid> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Bid getOrderById(Long id) {
    checkId(id);
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Bid.class, id));
  }

  @Override
  public void deleteOrderById(Long id) {
    checkId(id);
    try {
      orderRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(Bid.class, id);
    }
  }

  @Override
  public Bid saveOrUpdate(Bid bid) {
    return orderRepository.save(bid);
  }

  private void checkId(Long id) {
    if (id <= 0) throw new EntityBadIdException(Bid.class, id);
  }
}
