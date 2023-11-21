package com.example.ddd.service;

import com.example.ddd.domain.aggregate.OrderAggregate;
import com.example.ddd.exception.NotFoundException;
import com.example.ddd.repository.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;
  private final AddressRepository addressRepository;

  public OrderService(
      OrderRepository orderRepository,
      OrderItemRepository orderItemRepository,
      ProductRepository productRepository,
      CustomerRepository customerRepository,
      AddressRepository addressRepository
  ) {
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
    this.productRepository = productRepository;
    this.customerRepository = customerRepository;
    this.addressRepository = addressRepository;
  }

  public OrderAggregate getOneById(Long id) {
    final var order = this.orderRepository.getOneById(id).orElseThrow(() -> new NotFoundException("Order Not Found by id"));
    final var orderItems = this.orderItemRepository.getAllByOrderId(id);
    final var products = orderItems.stream().map(
        x -> this.productRepository.getOneById(x.getProductId()).orElseThrow(() -> new NotFoundException("Product Not Found by id"))
    ).collect(Collectors.toList());
    final var customer = this.customerRepository.getOneById(order.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer Not Found by id"));
    final var address = this.addressRepository.getOneById(order.getAddressId()).orElseThrow(() -> new NotFoundException("Address Not Found by id"));

    return new OrderAggregate(order, orderItems, products, customer, address);
  }
}
