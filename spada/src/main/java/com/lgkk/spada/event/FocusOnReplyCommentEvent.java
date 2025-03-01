package com.lgkk.spada.event;

public class FocusOnReplyCommentEvent {
    public int adapterPosition;
    public String usernameReply;
    public int type;

    public FocusOnReplyCommentEvent(int type, String usernameReply, int adapterPosition) {
        this.type = type;
        this.usernameReply = usernameReply;
        this.adapterPosition = adapterPosition;
    }
}
