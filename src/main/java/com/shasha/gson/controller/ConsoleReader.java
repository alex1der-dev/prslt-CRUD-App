package com.shasha.gson.controller;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner SCANNER = new Scanner(System.in);

    public Integer readNumericValue() {
        while (true) {
            String value = SCANNER.nextLine();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Wrong input!");
            }
        }
    }

    public String readTextValue() {
        while (true) {
            String value = SCANNER.nextLine();
            if (!value.isBlank()) {
                return value;
            }
        }
    }

    public String readWriterName() {
        while (true) {
            String value = SCANNER.nextLine();
            if (value.matches("^[a-zA-Z\\-.']*$")) {
                return value;
            }
        }
    }

    public void close() {
        SCANNER.close();
    }
}
