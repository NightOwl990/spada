package com.lgkk.spada.model.service;

import java.io.Serializable;

import lombok.Data;

@Data
public class ServiceTagComment implements Serializable {

    private String id;
    private String tagName;
    private int commentCount;
    private boolean isSelected = false;

    public ServiceTagComment() {
    }

    public ServiceTagComment(String tagName, int commentCount) {
        this.tagName = tagName;
        this.commentCount = commentCount;
    }

    public ServiceTagComment(String tagName) {
        this.tagName = tagName;
        this.commentCount = 0;
    }

    public ServiceTagComment(String id, String tagName) {
        this.id = id;
        this.tagName = tagName;
        this.commentCount = 0;
    }
}
