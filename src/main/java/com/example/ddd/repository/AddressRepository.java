package com.example.ddd.repository;

import com.example.ddd.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AddressRepository {
  public static UUID FIRST_VALID_ADDRESS_ID = UUID.fromString("e7d8ab10-8a35-4d2d-a25f-9add6ee29016");
  public static UUID SECOND_VALID_ADDRESS_ID = UUID.fromString("ebe0be72-753f-4a0f-b5ae-da32ee4cb49f");
  public static UUID THIRD_VALID_ADDRESS_ID = UUID.fromString("11e936c3-1f1e-4cd3-9a89-851d6666e34d");

  private List<Address> addresses = List.of(
      new Address(
          AddressRepository.FIRST_VALID_ADDRESS_ID,
          CustomerRepository.FIRST_VALID_CUSTOMER_ID,
          "14403-500",
          "Rua Aparecida dos Santos",
          "115",
          "Bairro Jardim Florido",
          "BL 10 Apto 205",
          "Franca",
          "SP"
      ),
      new Address(
          AddressRepository.SECOND_VALID_ADDRESS_ID,
          CustomerRepository.FIRST_VALID_CUSTOMER_ID,
          "14403-500",
          "Rua Peixoto",
          "1010",
          "Bairro Queiroz",
          null,
          "Franca",
          "SP"
      ),
      new Address(
          AddressRepository.THIRD_VALID_ADDRESS_ID,
          CustomerRepository.SECOND_VALID_CUSTOMER_ID,
          "14405-250",
          "Rua das Cantinas",
          "X-1",
          "Bairro Bela Vista",
          null,
          "Ribeirão Preto",
          "SP"
      )
  );

  public Optional<Address> getOneById(UUID id) {
    return this.addresses.stream().filter(x -> x.getId().equals(id)).findFirst();
  }

  public List<Address> getAllByCustomerId(UUID customerId) {
    return this.addresses.stream().filter(x -> x.getCustomerId().equals(customerId)).collect(Collectors.toList());
  }
}
