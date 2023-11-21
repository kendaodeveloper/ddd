package com.example.ddd.domain.aggregate;

import com.example.ddd.domain.Address;
import com.example.ddd.domain.Customer;
import com.example.ddd.domain.base.AggregateRoot;
import com.example.ddd.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CustomerAggregate extends AggregateRoot {
  private Customer customer;
  private List<Address> addresses;

  public CustomerAggregate(Customer customer, List<Address> addresses) {
    this.customer = customer;
    this.addresses = addresses;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public List<Address> getAddresses() {
    return this.addresses;
  }

  // validations

  @JsonIgnore
  public void validate() {
    if (!hasValidId()) {
      throw new BadRequestException("Invalid Customer ID");
    }

    if (!hasValidCpf()) {
      throw new BadRequestException("Invalid Customer Cpf");
    }
  }

  @JsonIgnore
  public boolean hasAddresses() {
    return this.addresses != null && !this.addresses.isEmpty();
  }

  @JsonIgnore
  public boolean hasValidId() {
    return this.customer != null && this.customer.getId() != null;
  }

  @JsonIgnore
  public boolean hasValidCpf() {
    return this.customer != null && this.customer.getCpf() != null && this.customer.getCpf().length() == 11;
  }
}
