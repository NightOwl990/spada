package com.lgkk.spada.model.home.nutrition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class NutritionCatAndRecommend {

    @SerializedName("category_list")
    @Expose
    private List<NutritionCategory> categoryList = null;
    @SerializedName("recommend_list")
    @Expose
    private List<Nutrition> recommendList = null;
}
