package com.lgkk.spada.model._getdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataInside {
    @SerializedName("categories")
    @Expose
    private List<CategoriesData> categories = null;

    public List<CategoriesData> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesData> categories) {
        this.categories = categories;
    }
}
