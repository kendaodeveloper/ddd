package com.example.ddd.domain;

import com.example.ddd.domain.base.Entity;

import java.util.UUID;

public class Customer extends Entity<UUID> {
  private String cpf;
  private String name;

  public Customer(UUID id, String cpf, String name) {
    super(id);
    this.cpf = cpf;
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
