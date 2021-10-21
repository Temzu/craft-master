package com.gb.agile.craft_master.controllers;

import com.gb.agile.craft_master.model.entities.Bid;
import com.gb.agile.craft_master.services.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final BidService bidService;

  @GetMapping()
  public List<Bid> getAllOrders() {
    return bidService.getAll();
  }

  @GetMapping("/{id}")
  public Bid getOrderById(@PathVariable Long id) {
    return bidService.getById(id);
  }

  @PostMapping
  public Bid updateOrder(@RequestBody Bid bid) {
    return bidService.saveOrUpdate(bid);
  }

  @PutMapping
  public Bid saveOrder(@RequestBody Bid bid) {
    return bidService.saveOrUpdate(bid);
  }

  @DeleteMapping("/{id}")
  public void deleteOrderById(@PathVariable Long id) {
    bidService.deleteById(id);
  }
}
