package com.example.ddd.service;

import com.example.ddd.domain.Customer;
import com.example.ddd.domain.aggregate.CustomerAggregate;
import com.example.ddd.exception.ConflictException;
import com.example.ddd.exception.NotFoundException;
import com.example.ddd.repository.AddressRepository;
import com.example.ddd.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final AddressRepository addressRepository;

  public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
    this.customerRepository = customerRepository;
    this.addressRepository = addressRepository;
  }

  public CustomerAggregate getOneById(UUID id) {
    final var customer = this.customerRepository.getOneById(id).orElseThrow(() -> new NotFoundException("Customer Not Found by id"));
    return this.getAggregate(customer);
  }

  public CustomerAggregate getOneByCpf(String cpf) {
    final var customer = this.customerRepository.getOneByCpf(cpf).orElseThrow(() -> new NotFoundException("Customer Not Found by cpf"));
    return this.getAggregate(customer);
  }

  public CustomerAggregate create(Customer newCustomer) {
    this.customerRepository.getOneById(newCustomer.getId())
        .ifPresent((x) -> {
          throw new ConflictException("Customer Already Exists");
        });

    final var aggregate = this.getAggregate(newCustomer);

    aggregate.validate();

    this.customerRepository.saveOrUpdateOneById(aggregate.getCustomer());

    return aggregate;
  }

  public CustomerAggregate updateById(UUID id, Customer newCustomer) {
    final var existingCustomer = this.customerRepository.getOneById(id).orElseThrow(() -> new NotFoundException("Customer Not Found by id"));

    existingCustomer.setCpf(newCustomer.getCpf());
    existingCustomer.setName(newCustomer.getName());

    final var aggregate = this.getAggregate(existingCustomer);

    aggregate.validate();

    this.customerRepository.saveOrUpdateOneById(aggregate.getCustomer());

    return aggregate;
  }

  private CustomerAggregate getAggregate(Customer customer) {
    final var addresses = this.addressRepository.getAllByCustomerId(customer.getId());
    return new CustomerAggregate(customer, addresses);
  }
}
