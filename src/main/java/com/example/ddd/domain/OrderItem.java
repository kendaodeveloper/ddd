package com.example.ddd.domain;

import com.example.ddd.domain.base.ValueObject;

import java.math.BigDecimal;

public class OrderItem extends ValueObject {
  private Long orderId;
  private Long productId;
  private Integer quantity;
  private BigDecimal value;

  public OrderItem(Long orderId, Long productId, Integer quantity, BigDecimal value) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.value = value;
  }

  public Long getOrderId() {
    return this.orderId;
  }

  public Long getProductId() {
    return this.productId;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public BigDecimal getValue() {
    return this.value;
  }
}
