package com.uditagarwal;

import static org.junit.Assert.assertEquals;

import com.uditagarwal.exception.InvalidCommandException;
import com.uditagarwal.exception.InvalidModeException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
  private InputStream sysInBackup;
  private PrintStream sysOutBackup;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Before
  public void setUp() throws Exception {
    sysInBackup = System.in; // backup System.in to restore it later
    sysOutBackup = System.out; // backup System.out to restore it later
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void tearDown() throws Exception {
    System.setIn(sysInBackup);
    System.setOut(sysOutBackup);
  }

  @Test
  public void testInteractiveMode() throws IOException {
    final String commands =
        "create_parking_lot 6\r\n"
            + "park KA-01-HH-1234 White\r\n"
            + "park KA-01-HH-9999 White\r\n"
            + "park KA-01-BB-0001 Black\r\n"
            + "park KA-01-HH-7777 Red\r\n"
            + "park KA-01-HH-2701 Blue\r\n"
            + "park KA-01-HH-3141 Black\r\n"
            + "leave 4\r\n"
            + "status\r\n"
            + "park KA-01-P-333 White\r\n"
            + "park DL-12-AA-9999 White\r\n"
            + "registration_numbers_for_cars_with_colour White\r\n"
            + "slot_numbers_for_cars_with_colour White\r\n"
            + "slot_number_for_registration_number KA-01-HH-3141\r\n"
            + "slot_number_for_registration_number MH-04-AY-1111\r\n"
            + "exit\r\n";

    final String expectedOutput =
        "Welcome to Go-Jek Parking lot.\n"
            + "Created a parking lot with 6 slots\n"
            + "Allocated slot number: 1\n"
            + "Allocated slot number: 2\n"
            + "Allocated slot number: 3\n"
            + "Allocated slot number: 4\n"
            + "Allocated slot number: 5\n"
            + "Allocated slot number: 6\n"
            + "Slot number 4 is free\n"
            + "Slot No.\tRegistration No.\tColor\n"
            + "1\t\tKA-01-HH-1234\t\tWhite\n"
            + "2\t\tKA-01-HH-9999\t\tWhite\n"
            + "3\t\tKA-01-BB-0001\t\tBlack\n"
            + "5\t\tKA-01-HH-2701\t\tBlue\n"
            + "6\t\tKA-01-HH-3141\t\tBlack\n"
            + "Allocated slot number: 4\n"
            + "Sorry, parking lot is full\n"
            + "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n"
            + "1, 2, 4\n"
            + "6\n"
            + "Not found\n"
            + "Thanks for using Go-Jek Parking lot service.\n";

    final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
    System.setIn(in);

    Main.main(new String[] {});
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void testStatusOfEmptyParkingLot() throws IOException {
    final String commands = "create_parking_lot 6\r\n" + "status\r\n" + "exit\r\n";
    final String expectedOutput =
        "Welcome to Go-Jek Parking lot.\n"
            + "Created a parking lot with 6 slots\n"
            + "Parking lot is empty\n"
            + "Thanks for using Go-Jek Parking lot service.\n";

    final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
    System.setIn(in);

    Main.main(new String[] {});
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test(expected = InvalidCommandException.class)
  public void testInvalidCommandParams() throws IOException {
    final String commands = "create_parking_lot 6 1\r\n";
    final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
    System.setIn(in);

    Main.main(new String[] {});
  }

  @Test
  public void testFileMode() throws IOException {
    final String expectedOutput =
        "Created a parking lot with 6 slots\n"
            + "Allocated slot number: 1\n"
            + "Allocated slot number: 2\n"
            + "Allocated slot number: 3\n"
            + "Allocated slot number: 4\n"
            + "Allocated slot number: 5\n"
            + "Allocated slot number: 6\n"
            + "Slot number 4 is free\n"
            + "Slot No.\tRegistration No.\tColor\n"
            + "1\t\tKA-01-HH-1234\t\tWhite\n"
            + "2\t\tKA-01-HH-9999\t\tWhite\n"
            + "3\t\tKA-01-BB-0001\t\tBlack\n"
            + "5\t\tKA-01-HH-2701\t\tBlue\n"
            + "6\t\tKA-01-HH-3141\t\tBlack\n"
            + "Allocated slot number: 4\n"
            + "Sorry, parking lot is full\n"
            + "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n"
            + "1, 2, 4\n"
            + "6\n"
            + "Not found\n";
    Main.main(new String[] {"file_input.txt"});
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void testFileModeWithInvalidFile() throws IOException {
    final String expectedOutput = "Invalid file given.\n";
    Main.main(new String[] {"some_random_file.txt"});
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test(expected = InvalidModeException.class)
  public void testInvalidMode() throws IOException {
    Main.main(new String[] {"file_input.txt", "some-other-input"});
  }
}
