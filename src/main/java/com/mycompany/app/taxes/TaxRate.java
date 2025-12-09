package com.mycompany.app.taxes;

public enum TaxRate {
  EIGHT(8),
  TEN(10),
  EIGHTTEEN(18);

  private int val;

  TaxRate(int val) {
    this.val = val;
  }

  public int getVal() {
    return this.val;
  }
}
