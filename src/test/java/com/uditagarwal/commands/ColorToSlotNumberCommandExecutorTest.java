package com.uditagarwal.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.Command;
import com.uditagarwal.model.Slot;
import com.uditagarwal.service.ParkingLotService;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public class ColorToSlotNumberCommandExecutorTest {
  private ParkingLotService parkingLotService;
  private OutputPrinter outputPrinter;
  private ColorToSlotNumberCommandExecutor colorToSlotNumberCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    outputPrinter = mock(OutputPrinter.class);
    colorToSlotNumberCommandExecutor =
        new ColorToSlotNumberCommandExecutor(parkingLotService, outputPrinter);
  }

  @Test
  public void testValidCommand() {
    assertTrue(
        colorToSlotNumberCommandExecutor.validate(
            new Command("slot_numbers_for_cars_with_colour white")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(
        colorToSlotNumberCommandExecutor.validate(
            new Command("slot_numbers_for_cars_with_colour")));
    assertFalse(
        colorToSlotNumberCommandExecutor.validate(
            new Command("slot_numbers_for_cars_with_colour a b")));
  }

  @Test
  public void testWhenNoSlotsFoundWithAColor() {
    when(parkingLotService.getSlotsForColor("white")).thenReturn(Collections.emptyList());
    colorToSlotNumberCommandExecutor.execute(
        new Command("slot_numbers_for_cars_with_colour white"));

    verify(outputPrinter).printWithNewLine("Not found");
  }

  @Test
  public void testCarsWithAColor() {
    final Slot slot1 = new Slot(1);
    slot1.assignCar(new Car("num_white1", "white"));
    final Slot slot2 = new Slot(2);
    slot2.assignCar(new Car("num_white2", "white"));
    when(parkingLotService.getSlotsForColor("white")).thenReturn(Arrays.asList(slot1, slot2));
    colorToSlotNumberCommandExecutor.execute(
        new Command("slot_numbers_for_cars_with_colour white"));

    verify(outputPrinter).printWithNewLine("1, 2");
  }
}
