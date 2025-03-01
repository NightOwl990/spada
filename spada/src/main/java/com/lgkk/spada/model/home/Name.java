package com.lgkk.spada.model.home;

/**
 * Created by hoang on 07/09/2017.
 */

public class Name {
    String name;
    String url;
    String image;

    public Name(String name, String url, String image) {
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public Name() {
    }

    public Name(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
