package com.example.ddd.domain;

import com.example.ddd.domain.base.Entity;

import java.util.UUID;

public class Address extends Entity<UUID> {
  private UUID customerId;
  private String zipCode;
  private String street;
  private String number;
  private String neighborhood;
  private String complement;
  private String city;
  private String state;

  public Address(UUID id, UUID customerId, String zipCode, String street, String number, String neighborhood, String complement, String city, String state) {
    super(id);
    this.customerId = customerId;
    this.zipCode = zipCode;
    this.street = street;
    this.number = number;
    this.neighborhood = neighborhood;
    this.complement = complement;
    this.city = city;
    this.state = state;
  }

  public UUID getCustomerId() {
    return this.customerId;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public String getStreet() {
    return this.street;
  }

  public String getNumber() {
    return this.number;
  }

  public String getNeighborhood() {
    return this.neighborhood;
  }

  public String getComplement() {
    return this.complement;
  }

  public String getCity() {
    return this.city;
  }

  public String getState() {
    return this.state;
  }
}
