package com.shasha.gson.view;

import com.shasha.gson.controller.PostController;
import com.shasha.gson.model.Post;

import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner scanner = new Scanner(System.in);
    private final PostController postController = new PostController();


    public void updatePost() {
        System.out.println("Select post id:");
        List<Post> currentPosts = postController.getAllPosts();
        System.out.println(currentPosts);
        Integer postToUpdateId = scanner.nextInt();
        System.out.println("Enter content:");
        scanner.nextLine();
        String postToUpdateNewName = scanner.nextLine();

        Post updatedPost = postController.updatePost(postToUpdateId, postToUpdateNewName);

        System.out.println("Updated post: " + updatedPost);
    }

    public void addPost() {
        System.out.println("Enter new post's content: ");
        String newPostContent = scanner.nextLine();

        Post newPost = postController.savePost(newPostContent);

        System.out.println("Created post: " + newPost);
    }

    public void removePost(){
        System.out.println("Enter post's ID to delete: ");
        Integer postToRemoveID = scanner.nextInt();
        postController.removePost(postToRemoveID);

    }


}
