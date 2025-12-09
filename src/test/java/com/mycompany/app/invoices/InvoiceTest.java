package com.mycompany.app.invoices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.app.products.ProductWithQt;
import com.mycompany.app.products.ProductsList;
import com.mycompany.app.taxes.TaxRate;

public class InvoiceTest {
  int id = 1;
  String title = "Invoice A";
  java.util.Date date = new java.util.Date();
  TaxRate taxRate = TaxRate.TEN;

  Map<Integer, ProductWithQt> products = Map.of(1, new ProductWithQt(1, "Product A", 10.0, 2),
      2, new ProductWithQt(2, "Product B", 20.0, 1));

  private ProductsList productsList;
  private Invoice invoice;

  @BeforeEach
  private void setNewInvoice() {
    productsList = new ProductsList(products);
    invoice = new Invoice(id, title, date, taxRate, productsList);
  }

  @Test
  public void testInvoiceConstructor() {
    // then
    assertEquals(id, invoice.getId());
    assertEquals(title, invoice.getTitle());
    assertEquals(date, invoice.getDate());
    assertEquals(taxRate.getVal(), invoice.getTaxRate());
    assertEquals(productsList, invoice.getProductsList());
  }

  @Test
  public void testCalculateTotalSum() {
    // 2 * 10 + 1 * 20
    double totalSum = 40;

    assertEquals(totalSum, invoice.getTotalSum());
  }

  @Test
  public void testCalculateTaxAmount() {
    // 40 * 10 / 100
    double taxAmount = 4;

    assertEquals(taxAmount, invoice.getTaxAmount());
  }

  @Test
  public void testCalculateTaxAmountWithOtherTaxRate() {
    // given
    final Invoice invoiceB = new Invoice(id, title, date, TaxRate.EIGHTTEEN, productsList);
    // 40 * 18 / 100
    double taxAmount = 7.2;

    assertEquals(18, invoiceB.getTaxRate());
    assertEquals(40, invoiceB.getTotalSum());
    assertEquals(taxAmount, invoiceB.getTaxAmount());
  }

  @Test
  public void testCalculateGrossSumWithOtherTaxRate() {
    // 40 + 4
    double grossSum = 44;

    assertEquals(grossSum, invoice.getGrossSum());
  }

  @Test
  public void testCalculateGrossSum() {
    // given
    final Invoice invoiceB = new Invoice(id, title, date, TaxRate.EIGHT, productsList);

    // 40 + 3.2
    double grossSum = 43.2;

    assertEquals(grossSum, invoiceB.getGrossSum());
  }
}
