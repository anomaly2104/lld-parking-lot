package com.uditagarwal.model;

public class Car {
  private String registrationNumber;
  private String color;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public String getColor() {
    return color;
  }

  public Car(String registrationNumber, String color) {
    this.registrationNumber = registrationNumber;
    this.color = color;
  }
}
