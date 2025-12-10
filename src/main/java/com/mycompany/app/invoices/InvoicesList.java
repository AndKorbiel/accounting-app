package com.mycompany.app.invoices;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvoicesList {
  private Map<Integer, List<Invoice>> invoicesMap;

  public InvoicesList(List<Invoice> invoices) {
    this.invoicesMap = mapInvoicesByUserId(invoices);
  }

  private static Map<Integer, List<Invoice>> mapInvoicesByUserId(List<Invoice> invoices) {
    /* Manual way */
    // Map<Integer, List<Invoice>> invoicesMap = new HashMap<>();
    // List<Integer> userIds = invoices.stream().map(el -> el.getUserId()).toList();

    // for (Integer userId : userIds) {
    // List<Invoice> userInvoices = new ArrayList<>();

    // for (Invoice inv : invoices) {
    // if (inv.getUserId() == userId) {
    // userInvoices.add(inv);
    // }
    // }

    // invoicesMap.put(userId, userInvoices);
    // }

    // return invoicesMap;

    /* Modern */
    return invoices.stream().collect(Collectors.groupingBy(Invoice::getUserId));
  }

  public Map<Integer, List<Invoice>> getInvociesMap() {
    return this.invoicesMap;
  }

  public List<Invoice> getInvociesByUsersId(int userId) {
    return invoicesMap.get(userId);
  }
}
