package com.shasha.gson.controller;

import com.shasha.gson.model.Label;
import com.shasha.gson.model.Post;
import com.shasha.gson.model.PostStatus;
import com.shasha.gson.repository.PostRepository;
import com.shasha.gson.repository.gson.GsonPostRepositoryImpl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class PostController {
    private final PostRepository postRepository = new GsonPostRepositoryImpl();


    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post getPostById(Integer id) {
        return postRepository.getById(id);
    }

    public Post updatePost(Integer id, String content, List<Label> labels) {
        Post postToUpdate = new Post(null);
        postToUpdate.setId(id);
        postToUpdate.setContent(content);
        postToUpdate.setLabels(labels);
        postToUpdate.setUpdated(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        return postRepository.update(postToUpdate);
    }

    public Post savePost(String content,List<Label>labels) {
        Post post = new Post(null);
        post.setContent(content);
        post.setLabels(labels);
        post.setStatus(PostStatus.ACTIVE);
        post.setCreated(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        post.setUpdated(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        return postRepository.save(post);
    }

    public void removePost(Integer id){
        postRepository.deleteById(id);
    }
}
