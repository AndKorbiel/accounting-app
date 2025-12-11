package com.mycompany.app.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ProductsListTest {
  @Test
  public void testProductsListConstructor() {
    // given
    ProductWithQt productA = new ProductWithQt(1, "Product A", 10.0, 5);
    ProductWithQt productB = new ProductWithQt(2, "Product B", 20.0, 2);

    List<ProductWithQt> products = List.of(productA, productB);

    Map<Integer, ProductWithQt> expectedResult = Map.of(productA.getId(), productA, productB.getId(), productB);

    // when
    ProductsList list = new ProductsList(products);

    // then
    assertEquals(expectedResult, list.getProductsList());
    assertEquals(90, list.getTotalSum());
  }

}
