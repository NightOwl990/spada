package com.lgkk.spada.model.home.nutrition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Nutrition {
    @SerializedName("restriction")
    @Expose
    private List<String> restriction = null;
    @SerializedName("cookBook")
    @Expose
    private List<String> cookBook = null;
    @SerializedName("isActive")
    @Expose
    private boolean isActive;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("pregnant_comment")
    @Expose
    private String pregnantComment;
    @SerializedName("puerpera_comment")
    @Expose
    private String puerperaComment;
    @SerializedName("baby_comment")
    @Expose
    private String babyComment;
    @SerializedName("lactation_comment")
    @Expose
    private String lactationComment;
    @SerializedName("nutrition")
    @Expose
    private String nutrition;
    @SerializedName("friendly_tips")
    @Expose
    private String friendlyTips;
    @SerializedName("taboo_ingredient")
    @Expose
    private String tabooIngredient;
    @SerializedName("strategy")
    @Expose
    private String strategy;
    @SerializedName("title_alias")
    @Expose
    private String titleAlias;
    @SerializedName("copywriting")
    @Expose
    private String copywriting;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("baby_notice")
    @Expose
    private Integer babyNotice;
    @SerializedName("baby_notice_month")
    @Expose
    private Integer babyNoticeMonth;
    @SerializedName("category")
    @Expose
    private NutritionCategory category;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("lactation_notice")
    @Expose
    private Integer lactationNotice;
    @SerializedName("pregnant_notice")
    @Expose
    private Integer pregnantNotice;
    @SerializedName("puerpera_notice")
    @Expose
    private Integer puerperaNotice;
    @SerializedName("sort")
    @Expose
    private Integer sort;
    @SerializedName("img")
    @Expose
    private String img;
}
