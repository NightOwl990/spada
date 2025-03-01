package com.lgkk.spada.model.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RatingScore implements Serializable {
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("detail")
    @Expose
    private List<RatingScoreDetail> detail = null;

}
