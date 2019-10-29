package com.uditagarwal.commands;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.Command;
import com.uditagarwal.model.Slot;
import com.uditagarwal.service.ParkingLotService;
import java.util.List;

public class StatusCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "status";

  public StatusCommandExecutor(final ParkingLotService parkingLotService,
      final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().isEmpty();
  }

  @Override
  public void execute(Command command) {
    final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();

    System.out.println("Slot No.\tRegistration No.\tColor");

    for (Slot slot : occupiedSlots) {
      final Car parkedCar = slot.getParkedCar();
      System.out.println(
          slot.getSlotNumber()
              + "\t\t" + parkedCar.getRegistrationNumber()
              + "\t\t" + parkedCar.getColor());
    }
  }
}
