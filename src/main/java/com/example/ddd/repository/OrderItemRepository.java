package com.example.ddd.repository;

import com.example.ddd.domain.OrderItem;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderItemRepository {
  private List<OrderItem> orderItems = List.of(
      new OrderItem(
          OrderRepository.FIRST_VALID_ORDER_ID,
          ProductRepository.FIRST_VALID_PRODUCT_ID,
          2,
          new BigDecimal("50")
      ),
      new OrderItem(
          OrderRepository.FIRST_VALID_ORDER_ID,
          ProductRepository.SECOND_VALID_PRODUCT_ID,
          2,
          new BigDecimal("100")
      ),
      new OrderItem(
          OrderRepository.SECOND_VALID_ORDER_ID,
          ProductRepository.FIRST_VALID_PRODUCT_ID,
          14,
          new BigDecimal("349.99")
      ),
      new OrderItem(
          OrderRepository.THIRD_VALID_ORDER_ID,
          ProductRepository.FIRST_VALID_PRODUCT_ID,
          20,
          new BigDecimal("500")
      ),
      new OrderItem(
          OrderRepository.THIRD_VALID_ORDER_ID,
          ProductRepository.SECOND_VALID_PRODUCT_ID,
          20,
          new BigDecimal("1000")
      ),
      new OrderItem(
          OrderRepository.THIRD_VALID_ORDER_ID,
          ProductRepository.THIRD_VALID_PRODUCT_ID,
          1,
          new BigDecimal("1000.50")
      )
  );

  public List<OrderItem> getAllByOrderId(Long orderId) {
    return this.orderItems.stream().filter(x -> x.getOrderId().equals(orderId)).collect(Collectors.toList());
  }
}
