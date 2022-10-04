package com.shasha.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shasha.gson.model.Writer;
import com.shasha.gson.repository.WriterRepository;
import com.shasha.gson.repository.WriterRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GsonWriterRepositoryImpl implements WriterRepository {

    private static final String WRITERS_FILE_PATH = "src/main/resources/writers.json";
    private static final Gson GSON = new Gson();


    private List<Writer> getWritersInternal() {
        try {
            String json = Files.readString(Paths.get(WRITERS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<Writer>>() {
            }.getType();

            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }


    private void writeWritersInternal(List<Writer> writers) {
        String json = GSON.toJson(writers);
        try {
            Files.writeString(Paths.get(WRITERS_FILE_PATH), json);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private Integer generateId(List<Writer> writers) {
        Writer maxIdWriter = writers.stream().max(Comparator.comparing(Writer::getId)).orElse(null);
        return Objects.nonNull(maxIdWriter) ? maxIdWriter.getId() + 1 : 1;
    }

    @Override
    public Writer getById(Integer id) {
        return getWritersInternal().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        try {
            String json = Files.readString(Paths.get(WRITERS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<Writer>>() {
            }.getType();

            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Writer save(Writer writerToSave) {
        List<Writer> existingWriters = getWritersInternal();
        writerToSave.setId(generateId(existingWriters));
        existingWriters.add(writerToSave);
        writeWritersInternal(existingWriters);
        return writerToSave;
    }

    @Override
    public Writer update(Writer writerToUpdate) {
        List<Writer> existingWriters = getWritersInternal();
        existingWriters.forEach(existingWriter -> {
            if (existingWriter.getId().equals(writerToUpdate.getId())) {
                existingWriter.setName(writerToUpdate.getName());
                existingWriter.setLastName(writerToUpdate.getLastName());
            }
        });

        writeWritersInternal(existingWriters);
        return writerToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> existingWriters = getWritersInternal();
        existingWriters.removeIf(p -> p.getId().equals(id));
        writeWritersInternal(existingWriters);
    }
}
