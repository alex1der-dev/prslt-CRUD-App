package com.shasha.gson.controller;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner SCANNER = new Scanner(System.in);

    public Integer readNumericValue(String input) {
        while (true) {
            System.out.printf("Enter %s: %n", input);
            String value = SCANNER.nextLine();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Wrong input!");
            }
        }
    }

    public String readTextValue(String input) {
        while (true) {
            System.out.printf("Enter %s: %n", input);
            String value = SCANNER.nextLine();
            if (!value.isBlank()) {
                return value;
            }
        }
    }

    public String readUserName(String input) {
        while (true) {
            System.out.printf("Enter %s: %n", input);
            String value = SCANNER.nextLine();
            if (value.matches("^[a-zA-Z\\-.']*$")) {
                return value;
            }
        }
    }

    public void closeScanner(){
        SCANNER.close();
    }
}
