package com.shasha.gson.controller;

import com.shasha.gson.model.Label;
import com.shasha.gson.repository.LabelRepository;
import com.shasha.gson.repository.gson.GsonLabelRepositoryImpl;

import java.util.List;

public class LabelController {

    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();

    public List<Label> getAllLabels() {
        return labelRepository.getAll();
    }

    public Label updateLabel(Integer id, String name) {
        Label labelToUpdate = new Label(id, name);
        labelToUpdate.setId(id);
        labelToUpdate.setName(name);

        return labelRepository.update(labelToUpdate);
    }

    public Label saveLabel(String name) {
        Label label = new Label(null, name);

        return labelRepository.save(label);
    }

    public void removeLabel(Integer id) {
        labelRepository.deleteById(id);
    }
}
