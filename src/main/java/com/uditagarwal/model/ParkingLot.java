package com.uditagarwal.model;

import com.uditagarwal.exception.InvalidSlotException;
import com.uditagarwal.exception.ParkingLotException;
import com.uditagarwal.exception.SlotAlreadyOccupiedException;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
  private static int MAX_CAPACITY = 100000;
  private int capacity;
  private Map<Integer, Slot> slots;

  public int getCapacity() {
    return capacity;
  }

  public ParkingLot(int capacity) {
    if (capacity > MAX_CAPACITY || capacity <= 0) {
      throw new ParkingLotException("Invalid capacity given for parking lot.");
    }
    this.capacity = capacity;
    this.slots = new HashMap<>();
  }

  public Map<Integer, Slot> getSlots() {
    return slots;
  }

  private Slot getSlot(Integer slotNumber) {
    if (slotNumber > getCapacity() || slotNumber <= 0) {
      throw new InvalidSlotException();
    }
    final Map<Integer, Slot> allSlots = getSlots();
    if (!allSlots.containsKey(slotNumber)) {
      allSlots.put(slotNumber, new Slot(slotNumber));
    }
    return allSlots.get(slotNumber);
  }

  public Slot park(final Car car, final Integer slotNumber) {
    final Slot slot = getSlot(slotNumber);
    if (!slot.isSlotFree()) {
      throw new SlotAlreadyOccupiedException();
    }
    slot.assignCar(car);
    return slot;
  }

  public Slot makeSlotFree(final Integer slotNumber) {
    final Slot slot = getSlot(slotNumber);
    slot.unassignCar();
    return slot;
  }
}
