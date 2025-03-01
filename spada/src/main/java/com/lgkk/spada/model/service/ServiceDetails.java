package com.lgkk.spada.model.service;

import com.lgkk.spada.model.shop.Category;
import com.lgkk.spada.model.shop.Shop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ServiceDetails {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("score")
    @Expose
    private Float score;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("bookNumber")
    @Expose
    private Integer bookNumber;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("discountType")
    @Expose
    private String discountType;

    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("isBooked")
    @Expose
    private boolean isBooked;
    @SerializedName("shop")
    @Expose
    private List<Shop> shop = null;

    public ServiceDetails(String id) {
        this.id = id;
    }

}
