package com.uditagarwal.commands;

import com.uditagarwal.model.Command;
import com.uditagarwal.model.ParkingLot;
import com.uditagarwal.model.parking.strategy.NaturalOrderingParkingStrategy;
import com.uditagarwal.service.ParkingLotService;
import com.uditagarwal.validator.IntegerValidator;
import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "create_parking_lot";

  public CreateParkingLotCommandExecutor(final ParkingLotService parkingLotService) {
    super(parkingLotService);
  }

  @Override
  public boolean validate(final Command command) {
    final List<String> params = command.getParams();
    if (params.size() != 1) {
      return false;
    }
    return IntegerValidator.isInteger(params.get(0));
  }

  @Override
  public void execute(final Command command) {
    final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
    parkingLotService.createParkingLot(new ParkingLot(parkingLotCapacity), new NaturalOrderingParkingStrategy());
  }
}
