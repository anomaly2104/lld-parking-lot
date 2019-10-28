package com.uditagarwal.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uditagarwal.exception.ParkingLotException;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.ParkingLot;
import com.uditagarwal.model.parking.strategy.NaturalOrderingParkingStrategy;
import com.uditagarwal.model.parking.strategy.ParkingStrategy;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotServiceTest {
  private ParkingLotService parkingLotService = new ParkingLotService();
  private ParkingStrategy parkingStrategy;
  private ParkingLot parkingLot;

  @Before
  public void setUp() throws Exception {
    parkingStrategy = mock(ParkingStrategy.class);
    ;
    parkingLot = mock(ParkingLot.class);
    parkingLotService.createParkingLot(parkingLot, parkingStrategy);
  }

  @Test(expected = ParkingLotException.class)
  public void testCreatingParkingLotWhenAlreadyExists() {
    final ParkingLotService parkingLotService = new ParkingLotService();
    parkingLotService.createParkingLot(new ParkingLot(10), new NaturalOrderingParkingStrategy());
    parkingLotService.createParkingLot(new ParkingLot(20), new NaturalOrderingParkingStrategy());
  }

  @Test
  public void testSlotNumberIsRemovedFromStrategyAfterParking() {
    final Car testCar = new Car("test-car-no", "white");
    when(parkingStrategy.getNextSlot()).thenReturn(1);
    parkingLotService.park(testCar);
    verify(parkingStrategy).removeSlot(1);
  }

  @Test
  public void testParkingIsDoneInTheParkingLot() {
    final Car testCar = new Car("test-car-no", "white");
    when(parkingStrategy.getNextSlot()).thenReturn(1);
    parkingLotService.park(testCar);
    verify(parkingLot).park(testCar, 1);
  }

  @Test(expected = ParkingLotException.class)
  public void testParkingCarWithoutCreatingParkingLot() {
    final ParkingLotService parkingLotService = new ParkingLotService();
    final Car testCar = new Car("test-car-no", "white");
    parkingLotService.park(testCar);
  }

  @Test
  public void testFreeingSlot() {
    parkingLotService.makeSlotFree(1);
    verify(parkingStrategy).addSlot(1);
    verify(parkingLot).makeSlotFree(1);
  }

  @Test(expected = ParkingLotException.class)
  public void testFreeingSlotWithoutCreatingParkingLot() {
    final ParkingLotService parkingLotService = new ParkingLotService();
    parkingLotService.makeSlotFree(1);
  }
}
