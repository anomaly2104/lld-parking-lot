package com.uditagarwal.commands;

import static com.uditagarwal.commands.CommandConstants.CREATE_PARKING_LOT;

import com.uditagarwal.model.Command;

public class CreateParkingLotCommandExecutor implements CommandExecutor {

  @Override
  public boolean validate(Command command) {
    return false;
  }

  @Override
  public void execute(Command command) {

  }
}
