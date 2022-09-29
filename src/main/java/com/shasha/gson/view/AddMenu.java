package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;

import java.util.Scanner;

public class AddMenu {
    final static String[][] ADD_MENU = {
            {"1", "Label"},
            {"2", "Post"},
            {"3", "Writer"},
    };


    public void performAddMenu() {
        String choice = null;
        LabelView labelView = new LabelView();
        PostView postView = new PostView();

        do {
            System.out.println("ADD MENU:");
            Menu.displaySubMenu(ADD_MENU);
            choice = (ConsoleReader.readTextValue("your choice")).toUpperCase();

            switch (choice) {
                case "1":
                    labelView.addLabel();

                    break;

                case "2":
                    postView.addPost();
                    break;

                case "3":
                    System.out.println("writer added");
                    break;

                case "B":
                    //Menu.displayMenu(MainMenu.MAIN_MENU);
                    return;


                case "Q":
                    throw new IllegalArgumentException();
                default:
                    System.err.println("Wrong option");
                    break;
            }
        }
        while (true);
    }
}

