package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;

import java.util.Scanner;

public class MainView {
    private final String[][] MAIN_MENU = {
            {"1", "LABEL"},
            {"2", "POST"},
            {"3", "WRITER"},
            {"Q", "QUIT"},
    };

    //private final LabelView labelView = new LabelView();
    //private final PostView postView = new PostView();

    private final ConsoleReader consoleReader = new ConsoleReader();

    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }

    public void displayMenu(String[][] menu) {
        for (String[] s : menu) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
    }

    public void displaySubMenu(String[][] subMenu) {
        for (String[] s : subMenu) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
        System.out.println("B. Back");
        System.out.println("Q. QUIT");
    }

//    public void showMainMenu() {
//        System.out.println("MAIN MENU:");
//
//        while (true) {
//            Integer choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//
//            }
//        }
//    }

    public void performMainMenu() {
        boolean isFinished = false;

        do {
            System.out.println("MAIN MENU");
            displayMenu(MAIN_MENU);
            String choice = consoleReader.readTextValue("your choice");
            switch (choice) {
                case "1":
                    try {
                        LabelView labelView = new LabelView();
                        labelView.performLabelView();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "2":
                    try {
                        PostView postView = new PostView();
                        postView.performPostMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "3":
                    try {
                       // writerView.performWriterMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;


                case "Q":

                    isFinished = true;

                    consoleReader.closeScanner();
                    break;
                default: {
                    System.err.println("Wrong option");
                }


            }

        } while (!isFinished);
    }

}
