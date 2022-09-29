package com.shasha.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shasha.gson.model.Label;
import com.shasha.gson.repository.LabelRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private static final String LABELS_FILE_PATH = "src/main/resources/labels.json";
    private static final Gson GSON = new Gson();

    private List<Label> getLabelsInternal() {
        try {
            String json = Files.readString(Paths.get(LABELS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<Label>>() { }.getType();

            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }


    private void writeLabelsInternal(List<Label> labels) {
        String json = GSON.toJson(labels);
        try {
            Files.writeString(Paths.get(LABELS_FILE_PATH), json);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private Integer generateId(List<Label> labels) {
        Label maxIdLabel = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.nonNull(maxIdLabel) ? maxIdLabel.getId() + 1 : 1;
    }

    @Override
    public Label getById(Integer id) {
        return getLabelsInternal().stream().filter(l-> l.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Label> getAll() {
        try {
            String json = Files.readString(Paths.get(LABELS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<Label>>() { }.getType();

            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Label save(Label labelToSave) {
        List<Label> existingLabels = getLabelsInternal();
        labelToSave.setId(generateId(existingLabels));
        existingLabels.add(labelToSave);
        writeLabelsInternal(existingLabels);
        return labelToSave;
    }

    @Override
    public Label update(Label labelToUpdate) {
        List<Label> existingLabels = getLabelsInternal();
        existingLabels.forEach(existingLabel -> {
            if(existingLabel.getId().equals(labelToUpdate.getId())) {
                existingLabel.setName(labelToUpdate.getName());
            }
        });

        writeLabelsInternal(existingLabels);
        return labelToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> existingLabels = getLabelsInternal();
        existingLabels.removeIf(l -> l.getId().equals(id));
        writeLabelsInternal(existingLabels);
    }
}
