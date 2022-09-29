package com.shasha.gson.controller;

import com.shasha.gson.model.Label;
import com.shasha.gson.model.Post;
import com.shasha.gson.repository.PostRepository;
import com.shasha.gson.repository.gson.GsonPostRepositoryImpl;

import java.util.List;

public class PostController {
    private final PostRepository postRepository = new GsonPostRepositoryImpl();


    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post updatePost(Integer id, String content) {
        Post postToUpdate = new Post(id,content);
        postToUpdate.setId(id);
        postToUpdate.setContent(content);

        return postRepository.update(postToUpdate);
    }

    public Post savePost(String content) {
        Post post = new Post(null, content);
        return postRepository.save(post);
    }

    public void removePost(Integer id){
        postRepository.deleteById(id);
    }
}
