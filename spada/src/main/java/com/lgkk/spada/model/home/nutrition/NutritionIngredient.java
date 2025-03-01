package com.lgkk.spada.model.home.nutrition;

import lombok.Data;

@Data
public class NutritionIngredient {
    private String ingredient;
    private String weight;

    public NutritionIngredient(String ingredient, String weight) {
        this.ingredient = ingredient;
        this.weight = weight;
    }
}
