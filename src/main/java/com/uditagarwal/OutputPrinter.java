package com.uditagarwal;

import java.sql.SQLOutput;

public class OutputPrinter {

  public void usage() {
    //TODO: Print usage.
  }

  public void welcome() {
    printWithNewLine("Welcome to Go-Jek Parking lot.");
  }

  public void endInteractive() {
    printWithNewLine("Thanks for using Go-Jek Parking lot service.");
  }

  public void notFound() {
    printWithNewLine("Not found");
  }

  public void statusHeader() {
    printWithNewLine("Slot No.\tRegistration No.\tColor");
  }

  public void parkingLotFull() {
    printWithNewLine("Sorry, parking lot is full");
  }

  public void parkingLotEmpty() {
    printWithNewLine("Parking lot is empty");
  }

  public void invalidFile() {
    printWithNewLine("Invalid file given.");
  }

  public void printWithNewLine(final String msg) {
    System.out.println(msg);
  }
}
