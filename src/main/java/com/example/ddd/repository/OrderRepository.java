package com.example.ddd.repository;

import com.example.ddd.domain.Order;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
  public static Long FIRST_VALID_ORDER_ID = 1L;
  public static Long SECOND_VALID_ORDER_ID = 2L;
  public static Long THIRD_VALID_ORDER_ID = 3L;

  private List<Order> orders = List.of(
      new Order(
          OrderRepository.FIRST_VALID_ORDER_ID,
          CustomerRepository.FIRST_VALID_CUSTOMER_ID,
          AddressRepository.FIRST_VALID_ADDRESS_ID,
          new BigDecimal("150"),
          LocalDate.now()
      ),
      new Order(
          OrderRepository.SECOND_VALID_ORDER_ID,
          CustomerRepository.FIRST_VALID_CUSTOMER_ID,
          AddressRepository.SECOND_VALID_ADDRESS_ID,
          new BigDecimal("349.99"),
          LocalDate.now()
      ),
      new Order(
          OrderRepository.THIRD_VALID_ORDER_ID,
          CustomerRepository.SECOND_VALID_CUSTOMER_ID,
          AddressRepository.THIRD_VALID_ADDRESS_ID,
          new BigDecimal("2500.50"),
          LocalDate.now()
      )
  );

  public Optional<Order> getOneById(Long id) {
    return this.orders.stream().filter(x -> x.getId().equals(id)).findFirst();
  }

  public List<Order> getAllByCustomerId(UUID customerId) {
    return this.orders.stream().filter(x -> x.getCustomerId().equals(customerId)).collect(Collectors.toList());
  }
}
