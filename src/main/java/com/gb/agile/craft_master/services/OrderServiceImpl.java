package com.gb.agile.craft_master.services;

import com.gb.agile.craft_master.core.interfaces.OrderService;
import com.gb.agile.craft_master.exceptions.OrderException;
import com.gb.agile.craft_master.model.entities.Order;
import com.gb.agile.craft_master.repositories.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
    return orderRepository.findById(id).orElseThrow(() -> OrderException.orderNotFound(id));
  }

  @Override
  public void deleteOrderById(Long id) {
    checkId(id);
    try {
      orderRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw OrderException.orderNotFound(id);
    }
  }

  @Override
  public Order saveOrUpdate(Order order) {
    return orderRepository.save(order);
  }

  private void checkId(Long id) {
    if (id <= 0) throw OrderException.badOrderId(id);
  }
}
