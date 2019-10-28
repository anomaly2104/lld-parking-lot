package com.uditagarwal.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.uditagarwal.model.Command;
import com.uditagarwal.model.parking.strategy.NaturalOrderingParkingStrategy;
import com.uditagarwal.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;

public class CreateParkingLotCommandExecutorTest {
  private ParkingLotService parkingLotService;
  private CreateParkingLotCommandExecutor createParkingLotCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    createParkingLotCommandExecutor = new CreateParkingLotCommandExecutor(parkingLotService);
  }

  @Test
  public void testValidCommand() {
    assertTrue(createParkingLotCommandExecutor.validate(new Command("create_parking_lot 6")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(createParkingLotCommandExecutor.validate(new Command("create_parking_lot")));
    assertFalse(createParkingLotCommandExecutor.validate(new Command("create_parking_lot abcd")));
  }

  @Test
  public void testCommandExecution() {
    createParkingLotCommandExecutor.execute(new Command("create_parking_lot 6"));
    verify(parkingLotService).createParkingLot(eq(6), any(NaturalOrderingParkingStrategy.class));
  }
}
