package com.mycompany.app.products;

public class ProductWithQt extends Product {
  private int quantity;
  private double localSum;

  public ProductWithQt(int id, String name, double price, int qt) {
    super(id, name, price);
    this.quantity = qt;

    setLocalSum();
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setLocalSum() {
    this.localSum = this.price * this.quantity;
  }

  public void setQuantity(int qt) {
    this.quantity = qt;

    setLocalSum();
  }

  public double getLocalSum() {
    return this.localSum;
  }
}
