package com.uditagarwal.model;

import com.uditagarwal.exception.InvalidSlotException;
import com.uditagarwal.exception.ParkingLotException;
import com.uditagarwal.exception.SlotAlreadyOccupiedException;
import java.util.HashMap;
import java.util.Map;

/**
 * Model object to represent the functioning of a parking lot.
 */
public class ParkingLot {
  private static int MAX_CAPACITY = 100000;
  private int capacity;
  private Map<Integer, Slot> slots;

  public int getCapacity() {
    return capacity;
  }

  public ParkingLot(final int capacity) {
    if (capacity > MAX_CAPACITY || capacity <= 0) {
      throw new ParkingLotException("Invalid capacity given for parking lot.");
    }
    this.capacity = capacity;
    this.slots = new HashMap<>();
  }

  public Map<Integer, Slot> getSlots() {
    return slots;
  }

  /**
   * Helper method to get a {@link Slot} object for a given slot number. If slot does not exists,
   * then new slot will be created before giving it back.
   *
   * @param slotNumber Slot number.
   * @return Slot.
   */
  private Slot getSlot(final Integer slotNumber) {
    if (slotNumber > getCapacity() || slotNumber <= 0) {
      throw new InvalidSlotException();
    }
    final Map<Integer, Slot> allSlots = getSlots();
    if (!allSlots.containsKey(slotNumber)) {
      allSlots.put(slotNumber, new Slot(slotNumber));
    }
    return allSlots.get(slotNumber);
  }

  /**
   * Parks a car into a given slot number.
   *
   * @param car Car to be parked.
   * @param slotNumber Slot number in which it has to be parked.
   * @return {@link Slot} if the parking succeeds. If the slot is already occupied then {@link
   *     SlotAlreadyOccupiedException} is thrown.
   */
  public Slot park(final Car car, final Integer slotNumber) {
    final Slot slot = getSlot(slotNumber);
    if (!slot.isSlotFree()) {
      throw new SlotAlreadyOccupiedException();
    }
    slot.assignCar(car);
    return slot;
  }

  /**
   * Makes the slot free from the current parked car.
   *
   * @param slotNumber Slot number to be freed.
   * @return Freed slot.
   */
  public Slot makeSlotFree(final Integer slotNumber) {
    final Slot slot = getSlot(slotNumber);
    slot.unassignCar();
    return slot;
  }
}
