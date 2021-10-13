//package com.gb.agile.craft_master.services;
//
//import com.gb.agile.craft_master.services.interfaces.OrderService;
//import com.gb.agile.craft_master.exceptions.entity_exceptions.EntityBadIdException;
//import com.gb.agile.craft_master.model.entities.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.NullSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.InvalidDataAccessApiUsageException;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class OrderServiceImplTest {
//
//  @Autowired
//  private OrderService orderService;
//
//  @Test
//  void getAllOrders() {
//    List<Order> allOrders = orderService.getAllOrders();
//    assertFalse(allOrders.isEmpty());
//  }
//
//  @CsvSource(value = {"2", "3"})
//  @ParameterizedTest
//  void getOrderById_true(Long id) {
//    Order order = orderService.getOrderById(id);
//    assertNotNull(order);
//  }
//
//  @CsvSource(value = {"0", "-1", "1000"})
//  @ParameterizedTest
//  void getOrderById_false(Long id) {
//    assertThrows(EntityBadIdException.class, () -> orderService.getOrderById(id));
//  }
//
//  @CsvSource(value = {"1", "4"})
//  @ParameterizedTest
//  void deleteOrderById_true(Long id) {
//    orderService.deleteOrderById(id);
//  }
//
//  @CsvSource(value = {"0", "-1", "1000"})
//  @ParameterizedTest
//  void deleteOrderById_false(Long id) {
//    assertThrows(EntityBadIdException.class, () -> orderService.deleteOrderById(id));
//  }
//
//  @CsvSource(value = {"1", "5", "-1", "100"})
//  @NullSource
//  @ParameterizedTest
//  void saveOrUpdate_true(Long id) {
//    Order order = new Order();
//    order.setId(id);
//    order.setName("Order-123");
//
//    orderService.saveOrUpdate(order);
//  }
//
//  @Test
//  void saveOrUpdate_false() {
//    assertThrows(InvalidDataAccessApiUsageException.class, () -> orderService.saveOrUpdate(null));
//  }
//}