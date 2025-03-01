package com.lgkk.spada.model.service;

import com.lgkk.spada.model.shop.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Serv {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("bookNumber")
    @Expose
    private Integer bookNumber;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
}
