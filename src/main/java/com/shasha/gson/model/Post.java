package com.shasha.gson.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


    public class Post {
        private Integer id;
        private String content;
        private Long created;
        private Long updated;
        private List<Label> labels;
        private PostStatus status;

public Post(Integer id) {
    this.id = id;
}
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getCreated() {
            return created;
        }

        public void setCreated(Long created) {
            this.created = created;
        }

        public Long getUpdated() {
            return updated;
        }

        public void setUpdated(Long updated) {
            this.updated = updated;
        }

        public List<Label> getLabels() {
            return labels;
        }

        public void setLabels(List<Label> labels) {
            this.labels = labels;
        }

        public PostStatus getStatus() {
            return status;
        }

        public void setStatus(PostStatus status) {
            this.status = status;
        }
    }


