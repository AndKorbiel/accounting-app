package com.mycompany.app.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ProductsListTest {
  @Test
  public void testProductsListConstructor() {
    // given
    Map<Integer, ProductWithQt> products = Map.of(1, new ProductWithQt(1, "Product A", 10.0, 5),
        2, new ProductWithQt(2, "Product B", 20.0, 2));

    // when
    ProductsList list = new ProductsList(products);

    // then
    assertEquals(products, list.getProductsList());
    assertEquals(90, list.getTotalSum());
  }

}
