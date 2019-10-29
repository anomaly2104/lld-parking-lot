package com.uditagarwal.commands;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;
import java.util.List;
import java.util.stream.Collectors;

public class ColorToRegNumberCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "registration_numbers_for_cars_with_colour";

  public ColorToRegNumberCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 1;
  }

  @Override
  public void execute(final Command command) {
    final List<Car> carsForColor = parkingLotService.getCarsForColor(command.getParams().get(0));
    if (carsForColor.isEmpty()) {
      outputPrinter.printWithNewLine("Not found");
    } else {
      final String result =
          carsForColor.stream().map(Car::getRegistrationNumber).collect(Collectors.joining(", "));
      outputPrinter.printWithNewLine(result);
    }
  }
}
