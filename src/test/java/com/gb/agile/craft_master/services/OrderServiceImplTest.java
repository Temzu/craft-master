package com.gb.agile.craft_master.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gb.agile.craft_master.core.interfaces.OrderService;
import com.gb.agile.craft_master.exceptions.OrderException;
import com.gb.agile.craft_master.model.entities.Order;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest
class OrderServiceImplTest {

  @Autowired
  private OrderService orderService;

  @Test
  void getAllOrders() {
    List<Order> allOrders = orderService.getAllOrders();
    assertFalse(allOrders.isEmpty());
  }

  @CsvSource(value = {"2", "3"})
  @ParameterizedTest
  void getOrderById_true(Long id) {
    Order order = orderService.getOrderById(id);
    assertNotNull(order);
  }

  @CsvSource(value = {"0", "-1", "1000"})
  @ParameterizedTest
  void getOrderById_false(Long id) {
    assertThrows(OrderException.class, () -> orderService.getOrderById(id));
  }

  @CsvSource(value = {"1", "4"})
  @ParameterizedTest
  void deleteOrderById_true(Long id) {
    orderService.deleteOrderById(id);
  }

  @CsvSource(value = {"0", "-1", "1000"})
  @ParameterizedTest
  void deleteOrderById_false(Long id) {
    assertThrows(OrderException.class, () -> orderService.deleteOrderById(id));
  }

  @CsvSource(value = {"1", "5", "-1", "100"})
  @NullSource
  @ParameterizedTest
  void saveOrUpdate_true(Long id) {
    Order order = new Order();
    order.setId(id);
    order.setName("Order-123");

    orderService.saveOrUpdate(order);
  }

  @Test
  void saveOrUpdate_false() {
    assertThrows(InvalidDataAccessApiUsageException.class, () -> orderService.saveOrUpdate(null));
  }
}