package com.lgkk.spada.event;

import com.lgkk.spada.model.community.post.PostDetails;

public class ReportOnClickEvent {
    public PostDetails item;
    public int position;

    public ReportOnClickEvent(PostDetails item, int position) {
        this.item = item;
        this.position = position;
    }
}
