package com.example.ddd.endpoint;

import com.example.ddd.domain.Customer;
import com.example.ddd.domain.aggregate.CustomerAggregate;
import com.example.ddd.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerEndpoint {
  private final CustomerService service;

  public CustomerEndpoint(CustomerService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public CustomerAggregate getCustomerById(@PathVariable UUID id) {
    return service.getOneById(id);
  }

  @GetMapping
  public CustomerAggregate getCustomerByCpf(@RequestParam String cpf) {
    return service.getOneByCpf(cpf);
  }

  @PostMapping
  public CustomerAggregate createCustomer(@RequestBody Customer customer) {
    return service.create(customer);
  }

  @PutMapping("/{id}")
  public CustomerAggregate updateCustomerById(@PathVariable UUID id, @RequestBody Customer customer) {
    return service.updateById(id, customer);
  }
}
