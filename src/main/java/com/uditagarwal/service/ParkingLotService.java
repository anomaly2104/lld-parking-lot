package com.uditagarwal.service;

import com.uditagarwal.exception.ParkingLotException;
import com.uditagarwal.model.ParkingLot;
import com.uditagarwal.model.parking.strategy.ParkingStrategy;

public class ParkingLotService {
  private ParkingLot parkingLot;
  private ParkingStrategy parkingStrategy;

  public void createParkingLot(final int capacity, final ParkingStrategy parkingStrategy) {
    if (parkingLot != null) {
      throw new ParkingLotException("Parking lot already exists.");
    }
    this.parkingLot = new ParkingLot(capacity);
    this.parkingStrategy = parkingStrategy;
    for (int i = 1; i <= capacity; i++) {
      parkingStrategy.addSlot(i);
    }
  }
}
