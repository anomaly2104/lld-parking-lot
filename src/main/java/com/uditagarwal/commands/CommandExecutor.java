package com.uditagarwal.commands;

import com.uditagarwal.model.Command;

/**
 * Command executor interface.
 */
public interface CommandExecutor {

  /**
   * Validates that whether a command is valid to be executed or not.
   *
   * @param command Command to be validated.
   * @return Boolean indicating whether command is valid or not.
   */
  public boolean validate(Command command);

  /**
   * Executes the command.
   *
   * @param command Command to be executed.
   */
  public void execute(Command command);
}
