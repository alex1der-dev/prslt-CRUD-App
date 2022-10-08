package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;
import com.shasha.gson.controller.PostController;
import com.shasha.gson.controller.WriterController;
import com.shasha.gson.model.Label;
import com.shasha.gson.model.Post;
import com.shasha.gson.model.Writer;

import java.util.List;

public class MainView {
    private final String[][] MAIN_MENU = {
            {"1", "LABEL"},
            {"2", "POST"},
            {"3", "WRITER"},
            {"4", "WRITE A POST BY EXISTING WRITER"},
            {"5", "WRITE A POST BY NEW WRITER"},
            {"Q", "QUIT"},
    };

    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();
    private final WriterController writerController = new WriterController();
    private final PostController postController = new PostController();
    private final ConsoleReader consoleReader = new ConsoleReader();

    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }

    public void displayMenu(String[][] menu) {
        for (String[] s : menu) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
    }

    public void displaySubMenu(String[][] subMenu) {
        for (String[] s : subMenu) {
            System.out.printf("%s. %s%n", s[0], s[1]);
        }
        System.out.println("B. Back");
        System.out.println("Q. QUIT");
    }

    public void performMainMenu() {
        boolean isFinished = false;
        do {
            System.out.println("MAIN MENU");
            displayMenu(MAIN_MENU);
            System.out.println("Select a menu option: ");
            String choice = consoleReader.readTextValue();
            switch (choice) {
                case "1":
                    try {
                        labelView.performLabelView();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "2":
                    try {
                        postView.performPostMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "3":
                    try {
                        writerView.performWriterMenu();
                    } catch (IllegalArgumentException e) {
                        isFinished = true;
                    }
                    break;

                case "4":
                    writePostExistingWriter();
                    break;

                case "5":
                    writePostNewWriter();
                    break;

                case "Q":
                    isFinished = true;
                    consoleReader.close();
                    break;
                default: {
                    System.err.println("Wrong option");
                }
            }

        } while (!isFinished);
    }

    public void writePostExistingWriter() {
        System.out.println("Select a writer id to create a post: ");
        System.out.println("Available writers: ");
        writerView.showAllWriter();
        Integer writerID = consoleReader.readNumericValue();
        Writer writerToAddPost = writerView.getWriterById(writerID);
        System.out.println("Write a post: ");
        String writerPostContent = consoleReader.readTextValue();
        List<Label> writerPostLabels = postView.pickUpLabels();
        Post writerPost = postController.savePost(writerPostContent, writerPostLabels);
        writerToAddPost.setPosts(writerPost);
        System.out.println("Post " + writerPost + " is added by writer " + writerToAddPost);
    }

    public void writePostNewWriter() {

        writerView.addWriter();
        Writer addedWriter = writerView.getLastAddedWriter();

//        System.out.println("Enter new writer name: ");
//        String newWriterName = consoleReader.readWriterName();
//        System.out.println("Enter new writer last name: ");
//        String newWriterLastName = consoleReader.readWriterName();
//        Writer newWriter = writerController.saveWriter(newWriterName, newWriterLastName);

        System.out.println("Write a post: ");
        String newWriterPostContent = consoleReader.readTextValue();
        List<Label> newWriterPostLabels = postView.pickUpLabels();
        Post newWriterPost = postController.savePost(newWriterPostContent, newWriterPostLabels);
        addedWriter.setPosts(newWriterPost);
        System.out.println("Post " + newWriterPost + " is added by writer " + addedWriter);
    }


}
