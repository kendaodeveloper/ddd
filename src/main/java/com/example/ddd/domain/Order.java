package com.example.ddd.domain;

import com.example.ddd.domain.base.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Order extends Entity<Long> {
  private UUID customerId;
  private UUID addressId;
  private BigDecimal totalValue;
  private LocalDate date;

  public Order(Long id, UUID customerId, UUID addressId, BigDecimal totalValue, LocalDate date) {
    super(id);
    this.customerId = customerId;
    this.addressId = addressId;
    this.totalValue = totalValue;
    this.date = date;
  }

  public UUID getCustomerId() {
    return this.customerId;
  }

  public UUID getAddressId() {
    return this.addressId;
  }

  public BigDecimal getTotalValue() {
    return this.totalValue;
  }

  public LocalDate getDate() {
    return this.date;
  }
}
