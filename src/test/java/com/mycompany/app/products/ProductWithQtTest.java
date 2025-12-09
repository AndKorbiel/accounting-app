package com.mycompany.app.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductWithQtTest {
  private ProductWithQt product;

  int id = 1;
  String name = "Product A";
  double price = 10.5;
  int qt = 1;

  @BeforeEach
  public void setNewProduct() {
    product = new ProductWithQt(id, name, price, qt);
  }

  @Test
  public void testProductWithQtConstructor() {
    // when
    double localSum = 10.5;

    // then
    assertEquals(id, product.getId());
    assertEquals(name, product.getName());
    assertEquals(price, product.getPrice());
    assertEquals(qt, product.getQuantity());
    assertEquals(localSum, product.getLocalSum());
  }

  @Test
  public void testSettingLocalSum() {
    // when
    product.setQuantity(3);
    double localSum = 31.5;

    // then
    assertEquals(localSum, product.getLocalSum());
  }
}
