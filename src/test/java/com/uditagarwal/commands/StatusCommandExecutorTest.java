package com.uditagarwal.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uditagarwal.model.Command;
import com.uditagarwal.model.Slot;
import com.uditagarwal.service.ParkingLotService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StatusCommandExecutorTest {
  private ParkingLotService parkingLotService;
  private StatusCommandExecutor statusCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    statusCommandExecutor = new StatusCommandExecutor(parkingLotService);
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
  public void testCommandExecution() {
    List<Slot> occupiedSlots = new ArrayList<>();
    when(parkingLotService.getOccupiedSlots()).thenReturn(occupiedSlots);
    statusCommandExecutor.execute(new Command("status"));
    verify(parkingLotService).getOccupiedSlots();
  }
}