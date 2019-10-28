package com.uditagarwal.service;

import com.uditagarwal.exception.ParkingLotException;
import com.uditagarwal.model.parking.strategy.NaturalOrderingParkingStrategy;
import org.junit.Test;

public class ParkingLotServiceTest {
  private ParkingLotService parkingLotService = new ParkingLotService();

  @Test(expected = ParkingLotException.class)
  public void testCreatingParkingLotWhenAlreadyExists() {
    parkingLotService.createParkingLot(10, new NaturalOrderingParkingStrategy());
    parkingLotService.createParkingLot(20, new NaturalOrderingParkingStrategy());
  }
}
