package com.uditagarwal.model.parking.strategy;

import com.uditagarwal.exception.NoFreeSlotAvailableException;
import java.util.TreeSet;

/**
 * Parking strategy in which the natural ordering numbers are used for deciding the slot numbers.
 * For example, 1st car will be parked in slot 1, then next in slot 2, then in slot 3, and so on.
 */
public class NaturalOrderingParkingStrategy implements ParkingStrategy {
  TreeSet<Integer> slotTreeSet;

  public NaturalOrderingParkingStrategy() {
    this.slotTreeSet = new TreeSet<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addSlot(Integer slotNumber) {
    this.slotTreeSet.add(slotNumber);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeSlot(Integer slotNumber) {
    this.slotTreeSet.remove(slotNumber);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer getNextSlot() {
    if (slotTreeSet.isEmpty()) {
      throw new NoFreeSlotAvailableException();
    }
    return this.slotTreeSet.first();
  }
}
