package com.uditagarwal.model;

import com.uditagarwal.exception.ParkingLotException;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
  private static int MAX_CAPACITY = 100000;
  private int capacity;

  public ParkingLot(int capacity) {
    if (capacity > MAX_CAPACITY || capacity <= 0) {
      throw new ParkingLotException("Invalid capacity given for parking lot.");
    }
    this.capacity = capacity;
  }
}
