package com.uditagarwal.model;

import static org.junit.Assert.*;

import com.uditagarwal.exception.ParkingLotException;
import org.junit.Test;

public class ParkingLotTest {

  @Test(expected = ParkingLotException.class)
  public void testNegativeCapacity() {
    new ParkingLot(-1);
  }

  @Test(expected = ParkingLotException.class)
  public void testZeroCapacity() {
    new ParkingLot(0);
  }

  @Test
  public void testValidCapacity() {
    new ParkingLot(100);
  }

  @Test(expected = ParkingLotException.class)
  public void testMoreThanMaxCapacity() {
    new ParkingLot(1000001);
  }
}