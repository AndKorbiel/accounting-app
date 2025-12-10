package com.mycompany.app.invoices;

import java.util.Date;

import com.mycompany.app.products.ProductsList;
import com.mycompany.app.taxes.TaxRate;

public class Invoice {
  private int id;
  private String title;
  private Date date;
  private TaxRate taxRate;
  private ProductsList productsList;
  private double taxAmount;
  private double grossSum;
  private int userId;

  public Invoice(
      int id,
      String title,
      Date date,
      TaxRate taxRate,
      ProductsList productsList,
      int userId) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.taxRate = taxRate;
    this.productsList = productsList;
    this.userId = userId;

    this.calculateTaxAmount();
    this.calculateGrossSum();
  }

  public int getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public Date getDate() {
    return this.date;
  }

  public int getTaxRate() {
    return this.taxRate.getVal();
  }

  public ProductsList getProductsList() {
    return this.productsList;
  }

  public double getTotalSum() {
    return this.productsList.getTotalSum();
  }

  public double getTaxAmount() {
    return this.taxAmount;
  }

  public double getGrossSum() {
    return this.grossSum;
  }

  public int getUserId() {
    return this.userId;
  }

  public void calculateTaxAmount() {
    this.taxAmount = this.productsList.getTotalSum() * this.getTaxRate() / 100;
  }

  public void calculateGrossSum() {
    this.grossSum = this.productsList.getTotalSum() + this.getTaxAmount();
  }
}
