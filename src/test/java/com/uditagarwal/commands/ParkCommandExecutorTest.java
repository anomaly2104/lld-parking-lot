package com.uditagarwal.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.exception.NoFreeSlotAvailableException;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ParkCommandExecutorTest {
  private ParkingLotService parkingLotService;
  private OutputPrinter outputPrinter;
  private ParkCommandExecutor parkCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    outputPrinter = mock(OutputPrinter.class);
    parkCommandExecutor = new ParkCommandExecutor(parkingLotService, outputPrinter);
  }

  @Test
  public void testValidCommand() {
    assertTrue(parkCommandExecutor.validate(new Command("park test-command-number white")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(parkCommandExecutor.validate(new Command("park")));
    assertFalse(parkCommandExecutor.validate(new Command("park test-car-number")));
    assertFalse(parkCommandExecutor.validate(new Command("park test-car-number white abcd")));
  }

  @Test
  public void testCommandExecutionWhenParkingSucceeds() {
    when(parkingLotService.park(any())).thenReturn(1);
    parkCommandExecutor.execute(new Command("park test-car-number white"));

    final ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
    verify(parkingLotService).park(argument.capture());
    assertEquals("test-car-number", argument.getValue().getRegistrationNumber());
    assertEquals("white", argument.getValue().getColor());

    verify(outputPrinter).printWithNewLine("Allocated slot number: 1");
  }

  @Test
  public void testCommandExecutionWhenParkingIsFull() {
    when(parkingLotService.park(any())).thenThrow(new NoFreeSlotAvailableException());
    parkCommandExecutor.execute(new Command("park test-car-number white"));

    final ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
    verify(parkingLotService).park(argument.capture());
    assertEquals("test-car-number", argument.getValue().getRegistrationNumber());
    assertEquals("white", argument.getValue().getColor());

    verify(outputPrinter).parkingLotFull();
  }
}
