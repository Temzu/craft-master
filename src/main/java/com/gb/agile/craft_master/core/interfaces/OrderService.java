package com.gb.agile.craft_master.core.interfaces;

import com.gb.agile.craft_master.model.entities.Order;
import java.util.List;

public interface OrderService {

  List<Order> getAllOrders();

  Order getOrderById(Long id);

  void deleteOrderById(Long id);

  Order saveOrUpdate(Order offer);
}
