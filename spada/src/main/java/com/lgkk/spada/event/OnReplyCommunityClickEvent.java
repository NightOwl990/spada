package com.lgkk.spada.event;

import com.lgkk.spada.model.community.post.postdetails.ReplyDetails;

public class OnReplyCommunityClickEvent {
    public String type;
    public int commentPosition;
    public int replyPositon;
    public ReplyDetails replyDetails;

    public OnReplyCommunityClickEvent(String type, int commentPosition, int replyPositon, ReplyDetails replyDetails) {
        this.type = type;
        this.commentPosition = commentPosition;
        this.replyPositon = replyPositon;
        this.replyDetails = replyDetails;
    }
}
