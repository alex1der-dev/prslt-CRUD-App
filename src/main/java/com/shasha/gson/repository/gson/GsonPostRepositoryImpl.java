package com.shasha.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shasha.gson.model.Label;
import com.shasha.gson.model.Post;
import com.shasha.gson.repository.LabelRepository;
import com.shasha.gson.repository.PostRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GsonPostRepositoryImpl implements PostRepository {

    private static final String POSTS_FILE_PATH = "src/main/resources/posts.json";
    private static final Gson GSON = new Gson();
    private static final GsonLabelRepositoryImpl LABEL_REPOSITORY = new GsonLabelRepositoryImpl();

//    Gson gson = new Gson();
//
//    Department department = gson.fromJson(departmentJson, Department.class);

    private List<Post> getPostsInternal() {
        try {
            String json = Files.readString(Paths.get(POSTS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<Post>>() {
            }.getType();

            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }


    private void writePostsInternal(List<Post> posts) {
        String json = GSON.toJson(posts);
        try {
            Files.writeString(Paths.get(POSTS_FILE_PATH), json);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private Integer generateId(List<Post> posts) {
        Post maxIdPost = posts.stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return Objects.nonNull(maxIdPost) ? maxIdPost.getId() + 1 : 1;
    }

    @Override
    public Post getById(Integer id) {
        return getPostsInternal().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Post> getAll() {
        try {
            String json = Files.readString(Paths.get(POSTS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<Post>>() {
            }.getType();

            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Post save(Post postToSave) {
        List<Post> existingPosts = getPostsInternal();
        //List<Label> existingLabels  = LABEL_REPOSITORY.getAll();
        postToSave.setId(generateId(existingPosts));
        postToSave.setLabels(pickUpPostLabels());
        existingPosts.add(postToSave);
        writePostsInternal(existingPosts);
        return postToSave;

//        List<Label> existingLabels = getLabelsInternal();
//        labelToSave.setId(generateId(existingLabels));
//        existingLabels.add(labelToSave);
//        writeLabelsInternal(existingLabels);
//        return labelToSave;
    }

    @Override
    public Post update(Post postToUpdate) {
        List<Post> existingPosts = getPostsInternal();
        existingPosts.forEach(existingPost -> {
            if (existingPost.getId().equals(postToUpdate.getId())) {
                existingPost.setContent(postToUpdate.getContent());
            }
        });

        writePostsInternal(existingPosts);
        return postToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> existingPosts = getPostsInternal();
        existingPosts.removeIf(p -> p.getId().equals(id));
        writePostsInternal(existingPosts);
    }

    public List<Label> pickUpPostLabels() {
        Scanner scanner = new Scanner(System.in);
        List<Label> inputLabels = new ArrayList<>();
        List<Label> existingLabels = LABEL_REPOSITORY.getAll();
        System.out.println("Enter post's labels: ");
        String inputLabelName = scanner.nextLine();


        while (!inputLabelName.equals("")) {
            for (Label l : existingLabels) {
                if (inputLabelName.equals(l.getName())) {

                    inputLabels.add(l);
                    break;

                }
                if (!inputLabelName.equals(l.getName()) && existingLabels.indexOf(l) == existingLabels.size() - 1) {
                    Label labelToAddToPost = LABEL_REPOSITORY.save(new Label(null, inputLabelName));
                    existingLabels.add(labelToAddToPost);

                }
            }
            inputLabelName = scanner.nextLine();

        }
        return inputLabels;
    }
}
