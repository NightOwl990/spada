package com.lgkk.spada.model.community.post;

import com.lgkk.spada.model.User;

import java.util.List;

public class PostServiceMostView extends PostService {


    public PostServiceMostView(String id, String title, String content, List<String> images, long viewNumber, User user) {
        super(id, title, content, images, viewNumber, user);
    }
}
