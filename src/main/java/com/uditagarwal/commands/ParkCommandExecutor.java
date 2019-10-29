package com.uditagarwal.commands;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.exception.NoFreeSlotAvailableException;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "park";

  public ParkCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 2;
  }

  @Override
  public void execute(final Command command) {
    final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
    try {
      final Integer slot = parkingLotService.park(car);
      outputPrinter.printWithNewLine("Allocated slot number: " + slot);
    } catch (NoFreeSlotAvailableException exception) {
      outputPrinter.parkingLotFull();
    }
  }
}
