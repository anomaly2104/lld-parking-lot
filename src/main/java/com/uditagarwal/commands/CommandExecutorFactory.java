package com.uditagarwal.commands;

import static com.uditagarwal.commands.CommandConstants.CREATE_PARKING_LOT;
import static com.uditagarwal.commands.CommandConstants.PARK;

import com.uditagarwal.exception.InvalidCommandException;
import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory to get correct {@link CommandExecutor} from a given command.
 */
public class CommandExecutorFactory {
  Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

  public CommandExecutorFactory(final ParkingLotService parkingLotService) {
    commands.put(CREATE_PARKING_LOT, new CreateParkingLotCommandExecutor(parkingLotService));
    commands.put(PARK, new ParkCommandExecutor(parkingLotService));
  }

  public CommandExecutor getCommandExecutor(final Command command) {
    CommandExecutor commandExecutor = commands.get(command.getCommandName());
    if (command == null) {
      throw new InvalidCommandException();
    }
    return commandExecutor;
  }
}
