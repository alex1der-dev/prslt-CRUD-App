package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;

public class ViewMenu {
    final static String[][] VIEW_MENU = {
            {"1", "Label"},
            {"2", "Post"},
            {"3", "Writer"},
    };

    public void performViewMenu() {
        String choice = null;
        LabelView labelView = new LabelView();
        PostView postView = new PostView();

        do {
            System.out.println("VIEW MENU:");
            Menu.displaySubMenu(VIEW_MENU);
            choice = (ConsoleReader.readTextValue("your choice")).toUpperCase();

            switch (choice) {
                case "1":
                    System.out.println("show labels");
                    labelView.showLabels();
                    break;

                case "2":
                    System.out.println("show posts");
                    break;

                case "3":
                    System.out.println("show writers");
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
