package com.uditagarwal.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.Command;
import com.uditagarwal.model.Slot;
import com.uditagarwal.service.ParkingLotService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StatusCommandExecutorTest {
  private ParkingLotService parkingLotService;
  private OutputPrinter outputPrinter;
  private StatusCommandExecutor statusCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    outputPrinter = mock(OutputPrinter.class);
    statusCommandExecutor = new StatusCommandExecutor(parkingLotService, outputPrinter);
  }

  @Test
  public void testValidCommand() {
    assertTrue(statusCommandExecutor.validate(new Command("status")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(statusCommandExecutor.validate(new Command("status 1")));
    assertFalse(statusCommandExecutor.validate(new Command("status 2 3")));
  }

  @Test
  public void testCommandExecutionWhenParkingLotIsEmpty() {
    List<Slot> occupiedSlots = new ArrayList<>();
    when(parkingLotService.getOccupiedSlots()).thenReturn(occupiedSlots);
    statusCommandExecutor.execute(new Command("status"));
    verify(parkingLotService).getOccupiedSlots();
    verify(outputPrinter).parkingLotEmpty();
  }

  @Test
  public void testCommandExecutionWithOccupiedParkingLot() {
    final Slot slot1 = new Slot(1);
    slot1.assignCar(new Car("reg-1", "white"));

    final Slot slot2 = new Slot(2);
    slot2.assignCar(new Car("reg-2", "blue"));

    when(parkingLotService.getOccupiedSlots()).thenReturn(Arrays.asList(slot1, slot2));

    statusCommandExecutor.execute(new Command("status"));

    verify(parkingLotService).getOccupiedSlots();
    verify(outputPrinter).statusHeader();
    verify(outputPrinter).printWithNewLine("1\t\treg-1\t\twhite");
    verify(outputPrinter).printWithNewLine("2\t\treg-2\t\tblue");
  }
}