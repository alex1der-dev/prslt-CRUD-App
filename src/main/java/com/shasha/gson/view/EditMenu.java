package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;

public class EditMenu {
    final static String[][] EDIT_MENU = {
            {"1", "Label"},
            {"2", "Post"},
            {"3", "Writer"}
    };

    public void performEditMenu() {
        String choice = null;
        LabelView labelView = new LabelView();
        PostView postView = new PostView();

        do {
            System.out.println("EDIT MENU:");
            Menu.displaySubMenu(EDIT_MENU);
            choice = (ConsoleReader.readTextValue("your choice")).toUpperCase();

            switch (choice) {
                case "1":
                    labelView.updateLabel();
                    break;

                case "2":
                    postView.updatePost();
                    break;

                case "3":
                    System.out.println("writer updated");
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
