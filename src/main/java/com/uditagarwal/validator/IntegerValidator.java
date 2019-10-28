package com.uditagarwal.validator;

public class IntegerValidator {

  /**
   * Validates that whether a string is a integer or not. Does this by checking if it is
   * successfully able to convert the input string to integer or not.
   *
   * @param input Input string.
   * @return Boolean indicating whether a input is integer or not.
   */
  public static boolean isInteger(final String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException exception) {
      return false;
    }
  }
}
