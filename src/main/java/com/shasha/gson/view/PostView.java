package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;
import com.shasha.gson.controller.LabelController;
import com.shasha.gson.controller.PostController;
import com.shasha.gson.model.Label;
import com.shasha.gson.model.Post;

import java.util.*;

public class PostView extends MainView {
    private final String[][] POST_SUBMENU = {
            {"1", "ADD"},
            {"2", "REMOVE"},
            {"3", "UPDATE CONTENT"},
            {"4", "UPDATE LABELS"},
            {"4", "SHOW ALL"}

    };
    private final Scanner scanner = new Scanner(System.in);
    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
ConsoleReader consoleReader = new ConsoleReader();

    public void updatePostContent() {
        List<Post> currentPosts = postController.getAllPosts();
        System.out.println(currentPosts);
        System.out.println("Select post id:");
        Integer postContentToUpdateId = scanner.nextInt();
        System.out.println("Enter content:");
        String postContentToUpdateNewContent = scanner.nextLine();

        List<Label> postContentToUpdateLabels = postController.getPostById(postContentToUpdateId).getLabels();

        Post updatedPost = postController.updatePost(postContentToUpdateId, postContentToUpdateNewContent, postContentToUpdateLabels);

        System.out.println("Updated post: " + updatedPost);
    }

    public void getPostById(Integer id) {
        List<Post> currentPosts = postController.getAllPosts();

    }

    public void addPost() {
        System.out.println("Enter new post's content: ");
        String newPostContent = scanner.nextLine();

//        List<Label> selectedLabels = selectLabels();
        List<Label> selectedLabels = pickUpLabels();

        Post newPost = postController.savePost(newPostContent, selectedLabels);

        System.out.println("Created post: " + newPost);
    }

    public void removePost() {
        System.out.println("Enter post's ID to delete: ");
        Integer postToRemoveID = scanner.nextInt();
        postController.removePost(postToRemoveID);

    }

    public void updatePostLabels() {
        System.out.println("Enter post's ID to change labels: ");
        Integer postLabelsToUpdateID = scanner.nextInt();

        System.out.println("Enter new post's labels: ");

        List<Label> selectedLabels = selectLabels();


        Post postLabelsToUpdate = postController.getPostById(postLabelsToUpdateID);
        postLabelsToUpdate.setLabels(selectedLabels);

        System.out.println("Updated post: " + postLabelsToUpdate);


    }


    public void performPostMenu() {
        String choice = null;


        do {
            System.out.println("POST MENU:");
            displaySubMenu(POST_SUBMENU);
            choice = super.getConsoleReader().readTextValue("your choice").toUpperCase();


            switch (choice) {
                case "1":
                    addPost();
                    break;

                case "2":
                    removePost();
                    break;

                case "3":
                    updatePostContent();
                    break;

                case "4":
                    updatePostLabels();
                    break;

                case "5":
                    System.out.println("show posts");
                    break;

                case "B":
                    super.performMainMenu();
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

    private List<Label> selectLabels() {
        List<Label> result = new ArrayList<>();
        List<Label> allLabels = labelController.getAllLabels();

        System.out.println(allLabels);

        System.out.println("Select label ID: ");

        Integer labelId = scanner.nextInt();
        while (labelId != -1) {
            Integer finalLabelId = labelId;
            Label selectedLabel = allLabels.stream().filter(s -> s.getId().equals(finalLabelId)).findFirst().orElse(null);

            if (Objects.nonNull(selectedLabel)) {
                result.add(selectedLabel);
            }
            labelId = scanner.nextInt();
        }

        return result;
    }

    private List<Label> pickUpLabels() {
        List<Label> result = new ArrayList<>();
        List<Label> allLabels = labelController.getAllLabels();

        String labelName = consoleReader.readTextValue("input label name");

        while (!labelName.equals("-")) {
             for(Label l : allLabels){
                 if(labelName.equals(l.getName())){
                     result.add(l);
                     break;
                 }

                 if(!labelName.equals(l.getName()) && allLabels.indexOf(l)== allLabels.size()-1) {
                     Label newLabel = labelController.saveLabel(labelName);
                     result.add(newLabel);

                 }
             }
             allLabels=labelController.getAllLabels();
             labelName= consoleReader.readTextValue("input label name");
        }

        return result;

    }
}
