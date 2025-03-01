package com.lgkk.spada.model.community.post;

/**
 * Created by Hoang Nam on 29/06/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MyPost implements Serializable {
    @SerializedName("data")
    @Expose
    private List<PostDetails> data;
    @SerializedName("next")
    @Expose
    private String next;

    public List<PostDetails> getData() {
        return data;
    }

    public void setData(List<PostDetails> data) {
        this.data = data;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
