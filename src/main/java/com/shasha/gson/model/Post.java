package com.shasha.gson.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


    public class Post {
        private Integer id;
        private String content;
        private String created;
        private String updated = "";
        private List<Label> labels;
        PostStatus status;
        private static Calendar c = Calendar.getInstance();

        public Post(Integer id, String content) {
this.id = id;
            this.content = content;
            this.created = String.format("%tY.%tm.%td", c, c, c);
            this.status = PostStatus.ACTIVE;
            this.labels = labels;
        }

        public String getCreated() {
            return created;
        }

        public List<Label> getLabels() {
            return labels;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setContent(String content) {
            this.content = content;
            this.updated = String.format("%tY.%tm.%td", c, c, c);
        }

        public String getContent() {
            return content;
        }

        public void setLabels(List<Label> labels) {
            this.labels = labels;
        }
    }


    enum PostStatus {
        ACTIVE,
        UNDER_REVIEW,
        DELETED
    }

