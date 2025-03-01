package com.lgkk.spada.model._getdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMainObject {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private DataInside data;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataInside getData() {
        return data;
    }

    public void setData(DataInside data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
