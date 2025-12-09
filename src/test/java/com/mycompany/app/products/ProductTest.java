package com.mycompany.app.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductTest {
  @Test
  public void testProductConstructor() {
    // given
    int id = 1;
    String name = "Product A";
    double price = 10.5;

    // when
    final Product product = new Product(id, name, price);

    // then
    assertEquals(id, product.getId());
    assertEquals(name, product.getName());
    assertEquals(price, product.getPrice());
  }
}
