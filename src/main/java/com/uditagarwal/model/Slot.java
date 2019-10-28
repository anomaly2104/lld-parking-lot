package com.uditagarwal.model;

public class Slot {
  private Car parkedCar;
  private Integer slotNumber;

  public Slot(Integer slotNumber) {
    this.slotNumber = slotNumber;
  }

  public boolean isSlotFree() {
    return parkedCar == null;
  }

  public void assignCar(Car car) {
    this.parkedCar = car;
  }

  public Car getParkedCar() {
    return parkedCar;
  }
}
