package com.uditagarwal;

import com.uditagarwal.commands.CommandExecutorFactory;
import com.uditagarwal.exception.InvalidModeException;
import com.uditagarwal.mode.FileMode;
import com.uditagarwal.mode.InteractiveMode;
import com.uditagarwal.service.ParkingLotService;
import java.io.IOException;

public class Main {
  public static void main(final String[] args) throws IOException {
    final OutputPrinter outputPrinter = new OutputPrinter();
    final ParkingLotService parkingLotService = new ParkingLotService();
    final CommandExecutorFactory commandExecutorFactory =
        new CommandExecutorFactory(parkingLotService);

    if (isInteractiveMode(args)) {
      new InteractiveMode(commandExecutorFactory, outputPrinter).process();
    } else if (isFileInputMode(args)) {
      new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
    } else {
      throw new InvalidModeException();
    }
  }

  private static boolean isFileInputMode(final String[] args) {
    return args.length == 1;
  }

  private static boolean isInteractiveMode(final String[] args) {
    return args.length == 0;
  }
}
