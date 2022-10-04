package com.shasha.gson.controller;

import com.shasha.gson.model.Label;
import com.shasha.gson.model.Post;
import com.shasha.gson.model.Writer;
import com.shasha.gson.repository.WriterRepository;
import com.shasha.gson.repository.gson.GsonWriterRepositoryImpl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository = new GsonWriterRepositoryImpl();


    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public Writer updateWriter(Integer id, String name, String lastName, List<Post> posts) {
        Writer writerToUpdate = new Writer();
        writerToUpdate.setId(id);
        writerToUpdate.setName(name);
        writerToUpdate.setLastName(lastName);
        writerToUpdate.setPosts(posts);

        return writerRepository.update(writerToUpdate);
    }

    public Writer saveWriter(String name, String lastName, List<Post> posts) {
        Writer writer = new Writer();
        writer.setName(name);
        writer.setLastName(lastName);
        writer.setPosts(posts);

        return writerRepository.save(writer);
    }

    public void removeWriter(Integer id){
        writerRepository.deleteById(id);
    }
}
