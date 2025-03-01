package com.lgkk.module_home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Choice {
    @SerializedName("choice")
    @Expose
    private String choice;
    @SerializedName("votes")
    @Expose
    private Integer votes;
    @SerializedName("url")
    @Expose
    private String url;
}
