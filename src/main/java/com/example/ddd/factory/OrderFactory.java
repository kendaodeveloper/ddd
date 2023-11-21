package com.example.ddd.factory;

import com.example.ddd.domain.*;
import com.example.ddd.domain.aggregate.OrderAggregate;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFactory {
  private OrderFactory() {
  }

  public static OrderAggregate createNewOrder(Order order, List<OrderItem> orderItems, List<Product> products, Customer customer, Address address) {
    final var items = orderItems.stream().map(x -> new OrderAggregate.CustomOrderItem(
        x, products.stream().filter(y -> y.getId().equals(x.getProductId())).findFirst().orElse(null)
    )).collect(Collectors.toList());

    return new OrderAggregate(order, items, customer, address);
  }
}
