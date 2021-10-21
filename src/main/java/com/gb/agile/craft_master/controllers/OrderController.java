package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @PostMapping("/byoffer")
  public void createOrderByOfferAndUser(@RequestParam Long offerid, @RequestParam String userlogin) {
    orderService.createByOfferAndUser(offerid, userlogin);
  }
}
