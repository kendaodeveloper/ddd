package com.example.ddd.repository;

import com.example.ddd.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepository {
  public static UUID FIRST_VALID_CUSTOMER_ID = UUID.fromString("a4454bb9-4af4-4deb-a818-e8a85e9be3eb");
  public static UUID SECOND_VALID_CUSTOMER_ID = UUID.fromString("7a7f943e-0741-46fa-8961-b503c4498745");
  public static UUID THIRD_VALID_CUSTOMER_ID = UUID.fromString("7938839c-52b0-41a1-8743-d378c1f29a96");

  private final ArrayList<Customer> customers = new ArrayList<>(List.of(
      new Customer(CustomerRepository.FIRST_VALID_CUSTOMER_ID, "90233443088", "João"),
      new Customer(CustomerRepository.SECOND_VALID_CUSTOMER_ID, "55586733030", "Maria"),
      new Customer(CustomerRepository.THIRD_VALID_CUSTOMER_ID, "31986915085", "José")
  ));

  public Optional<Customer> getOneById(UUID id) {
    return this.customers.stream().filter(x -> x.getId().equals(id)).findFirst();
  }

  public Optional<Customer> getOneByCpf(String cpf) {
    return this.customers.stream().filter(x -> x.getCpf().equals(cpf)).findFirst();
  }

  public void saveOrUpdateOneById(Customer newCustomer) {
    this.customers.stream().filter(x -> x.getId().equals(newCustomer.getId())).findFirst()
        .ifPresentOrElse((x) -> {
          x.setCpf(newCustomer.getCpf());
          x.setName(newCustomer.getName());
        }, () -> {
          this.customers.add(newCustomer);
        });
  }
}
