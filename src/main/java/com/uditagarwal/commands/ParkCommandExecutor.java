package com.uditagarwal.commands;

import com.uditagarwal.model.Car;
import com.uditagarwal.model.Command;
import com.uditagarwal.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor {

  public ParkCommandExecutor(ParkingLotService parkingLotService) {
    super(parkingLotService);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 2;
  }

  @Override
  public void execute(final Command command) {
    Car car = new Car(command.getParams().get(0), command.getParams().get(1));
    parkingLotService.park(car);
  }
}
