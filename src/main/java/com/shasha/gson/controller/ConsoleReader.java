package com.shasha.gson.controller;

import java.util.Scanner;

public class ConsoleReader {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static long readNumericValue(String input) {
        while (true) {
            System.out.printf("Enter %s: %n", input);
            String value = SCANNER.nextLine();
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                System.err.println("Wrong input!");
            }
        }
    }

    public static String readTextValue(String input) {
        while (true) {
            System.out.printf("Enter %s: %n", input);
            String value = SCANNER.nextLine();
            if (!value.isBlank()) {
                return value;
            }
        }
    }

    public static String readUserName(String input) {
        while (true) {
            System.out.printf("Enter %s: %n", input);
            String value = SCANNER.nextLine();
            if (value.matches("^[a-zA-Z\\-.']*$")) {
                return value;
            }
        }
    }

    public static void closeScanner(){
        SCANNER.close();
    }
}
