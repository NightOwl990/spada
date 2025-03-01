package com.lgkk.spada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang Nam on 22/03/2017.
 */

public class Error {
    @SerializedName("ErrorMessage")
    @Expose
    private String ErrorMessage;

    public String getError() {
        return ErrorMessage;
    }

    public void setError(String error) {
        this.ErrorMessage = error;
    }
}
