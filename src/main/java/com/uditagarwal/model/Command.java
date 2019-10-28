package com.uditagarwal.model;

import com.uditagarwal.exception.InvalidCommandException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

  public static final String SPACE = " ";
  private String commandName;
  private List<String> params;

  public String getCommandName() {
    return commandName;
  }

  public List<String> getParams() {
    return params;
  }

  public Command(final String inputLine) {
    final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
        .map(String::trim)
        .filter(token -> (token.length() > 0)).collect(Collectors.toList());

    if (tokensList.size() == 0) {
      throw new InvalidCommandException();
    }

    commandName = tokensList.get(0).toLowerCase();
    tokensList.remove(0);
    params = tokensList;
  }

}
