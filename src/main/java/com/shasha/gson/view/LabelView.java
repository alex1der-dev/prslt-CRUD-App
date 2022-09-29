package com.shasha.gson.view;

import com.shasha.gson.controller.LabelController;
import com.shasha.gson.model.Label;

import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final Scanner scanner = new Scanner(System.in);
    private final LabelController labelController = new LabelController();


    public void updateLabel() {
        System.out.println("Select label id:");
        List<Label> currentLabels = labelController.getAllLabels();
        System.out.println(currentLabels);
        Integer labelToUpdateId = scanner.nextInt();
        System.out.println("Enter new name:");
        scanner.nextLine();
        String labelToUpdateNewName = scanner.nextLine();

        Label updatedLabel = labelController.updateLabel(labelToUpdateId, labelToUpdateNewName);

        System.out.println("Updated label: " + updatedLabel);
    }

    public void addLabel() {
        System.out.println("Enter new label's name: ");
        String newLabelName = scanner.nextLine();
        Label newLabel = labelController.saveLabel(newLabelName);

        System.out.println("Created label: " + newLabel);
    }

    public void removeLabel(){
        System.out.println("Enter label's ID to delete: ");
        Integer labelToRemoveID = scanner.nextInt();
        labelController.removeLabel(labelToRemoveID);

    }

    public void showLabels(){
        for(Label label: labelController.getAllLabels()){
            System.out.println(label);
        }
    }


}
