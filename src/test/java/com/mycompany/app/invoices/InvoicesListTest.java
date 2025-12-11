package com.mycompany.app.invoices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.app.products.ProductWithQt;
import com.mycompany.app.products.ProductsList;
import com.mycompany.app.taxes.TaxRate;

public class InvoicesListTest {
  java.util.Date date = new java.util.Date();
  private ProductWithQt productA = new ProductWithQt(1, "Product A", 10.0, 2);
  private ProductWithQt productB = new ProductWithQt(2, "Product B", 20.0, 1);
  private ProductWithQt productC = new ProductWithQt(3, "Product C", 120.0, 21);

  List<ProductWithQt> products = List.of(productA, productB);

  private ProductsList productsList;
  private Invoice invoiceA;
  private Invoice invoiceB;
  private Invoice invoiceC;
  private InvoicesList invoicesList;
  private Map<Integer, List<Invoice>> invoicesMap;

  @BeforeEach
  private void setNewInvoice() {
    productsList = new ProductsList(products);

    invoiceA = new Invoice(1, "Invoice A", date, TaxRate.TEN, productsList, 10);
    invoiceB = new Invoice(2, "Invoice B", date, TaxRate.EIGHTTEEN, productsList, 21);
    invoiceC = new Invoice(3, "Invoice C", date, TaxRate.EIGHT, new ProductsList(List.of(productC)),
        10);

    invoicesList = new InvoicesList(List.of(invoiceA, invoiceB, invoiceC));

    invoicesMap = Map.of(10, List.of(invoiceA, invoiceC), 21, List.of(invoiceB));
  }

  @Test
  public void testInvoicesListConstructor() {
    assertEquals(invoicesMap, invoicesList.getInvociesMap());
  }

  @Test
  public void testGetInvociesListByUserId() {
    assertEquals(List.of(invoiceA, invoiceC), invoicesList.getInvociesByUsersId(10));
    assertEquals(List.of(invoiceB), invoicesList.getInvociesByUsersId(21));
    assertEquals(null, invoicesList.getInvociesByUsersId(99));
  }
}
