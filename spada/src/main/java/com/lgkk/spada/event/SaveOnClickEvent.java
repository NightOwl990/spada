package com.lgkk.spada.event;

import com.lgkk.spada.model.community.post.PostDetails;

public class SaveOnClickEvent {
    public PostDetails item;
    public int position;

    public SaveOnClickEvent(PostDetails item, int position) {
        this.item = item;
        this.position = position;
    }
}
