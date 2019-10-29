package com.uditagarwal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uditagarwal.exception.ParkingLotException;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.ParkingLot;
import com.uditagarwal.model.Slot;
import com.uditagarwal.model.parking.strategy.NaturalOrderingParkingStrategy;
import com.uditagarwal.model.parking.strategy.ParkingStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  @Test
  public void testOccupiedSlots() {
    final Map<Integer, Slot> allSlots = new HashMap<>();
    final Slot slot1 = new Slot(1);
    final Slot slot2 = new Slot(2);
    slot2.assignCar(new Car("test-car-no1", "white"));
    final Slot slot3 = new Slot(3);
    final Slot slot4 = new Slot(4);
    slot4.assignCar(new Car("test-car-no2", "white"));

    allSlots.put(1, slot1);
    allSlots.put(2, slot2);
    allSlots.put(3, slot3);
    allSlots.put(4, slot4);

    when(parkingLot.getSlots()).thenReturn(allSlots);
    when(parkingLot.getCapacity()).thenReturn(10);

    final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
    assertEquals(2, occupiedSlots.size());
    assertEquals(slot2, occupiedSlots.get(0));
    assertEquals(slot4, occupiedSlots.get(1));
  }

  @Test
  public void testGetSlotsForAParticularColor() {
    final Map<Integer, Slot> allSlots = new HashMap<>();
    final Slot slot1 = new Slot(1);
    slot1.assignCar(new Car("test-car-no1", "blue"));
    final Slot slot2 = new Slot(2);
    slot2.assignCar(new Car("test-car-no2", "white"));
    final Slot slot3 = new Slot(3);
    final Slot slot4 = new Slot(4);
    slot4.assignCar(new Car("test-car-no3", "white"));

    allSlots.put(1, slot1);
    allSlots.put(2, slot2);
    allSlots.put(3, slot3);
    allSlots.put(4, slot4);

    when(parkingLot.getSlots()).thenReturn(allSlots);
    when(parkingLot.getCapacity()).thenReturn(10);

    final List<Slot> slots = parkingLotService.getSlotsForColor("white");
    assertEquals(2, slots.size());
    assertEquals(slot2, slots.get(0));
    assertEquals(slot4, slots.get(1));
  }

  @Test
  public void testGetSlotsForAParticularCarColorWhenNoCarMatches() {
    final Map<Integer, Slot> allSlots = new HashMap<>();
    final Slot slot1 = new Slot(1);
    slot1.assignCar(new Car("test-car-no1", "blue"));
    final Slot slot2 = new Slot(2);
    final Slot slot3 = new Slot(3);
    slot3.assignCar(new Car("test-car-no2", "red"));

    allSlots.put(1, slot1);
    allSlots.put(2, slot2);
    allSlots.put(3, slot3);

    when(parkingLot.getSlots()).thenReturn(allSlots);
    when(parkingLot.getCapacity()).thenReturn(10);

    final List<Slot> slots = parkingLotService.getSlotsForColor("white");
    assertEquals(0, slots.size());
  }
}
