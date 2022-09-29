package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;

public class MainMenu extends Menu {

    final static String[][] MAIN_MENU = {
            {"1", "ADD"},
            {"2", "EDIT"},
            {"3", "VIEW"},
            {"4", "DELETE"},
            {"Q", "QUIT"},
    };

    ConsoleReader consoleReader = new ConsoleReader();
    LabelView labelView = new LabelView();
    AddMenu addMenu = new AddMenu();
    DeleteMenu deleteMenu = new DeleteMenu();
    EditMenu editMenu = new EditMenu();
    ViewMenu viewMenu = new ViewMenu();

    public void performMainMenu() {
        boolean isFinished = false;

        do {
            System.out.println("MAIN MENU");
            displayMenu(MAIN_MENU);
            String choice = consoleReader.readTextValue("your choice");
            switch (choice) {
                case "1":
                    try {
                        addMenu.performAddMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "2":
                    try {
                        editMenu.performEditMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "3":
                    try {
                        viewMenu.performViewMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "4":
                    try {
                        deleteMenu.performDeleteMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "Q":

                        isFinished = true;

                        ConsoleReader.closeScanner();
                 break;
                default: {
                    System.err.println("Wrong option");
                }


            }

        } while (!isFinished);
    }
}

