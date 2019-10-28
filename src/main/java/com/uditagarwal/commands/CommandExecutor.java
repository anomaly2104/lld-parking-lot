package com.uditagarwal.commands;

import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;

/**
 * Command executor interface.
 */
public abstract class CommandExecutor {
  protected ParkingLotService parkingLotService;

  public CommandExecutor(ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  /**
   * Validates that whether a command is valid to be executed or not.
   *
   * @param command Command to be validated.
   * @return Boolean indicating whether command is valid or not.
   */
  public abstract boolean validate(Command command);

  /**
   * Executes the command.
   *
   * @param command Command to be executed.
   */
  public abstract void execute(Command command);
}
