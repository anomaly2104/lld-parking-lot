package com.uditagarwal.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;

public class ExitCommandExecutorTest {
  private ParkingLotService parkingLotService;
  private OutputPrinter outputPrinter;
  private ExitCommandExecutor exitCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    outputPrinter = mock(OutputPrinter.class);
    exitCommandExecutor = new ExitCommandExecutor(parkingLotService, outputPrinter);
  }

  @Test
  public void testValidCommand() {
    assertTrue(exitCommandExecutor.validate(new Command("exit")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(exitCommandExecutor.validate(new Command("exit 1")));
    assertFalse(exitCommandExecutor.validate(new Command("exit 1 2")));
    assertFalse(exitCommandExecutor.validate(new Command("exit a")));
  }

  @Test
  public void textCommandExecution() {
    exitCommandExecutor.execute(new Command("exit"));
    verify(outputPrinter).end();
  }
}
