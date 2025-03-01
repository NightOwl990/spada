package com.lgkk.spada.model.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class RatingScoreDetail implements Serializable {
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("length")
    @Expose
    private Integer length;

}
