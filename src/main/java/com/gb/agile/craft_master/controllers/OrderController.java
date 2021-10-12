package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.core.interfaces.OrderService;
import com.gb.agile.craft_master.model.entities.Bid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping()
  public List<Bid> getAllOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("/{id}")
  public Bid getOrderById(@PathVariable Long id) {
    return orderService.getOrderById(id);
  }

  @PostMapping
  public Bid updateOrder(@RequestBody Bid bid) {
    return orderService.saveOrUpdate(bid);
  }

  @PutMapping
  public Bid saveOrder(@RequestBody Bid bid) {
    return orderService.saveOrUpdate(bid);
  }

  @DeleteMapping("/{id}")
  public void deleteOrderById(@PathVariable Long id) {
    orderService.deleteOrderById(id);
  }
}
