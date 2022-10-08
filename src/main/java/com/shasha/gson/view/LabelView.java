package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;
import com.shasha.gson.controller.LabelController;
import com.shasha.gson.model.Label;

import java.util.List;

public class LabelView {
    private final String[][] LABEL_SUBMENU = {
            {"1", "ADD"},
            {"2", "REMOVE"},
            {"3", "UPDATE"},
            {"4", "SHOW ALL"}

    };

    private final LabelController labelController = new LabelController();
    private final ConsoleReader consoleReader = new ConsoleReader();

    public void updateLabel() {
        System.out.println("Available labels :");
        List<Label> existingLabels = labelController.getAllLabels();
        System.out.println(existingLabels);
        System.out.println("Enter label's ID to update: ");
        Integer labelToUpdateId = consoleReader.readNumericValue();
        System.out.println("Enter label new name:");
        String labelToUpdateNewName = consoleReader.readTextValue();
        Label updatedLabel = labelController.updateLabel(labelToUpdateId, labelToUpdateNewName);
        System.out.println("Updated label: " + updatedLabel);
    }

    public void addLabel() {
        System.out.println("Enter new label's name: ");
        String newLabelName = consoleReader.readTextValue();
        Label newLabel = labelController.saveLabel(newLabelName);
        System.out.println("Created label: " + newLabel);
    }

    public void removeLabel() {
        System.out.println("Enter label's ID to delete: ");
        Integer labelToRemoveID = consoleReader.readNumericValue();
        labelController.removeLabel(labelToRemoveID);
        System.out.println("Removed label ID: " + labelToRemoveID);
    }

    public void showLabels() {
        for (Label label : labelController.getAllLabels()) {
            System.out.println(label);
        }
    }

    public void performLabelView() {
        String choice = null;
        do {
            MainView mainView = new MainView();
            System.out.println("LABEL MENU:");
            mainView.displaySubMenu(LABEL_SUBMENU);
            System.out.println("Select a label-menu option: ");
            choice = consoleReader.readTextValue().toUpperCase();
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

