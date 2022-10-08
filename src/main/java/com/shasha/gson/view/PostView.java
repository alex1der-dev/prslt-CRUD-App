package com.shasha.gson.view;

import com.shasha.gson.controller.ConsoleReader;
import com.shasha.gson.controller.LabelController;
import com.shasha.gson.controller.PostController;
import com.shasha.gson.model.Label;
import com.shasha.gson.model.Post;
import com.shasha.gson.model.PostStatus;

import java.util.*;

public class PostView {

    private final String[][] POST_SUBMENU = {
            {"1", "ADD"},
            {"2", "REMOVE"},
            {"3", "UPDATE CONTENT"},
            {"4", "UPDATE LABELS"},
            {"5", "SHOW ALL"},
            {"6", "SET POST STATUS TO ACTIVE"},
            {"7", "SET POST STATUS TO UNDER-REVIEW"},
            {"8", "SET POST STATUS TO DELETED"}

    };

    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final Scanner scanner = new Scanner(System.in);

    public void updatePostContent() {
        List<Post> currentPosts = postController.getAllPosts();
        System.out.println(currentPosts);
        System.out.println("Select post ID to update: ");
        Integer postContentToUpdateId = consoleReader.readNumericValue();
        System.out.println("Enter content: ");
        String postContentToUpdateNewContent = consoleReader.readTextValue();
        List<Label> postContentToUpdateLabels = postController.getPostById(postContentToUpdateId).getLabels();
        Post updatedPost = postController.updatePost(postContentToUpdateId, postContentToUpdateNewContent, postContentToUpdateLabels);
        System.out.println("Updated post: " + updatedPost);
    }

    public void showAllPosts() {
        List<Post> existingPosts = postController.getAllPosts();
        for(Post p : existingPosts) {
            System.out.println(p);
        }
    }

    public void addPost() {
        System.out.println("Enter new post's content: ");
        String newPostContent = consoleReader.readTextValue();
        List<Label> selectedLabels = pickUpLabels();
        Post newPost = postController.savePost(newPostContent, selectedLabels);
        System.out.println("Created post: " + newPost);
    }

    public void removePost() {
        System.out.println("Enter post's ID to delete: ");
        Integer postToRemoveID = consoleReader.readNumericValue();
        postController.removePost(postToRemoveID);
        System.out.println("Deleted post ID" + postToRemoveID);
    }

    public void updatePostLabels() {
        System.out.println("Enter post's ID to change labels: ");
        Integer postLabelsToUpdateID = consoleReader.readNumericValue();
        System.out.println("Enter post's new labels: ");

        List<Label> selectedLabels = pickUpLabels();
        String postContent = postController.getPostById(postLabelsToUpdateID).getContent();
        Post newPost = postController.savePost(postContent, selectedLabels);
        System.out.println("Updated post: " + newPost);
    }

    public void setPostStatusActive() {
        System.out.println("Enter post's ID to change status to active: ");
        Integer postStatusToUpdateID = consoleReader.readNumericValue();
        Post postStatusToUpdate = postController.getPostById(postStatusToUpdateID);
        postStatusToUpdate.setStatus(PostStatus.ACTIVE);
        System.out.println("Post status changed: " + postStatusToUpdate + " " + postStatusToUpdate.getStatus());
    }

    public void setPostStatusUnderReview() {
        System.out.println("Enter post's ID to change status to under review: ");
        Integer postStatusToUpdateID = consoleReader.readNumericValue();
        Post postStatusToUpdate = postController.getPostById(postStatusToUpdateID);
        postStatusToUpdate.setStatus(PostStatus.UNDER_REVIEW);
        System.out.println("Post status changed: " + postStatusToUpdate + " " + postStatusToUpdate.getStatus());

    }

    public void setPostStatusDeleted() {
        System.out.println("Enter post's ID to change status to deleted: ");
        Integer postStatusToUpdateID = consoleReader.readNumericValue();
        Post postStatusToUpdate = postController.getPostById(postStatusToUpdateID);
        postStatusToUpdate.setStatus(PostStatus.DELETED);
        System.out.println("Post status changed: " + postStatusToUpdate + " " + postStatusToUpdate.getStatus());
    }

    public void performPostMenu() {
        String choice = null;
        do {
            MainView mainView = new MainView();
            System.out.println("POST MENU:");
            mainView.displaySubMenu(POST_SUBMENU);
            System.out.println("Select a post-menu option: ");
            choice = consoleReader.readTextValue().toUpperCase();

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
                    showAllPosts();
                    break;

                case "6":
                    setPostStatusActive();
                    break;

                case "7":
                    setPostStatusUnderReview();
                    break;

                case "8":
                    setPostStatusDeleted();
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

    public List<Label> pickUpLabels() {
        List<Label> result = new ArrayList<>();
        List<Label> existingLabels = labelController.getAllLabels();
        System.out.println("Available labels" + existingLabels);
        System.out.println("Enter name of available labels or name for new one or \"-\" to stop: ");
        String labelName = consoleReader.readTextValue();
        while (!labelName.equals("-")) {
            for (Label l : existingLabels) {
                if (labelName.equals(l.getName())) {
                    result.add(l);
                    break;
                }
                if (!labelName.equals(l.getName()) && existingLabels.indexOf(l) == existingLabels.size() - 1) {
                    Label newLabel = labelController.saveLabel(labelName);
                    result.add(newLabel);
                }
            }
            existingLabels = labelController.getAllLabels();
            System.out.println("Available labels" + existingLabels);
            System.out.println("Enter name of available labels or name for new one or \"-\" to stop: ");
            labelName = consoleReader.readTextValue();
        }

        return result;
    }
}
