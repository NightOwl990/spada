package com.lgkk.spada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang Nam on 17/04/2017.
 */

public class Booking {
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Ordinal_number")
    @Expose
    private Integer ordinalNumber;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

}