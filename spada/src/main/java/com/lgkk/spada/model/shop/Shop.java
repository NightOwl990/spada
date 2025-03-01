package com.lgkk.spada.model.shop;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class Shop implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("shipper")
    @Expose
    private String shipper;
    @SerializedName("isCheckedShop")
    @Expose
    private boolean isCheckedShop = true;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("score")
    @Expose
    private Double score;
}
