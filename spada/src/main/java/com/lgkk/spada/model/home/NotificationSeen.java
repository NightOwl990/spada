package com.lgkk.spada.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationSeen {
    @SerializedName("isSeen")
    @Expose
    private boolean isSeen;

    public boolean getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }
}
