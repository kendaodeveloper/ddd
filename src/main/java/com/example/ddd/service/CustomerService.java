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
    this.customerRepository.getOneByCpf(newCustomer.getCpf())
        .ifPresent((x) -> {
          throw new ConflictException("Customer Already Exists by CPF");
        });

    final var aggregate = this.getAggregate(
        new Customer(UUID.randomUUID(), newCustomer.getCpf(), newCustomer.getName())
    );

    aggregate.validate();

    this.customerRepository.saveOrUpdateOneById(aggregate.getCustomer());

    return aggregate;
  }

  public CustomerAggregate updateById(UUID id, Customer newCustomer) {
    final var existingCustomer = this.customerRepository.getOneById(id).orElseThrow(() -> new NotFoundException("Customer Not Found by id"));

    this.customerRepository.getOneByCpf(newCustomer.getCpf())
        .filter(x -> !x.getId().equals(id))
        .ifPresent((x) -> {
          throw new ConflictException("A Customer Already Exists by CPF");
        });

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
