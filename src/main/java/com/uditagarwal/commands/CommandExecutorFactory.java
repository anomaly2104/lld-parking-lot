package com.uditagarwal.commands;

import com.uditagarwal.exception.InvalidCommandException;
import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;
import java.util.HashMap;
import java.util.Map;

/** Factory to get correct {@link CommandExecutor} from a given command. */
public class CommandExecutorFactory {
  Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

  public CommandExecutorFactory(final ParkingLotService parkingLotService) {
    commands.put(
        CreateParkingLotCommandExecutor.COMMAND_NAME,
        new CreateParkingLotCommandExecutor(parkingLotService));
    commands.put(ParkCommandExecutor.COMMAND_NAME, new ParkCommandExecutor(parkingLotService));
    commands.put(LeaveCommandExecutor.COMMAND_NAME, new LeaveCommandExecutor(parkingLotService));
  }

  public CommandExecutor getCommandExecutor(final Command command) {
    CommandExecutor commandExecutor = commands.get(command.getCommandName());
    if (commandExecutor == null) {
      throw new InvalidCommandException();
    }
    return commandExecutor;
  }
}
