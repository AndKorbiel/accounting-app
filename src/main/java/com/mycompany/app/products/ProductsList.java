package com.mycompany.app.products;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsList {
  private Map<Integer, ProductWithQt> productsMappedById;
  private double totalSum;

  public ProductsList(List<ProductWithQt> productsList) {
    this.productsMappedById = productsList.stream().collect(Collectors.toMap(ProductWithQt::getId, item -> item));

    ProductsListCalculations productsListCalculations = new ProductsListCalculations();
    productsListCalculations.calculateTotalSum();
  }

  public double getTotalSum() {
    return this.totalSum;
  }

  public Map<Integer, ProductWithQt> getProductsList() {
    return this.productsMappedById;
  }

  private class ProductsListCalculations {
    public void calculateTotalSum() {
      double sum = 0;
      final Set<Integer> keys = ProductsList.this.productsMappedById.keySet();

      for (Integer key : keys) {
        sum += productsMappedById.get(key).getLocalSum();
      }

      totalSum = sum;
    }
  }
}
