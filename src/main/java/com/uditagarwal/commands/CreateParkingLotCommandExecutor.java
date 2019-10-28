package com.uditagarwal.commands;

import com.uditagarwal.model.Command;
import com.uditagarwal.model.ParkingLot;
import com.uditagarwal.model.parking.strategy.NaturalOrderingParkingStrategy;
import com.uditagarwal.service.ParkingLotService;
import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {

  public CreateParkingLotCommandExecutor(final ParkingLotService parkingLotService) {
    super(parkingLotService);
  }

  @Override
  public boolean validate(final Command command) {
    final List<String> params = command.getParams();
    if (params.size() != 1) {
      return false;
    }

    try {
      Integer.parseInt(params.get(0));
      return true;
    } catch (NumberFormatException exception) {
      return false;
    }
  }

  @Override
  public void execute(final Command command) {
    final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
    parkingLotService.createParkingLot(new ParkingLot(parkingLotCapacity), new NaturalOrderingParkingStrategy());
  }
}
