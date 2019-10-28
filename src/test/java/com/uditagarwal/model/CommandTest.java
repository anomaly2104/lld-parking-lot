package com.uditagarwal.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.uditagarwal.exception.InvalidCommandException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class CommandTest {

  @Test
  public void testCommandParsingFromInput() {
    validateCommandParsing("my_command 1 2 3", "my_command", Arrays.asList("1", "2", "3"));
    validateCommandParsing("my_command   1  2 ", "my_command", Arrays.asList("1", "2"));
    validateCommandParsing("my_command", "my_command", Collections.emptyList());
    validateCommandParsing("  my_command     ", "my_command", Collections.emptyList());
  }

  @Test(expected = InvalidCommandException.class)
  public void testCommandParsingFromInputHavingOnlySpaces() {
    Command command = new Command("   ");
  }

  @Test(expected = InvalidCommandException.class)
  public void testCommandParsingFromEmptyInput() {
    Command command = new Command("");
  }

  /**
   * Helper method to validate command parsing.
   *
   * @param input Input line.
   * @param expectedCommandName Expected command name from input.
   * @param expectedParams Expected command params from inout.
   */
  private void validateCommandParsing(
      final String input, final String expectedCommandName, final List expectedParams) {
    Command command = new Command(input);
    assertNotNull(command);
    assertEquals(expectedCommandName, command.getCommandName());
    assertEquals(expectedParams, command.getParams());
  }
}
