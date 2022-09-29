package com.shasha.gson.view;

public class Menu {

    public static void displayMenu(String[][] menu) {
        for (String[] s : menu) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
    }

    public static void displaySubMenu(String[][] subMenu) {
        for (String[] s : subMenu) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
        System.out.println("B. Back");
        System.out.println("Q. QUIT");
    }
}
