package com.mycompany.app.users;

public class User {
  private int id;
  private String name;
  private double userDiscount;

  public User(int id, String name, double discount) {
    this.id = id;
    this.name = name;
    this.userDiscount = discount;
  }

  public int getUserId() {
    return this.id;
  }

  public String getUserName() {
    return this.name;
  }

  public double getUserDiscount() {
    return this.userDiscount;
  }
}
