package com.example.ddd.domain.aggregate;

import com.example.ddd.domain.*;
import com.example.ddd.domain.base.AggregateRoot;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderAggregate extends AggregateRoot {
  private Order order;
  private Customer customer;
  private Address address;
  private List<CustomOrderItem> items;

  public OrderAggregate(Order order, List<OrderItem> orderItems, List<Product> products, Customer customer, Address address) {
    this.order = order;
    this.items = orderItems.stream().map(x -> new CustomOrderItem(
        x, products.stream().filter(y -> y.getId().equals(x.getProductId())).findFirst().orElse(null)
    )).collect(Collectors.toList());
    this.customer = customer;
    this.address = address;
  }

  public Order getOrder() {
    return this.order;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public Address getAddress() {
    return this.address;
  }

  public List<CustomOrderItem> getItems() {
    return this.items;
  }

  // validations

  @JsonIgnore
  public boolean hasProducts() {
    return this.items != null && !this.items.isEmpty();
  }

  @JsonIgnore
  public boolean isOlderSale() {
    return this.order != null && this.order.getDate() != null && this.order.getDate().getYear() < 2000;
  }

  @JsonIgnore
  public boolean isBigSale() {
    return this.order != null && this.order.getTotalValue() != null && this.order.getTotalValue().compareTo(new BigDecimal(1000)) > 0;
  }

  // other classes

  private static class CustomOrderItem {
    private Long orderId;
    private Product product;
    private Integer quantity;
    private BigDecimal value;

    public CustomOrderItem(OrderItem orderItem, Product product) {
      this.orderId = orderItem.getOrderId();
      this.product = product;
      this.quantity = orderItem.getQuantity();
      this.value = orderItem.getValue();
    }

    public Long getOrderId() {
      return this.orderId;
    }

    public Product getProduct() {
      return this.product;
    }

    public Integer getQuantity() {
      return this.quantity;
    }

    public BigDecimal getValue() {
      return this.value;
    }
  }
}
