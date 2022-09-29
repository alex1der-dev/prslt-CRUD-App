package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;

public class DeleteMenu {
    final static String[][] DELETE_MENU = {
            {"1", "Label"},
            {"2", "Post"},
            {"3", "Writer"},
    };

    public void performDeleteMenu() {
        String choice = null;
        LabelView labelView = new LabelView();
        PostView postView = new PostView();

        do {
            System.out.println("DELETE MENU:");
            Menu.displaySubMenu(DELETE_MENU);
            choice = (ConsoleReader.readTextValue("your choice")).toUpperCase();

            switch (choice) {
                case "1":
                    labelView.removeLabel();
                    break;

                case "2":
                    postView.removePost();
                    break;

                case "3":
                    System.out.println("writer deleted");
                    break;

                case "B":
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
