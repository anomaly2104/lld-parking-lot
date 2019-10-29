package com.uditagarwal.commands;

import com.uditagarwal.OutputPrinter;
import com.uditagarwal.model.Command;
import com.uditagarwal.model.Slot;
import com.uditagarwal.service.ParkingLotService;
import java.util.List;
import java.util.stream.Collectors;

public class ColorToSlotNumberCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

  public ColorToSlotNumberCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 1;
  }

  @Override
  public void execute(final Command command) {
    final List<Slot> slotsForColor = parkingLotService.getSlotsForColor(command.getParams().get(0));
    if (slotsForColor.isEmpty()) {
      outputPrinter.printWithNewLine("Not found");
    } else {
      final String result =
          slotsForColor.stream()
              .map(slot -> slot.getSlotNumber().toString())
              .collect(Collectors.joining(", "));
      outputPrinter.printWithNewLine(result);
    }
  }
}
