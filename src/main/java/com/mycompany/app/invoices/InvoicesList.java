package com.mycompany.app.invoices;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvoicesList {
  private Map<Integer, List<Invoice>> invoicesMap;

  public InvoicesList(List<Invoice> invoices) {
    UserOperations userOperations = new UserOperations();

    this.invoicesMap = userOperations.mapInvoicesByUserId(invoices);
  }

  public Map<Integer, List<Invoice>> getInvociesMap() {
    return this.invoicesMap;
  }

  public List<Invoice> getInvociesByUsersId(int userId) {
    return invoicesMap.get(userId);
  }

  public double calculateUserInvoicesGrossSum(int userId) {
    UserOperations userOperations = new UserOperations();

    return userOperations.calculateUserInvoicesGrossSum(userId);
  }

  private class UserOperations {
    private Map<Integer, List<Invoice>> mapInvoicesByUserId(List<Invoice> invoices) {
      return invoices.stream().collect(Collectors.groupingBy(Invoice::getUserId));
    }

    public double calculateUserInvoicesGrossSum(int userId) {
      List<Invoice> userInvoices = InvoicesList.this.getInvociesByUsersId(userId);

      return userInvoices.stream().mapToDouble(Invoice::getGrossSum).sum();
    }
  }
}