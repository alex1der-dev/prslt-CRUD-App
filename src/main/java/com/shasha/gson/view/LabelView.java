package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;
import com.shasha.gson.controller.LabelController;
import com.shasha.gson.model.Label;

import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final String[][] LABEL_SUBMENU = {
            {"1", "ADD"},
            {"2", "REMOVE"},
            {"3", "UPDATE"},
            {"4", "SHOW ALL"}

    };
    //private final Scanner scanner = new Scanner(System.in);
    private final LabelController labelController = new LabelController();
//private final MainView mainView = new MainView();
private final ConsoleReader consoleReader = new ConsoleReader();

    public void updateLabel() {
        System.out.println("Select label id:");
        List<Label> currentLabels = labelController.getAllLabels();
        System.out.println(currentLabels);
        //Integer labelToUpdateId = scanner.nextInt();
        Integer labelToUpdateId = consoleReader.readNumericValue("label ID");
        System.out.println("Enter new name:");
//        scanner.nextLine();
//        String labelToUpdateNewName = scanner.nextLine();
        String labelToUpdateNewName = consoleReader.readTextValue("new label's name");

        Label updatedLabel = labelController.updateLabel(labelToUpdateId, labelToUpdateNewName);

        System.out.println("Updated label: " + updatedLabel);
    }

    public void addLabel() {
        System.out.println("Enter new label's name: ");
//        String newLabelName = scanner.nextLine();
        String newLabelName = consoleReader.readTextValue("new label's name");
        Label newLabel = labelController.saveLabel(newLabelName);

        System.out.println("Created label: " + newLabel);
    }

    public void removeLabel() {
        System.out.println("Enter label's ID to delete: ");
//        Integer labelToRemoveID = scanner.nextInt();
        Integer labelToRemoveID = consoleReader.readNumericValue("label's ID to delete");
        labelController.removeLabel(labelToRemoveID);


    }

    public void showLabels() {
        for (Label label : labelController.getAllLabels()) {
            System.out.println(label);
        }
    }


    public void performLabelView() {
        String choice = null;


        do {
            System.out.println("LABEL MENU:");
            MainView mainView = new MainView();
            mainView.displaySubMenu(LABEL_SUBMENU);
            choice= consoleReader.readTextValue("your choice").toUpperCase();


            switch (choice) {
                case "1":
                    addLabel();
                    break;

                case "2":
                    removeLabel();
                    break;

                case "3":
                    updateLabel();
                    break;

                case "4":
                    showLabels();
                    break;

                case "B":
                    mainView.performMainMenu();
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

