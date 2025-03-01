package com.lgkk.spada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang Nam on 05/05/2017.
 */

public class Message {
    @SerializedName("Message")
    @Expose
    private String Message;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Message(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
