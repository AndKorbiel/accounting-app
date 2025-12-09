package com.mycompany.app.products;

import java.util.Map;
import java.util.Set;

public class ProductsList {
  private Map<Integer, ProductWithQt> productsList;
  private double totalSum;

  public ProductsList(Map<Integer, ProductWithQt> productsList) {
    this.productsList = productsList;
    this.calculateTotalSum();
  }

  public void calculateTotalSum() {
    double sum = 0;
    final Set<Integer> keys = productsList.keySet();

    for (Integer key : keys) {
      sum += productsList.get(key).getLocalSum();
    }

    totalSum = sum;
  }

  public double getTotalSum() {
    return this.totalSum;
  }

  public Map<Integer, ProductWithQt> getProductsList() {
    return this.productsList;
  }
}
