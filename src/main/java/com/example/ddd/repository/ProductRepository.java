package com.example.ddd.repository;

import com.example.ddd.domain.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
  public static Long FIRST_VALID_PRODUCT_ID = 1L;
  public static Long SECOND_VALID_PRODUCT_ID = 2L;
  public static Long THIRD_VALID_PRODUCT_ID = 3L;

  private List<Product> products = List.of(
      new Product(
          ProductRepository.FIRST_VALID_PRODUCT_ID,
          "Mouse",
          420,
          new BigDecimal("25.00")
      ),
      new Product(
          ProductRepository.SECOND_VALID_PRODUCT_ID,
          "Teclado",
          350,
          new BigDecimal("50.00")
      ),
      new Product(
          ProductRepository.THIRD_VALID_PRODUCT_ID,
          "Monitor",
          50,
          new BigDecimal("1000.50")
      )
  );

  public Optional<Product> getOneById(Long id) {
    return this.products.stream().filter(x -> x.getId().equals(id)).findFirst();
  }
}
