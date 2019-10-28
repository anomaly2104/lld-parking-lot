package com.uditagarwal;

import java.sql.SQLOutput;

public class OutputPrinter {

  public static void welcome() {
    printLine("Welcome to Go-Jek Parking lot.");
  }

  private static void printLine(String msg) {
    System.out.println(msg);
  }
}
