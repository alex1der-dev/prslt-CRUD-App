package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;
import com.shasha.gson.controller.WriterController;
import com.shasha.gson.model.Writer;

import java.util.List;

public class WriterView {

    private final String[][] POST_SUBMENU = {
            {"1", "ADD"},
            {"2", "REMOVE"},
            {"3", "UPDATE"},
            {"4", "SHOW ALL"},
    };

    private final WriterController writerController = new WriterController();
    private final ConsoleReader consoleReader = new ConsoleReader();

    public void showAllWriter() {
        List<Writer> existingWriters = writerController.getAllWriters();
        System.out.println(existingWriters);
    }

    public Writer addWriter() {
        System.out.println("Enter new writer's name: ");
        String newWriterName = consoleReader.readWriterName();
        System.out.println("Enter new writer's last name: ");
        String newWriterLastName = consoleReader.readWriterName();
        Writer newWriter = writerController.saveWriter(newWriterName, newWriterLastName);
        System.out.println("Saved writer: " + newWriter);
        return newWriter;
    }

    public void removeWriter() {
        System.out.println("Enter writer's ID to delete: ");
        Integer writerToRemoveID = consoleReader.readNumericValue();
        writerController.removeWriter(writerToRemoveID);
        System.out.println("Removed writer ID: " + writerToRemoveID);
    }

    public void updateWriter() {
        System.out.println("Enter writer's ID to update: ");
        Integer writerToUpdateID = consoleReader.readNumericValue();
        System.out.println(writerController.getAllWriters());
        System.out.println("Enter writer's name to update (leave blank to keep unchanged): ");
        String writerInputName = consoleReader.readWriterName();
        String writerUpdatedName = writerInputName.equals("")
                ? writerController.getWriterById(writerToUpdateID).getName()
                : writerInputName;
        System.out.println("Enter writer's last name to update (leave blank to keep unchanged): ");
        String writerInputLastName = consoleReader.readWriterName();
        String writerUpdatedLastName = writerInputLastName.equals("")
                ? writerController.getWriterById(writerToUpdateID).getLastName()
                : writerInputLastName;
        Writer updatedWriter = writerController.updateWriter(writerToUpdateID, writerUpdatedName, writerUpdatedLastName);
        System.out.println("Updated writer: " + updatedWriter);
    }

    public void performWriterMenu() {
        String choice = null;
        do {
            MainView mainView = new MainView();
            System.out.println("POST MENU:");
            mainView.displaySubMenu(POST_SUBMENU);
            System.out.println("Select a writer-menu option: ");
            choice = consoleReader.readTextValue();

            switch (choice) {
                case "1":
                    addWriter();
                    break;

                case "2":
                    removeWriter();
                    break;

                case "3":
                    updateWriter();
                    break;

                case "4":
                    showAllWriter();
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

    public Writer getWriterById(Integer id) {
        return writerController.getWriterById(id);
    }

    public Writer getLastAddedWriter() {
        List<Writer> allWriters = writerController.getAllWriters();
        return allWriters.get(allWriters.size()-1);
    }
}
