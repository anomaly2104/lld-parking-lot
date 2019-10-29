package com.uditagarwal;

import java.sql.SQLOutput;

public class OutputPrinter {

  public static void usage() {
    //TODO: Print usage.
  }

  public static void endInteractive() {
    //TODO: Print end of interactive mode.
  }

  public static void invalidFile() {
    printLine("Invalid file given.");
  }

  public static void welcome() {
    printLine("Welcome to Go-Jek Parking lot.");
  }

  private static void printLine(String msg) {
    System.out.println(msg);
  }

  public void printWithNewLine(final String msg) {
    System.out.println(msg);
  }
}
