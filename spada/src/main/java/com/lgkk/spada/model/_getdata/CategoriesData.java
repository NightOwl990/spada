package com.lgkk.spada.model._getdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesData {
    @SerializedName("category")
    @Expose
    private CategoryData category;
    @SerializedName("forums")
    @Expose
    private List<Forum> forums = null;

    public CategoryData getCategory() {
        return category;
    }

    public void setCategory(CategoryData category) {
        this.category = category;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }
}
