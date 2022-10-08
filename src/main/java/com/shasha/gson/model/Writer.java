package com.shasha.gson.model;

import java.util.List;

public class Writer {

    private Integer id;
    private String name;
    private String lastName;
    private List<Post> posts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(Post post) {
        this.getPosts().add(post);
    }

    public String toString() {
        return getName() + " " + getLastName();
    }
}
