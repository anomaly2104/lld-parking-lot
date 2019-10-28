package com.uditagarwal.model.parking.strategy;

import static org.junit.Assert.assertEquals;

import com.uditagarwal.exception.NoFreeSlotAvailableException;
import org.junit.Test;

public class NaturalOrderingParkingStrategyTest {
  private NaturalOrderingParkingStrategy naturalOrderingParkingStrategy =
      new NaturalOrderingParkingStrategy();

  @Test
  public void testValidStrategyExecution() {
    naturalOrderingParkingStrategy.addSlot(1);
    naturalOrderingParkingStrategy.addSlot(2);
    naturalOrderingParkingStrategy.addSlot(3);
    assertEquals((Integer)1, naturalOrderingParkingStrategy.getNextSlot());
    naturalOrderingParkingStrategy.removeSlot(2);
    assertEquals((Integer)1, naturalOrderingParkingStrategy.getNextSlot());
    naturalOrderingParkingStrategy.removeSlot(1);
    assertEquals((Integer)3, naturalOrderingParkingStrategy.getNextSlot());
    naturalOrderingParkingStrategy.addSlot(2);
    assertEquals((Integer)2, naturalOrderingParkingStrategy.getNextSlot());
  }

  @Test(expected = NoFreeSlotAvailableException.class)
  public void testFullParkingStrategy() {
    Integer nextSlot = naturalOrderingParkingStrategy.getNextSlot();
  }
}
