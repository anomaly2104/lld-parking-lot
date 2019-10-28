package com.uditagarwal;

import com.uditagarwal.exception.InvalidModeException;

public class Main {
  public static void main(String[] args) {
    OutputPrinter.welcome();

    if (isInteractiveMode(args)) {

    } else if (isFileInputMode(args)) {

    } else {
      throw new InvalidModeException();
    }
  }

  private static boolean isFileInputMode(String[] args) {
    return args.length == 1;
  }

  private static boolean isInteractiveMode(String[] args) {
    return args.length == 0;
  }
}
