package com.mycompany.app.invoices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.app.products.ProductWithQt;
import com.mycompany.app.products.ProductsList;
import com.mycompany.app.taxes.TaxRate;

public class InvoicesListTest {
  Date date = new java.util.Date();
  Date tenDaysBefore = new Date(date.getTime() - (10 * 24 * 60 * 60 * 1000));

  private ProductWithQt productA = new ProductWithQt(1, "Product A", 10.0, 2);
  private ProductWithQt productB = new ProductWithQt(2, "Product B", 20.0, 1);
  private ProductWithQt productC = new ProductWithQt(3, "Product C", 120.0, 2);

  List<ProductWithQt> products = List.of(productA, productB);

  private ProductsList productsList;
  private Invoice invoiceA;
  private Invoice invoiceB;
  private Invoice invoiceC;
  private InvoicesList invoicesList;
  private Map<Integer, List<Invoice>> invoicesMap;

  @BeforeEach
  private void setNewInvoicesList() {
    productsList = new ProductsList(products);

    invoiceA = new Invoice(1, "Invoice A", date, TaxRate.TEN, productsList, 10);
    invoiceB = new Invoice(2, "Invoice B", date, TaxRate.EIGHTTEEN, productsList, 21);
    invoiceC = new Invoice(3, "Invoice C",
        tenDaysBefore, TaxRate.EIGHT, new ProductsList(List.of(productC)),
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

  @Test
  public void testGetInvoicesByDate() {
    assertEquals(Map.of(10, List.of(invoiceC)), invoicesList.getInvoicesByDate(tenDaysBefore));
    assertEquals(Map.of(10, List.of(invoiceA), 21, List.of(invoiceB)), invoicesList.getInvoicesByDate(date));
  }

  @Test
  public void testGetInvoicesByDateRange() {
    Date twoWeeksBefore = new Date(date.getTime() - (14 * 24 * 60 * 60 * 1000));
    Date fiveDaysBefore = new Date(date.getTime() - (5 * 24 * 60 * 60 * 1000));
    Date oneDayBefore = new Date(date.getTime() - (1 * 24 * 60 * 60 * 1000));

    assertEquals(Map.of(10, List.of(invoiceA, invoiceC), 21, List.of(invoiceB)),
        invoicesList.getInvoicesFromDateRange(twoWeeksBefore));

    assertEquals(Map.of(10, List.of(invoiceA), 21, List.of(invoiceB)),
        invoicesList.getInvoicesFromDateRange(oneDayBefore));

    assertEquals(Map.of(10, List.of(invoiceC)),
        invoicesList.getInvoicesFromDateRange(twoWeeksBefore, fiveDaysBefore));
  }

  @Test
  public void testCalculateUserInvoicesGrossSum() {
    // invoiceA: 40 * 10% = 44
    // invoiceC: 240 * 8% = 259,2
    double expectedResultA = 303.2;

    // invoiceB: 40* 18%
    double expectedResultB = 47.2;

    assertEquals(expectedResultA, invoicesList.calculateUserInvoicesGrossSum(10));
    assertEquals(expectedResultB, invoicesList.calculateUserInvoicesGrossSum(21));
  }

  @Test
  public void testCalculateUserTaxAmountSum() {
    // 4 + 19,2
    double expectedResultA = 23.2;

    // invoiceB: 40 * 18%
    double expectedResultB = 7.2;

    assertEquals(expectedResultA, invoicesList.calculateUserTaxAmountSum(10));
    assertEquals(expectedResultB, invoicesList.calculateUserTaxAmountSum(21));
  }
}
