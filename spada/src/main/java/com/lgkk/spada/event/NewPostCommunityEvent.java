package com.lgkk.spada.event;

import com.lgkk.spada.model.AddPostResult;

public class NewPostCommunityEvent {
    public AddPostResult addPostResult;
    public int type;

    public NewPostCommunityEvent(AddPostResult addPostResult) {
        this.addPostResult = addPostResult;
    }
}
