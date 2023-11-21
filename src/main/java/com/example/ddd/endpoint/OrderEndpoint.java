package com.example.ddd.endpoint;

import com.example.ddd.domain.aggregate.OrderAggregate;
import com.example.ddd.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderEndpoint {
  private final OrderService service;

  public OrderEndpoint(OrderService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public OrderAggregate getOrderById(@PathVariable Long id) {
    return service.getOneById(id);
  }
}
