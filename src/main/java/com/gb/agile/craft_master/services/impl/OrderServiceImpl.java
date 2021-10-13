package com.gb.agile.craft_master.services.impl;

import com.gb.agile.craft_master.exceptions.entityexceptions.EntityBadIdException;
import com.gb.agile.craft_master.exceptions.entityexceptions.EntityNotFoundException;
import com.gb.agile.craft_master.model.entities.Order;
import com.gb.agile.craft_master.repositories.OrderRepository;
import com.gb.agile.craft_master.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order getOrderById(Long id) {
    checkId(id);
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(Order.class, id));
  }

  @Override
  public void deleteOrderById(Long id) {
    checkId(id);
    try {
      orderRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(Order.class, id);
    }
  }

  @Override
  public Order saveOrUpdate(Order order) {
    return orderRepository.save(order);
  }

  private void checkId(Long id) {
    if (id <= 0) throw new EntityBadIdException(Order.class, id);
  }
}
