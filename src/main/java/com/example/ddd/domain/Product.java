package com.example.ddd.domain;

import com.example.ddd.domain.base.Entity;

import java.math.BigDecimal;

public class Product extends Entity<Long> {
  private String name;
  private Integer stock;
  private BigDecimal value;

  public Product(Long id, String name, Integer stock, BigDecimal value) {
    super(id);
    this.name = name;
    this.stock = stock;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public Integer getStock() {
    return this.stock;
  }

  public BigDecimal getValue() {
    return this.value;
  }
}
