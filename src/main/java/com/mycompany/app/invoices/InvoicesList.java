package com.mycompany.app.invoices;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvoicesList {
  private Map<Integer, List<Invoice>> invoicesMap;
  UserOperations userOperations;
  DateOperations dateOperations;

  public InvoicesList(List<Invoice> invoices) {
    this.userOperations = new UserOperations();
    this.dateOperations = new DateOperations();
    this.invoicesMap = userOperations.mapInvoicesByUserId(invoices);
  }

  public Map<Integer, List<Invoice>> getInvociesMap() {
    return this.invoicesMap;
  }

  public List<Invoice> getInvociesByUsersId(int userId) {
    return invoicesMap.get(userId);
  }

  public Map<Integer, List<Invoice>> getInvoicesByDate(Date date) {
    return this.dateOperations.getInvoicesByDate(date);

  }

  public Map<Integer, List<Invoice>> getInvoicesFromDateRange(Date startDate, Date... endDate) {
    return this.dateOperations.getInvoicesFromDateRange(startDate, endDate);
  }

  public double calculateUserInvoicesGrossSum(int userId) {
    return this.userOperations.calculateUserInvoicesGrossSum(userId);
  }

  public double calculateUserTaxAmountSum(int userId) {
    return this.userOperations.calculateUserTaxAmountSum(userId);
  }

  private class UserOperations {
    private Map<Integer, List<Invoice>> mapInvoicesByUserId(List<Invoice> invoices) {
      return invoices.stream().collect(Collectors.groupingBy(Invoice::getUserId));
    }

    public double calculateUserInvoicesGrossSum(int userId) {
      List<Invoice> userInvoices = InvoicesList.this.getInvociesByUsersId(userId);

      return userInvoices.stream().mapToDouble(Invoice::getGrossSum).sum();
    }

    public double calculateUserTaxAmountSum(int userId) {
      List<Invoice> userInvoices = InvoicesList.this.getInvociesByUsersId(userId);
      double sum = 0;

      for (Invoice in : userInvoices) {
        sum += in.getTaxAmount();
      }

      return sum;
    }
  }

  private class DateOperations {
    private Map<Integer, List<Invoice>> filterInvoices(Predicate<Invoice> predicate) {
      return invoicesMap.entrySet().stream()
          .map(e -> Map.entry(
              e.getKey(),
              e.getValue().stream()
                  .filter(predicate)
                  .collect(Collectors.toList())))
          .filter(e -> !e.getValue().isEmpty())
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, List<Invoice>> getInvoicesByDate(Date date) {
      return filterInvoices(inv -> inv.getDate().equals(date));
    }

    public Map<Integer, List<Invoice>> getInvoicesFromDateRange(Date startDate, Date... endDate) {
      Date now = new Date();

      return filterInvoices(inv -> inv.getDate().after(startDate)
          && inv.getDate().before((endDate != null && endDate.length > 0) ? endDate[0] : now));
    }
  }
}