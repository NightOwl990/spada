package com.lgkk.spada.screen.home.widget;

import lombok.Data;

@Data
public class BrowseEntity {
    public String img;
    public String username;

    public BrowseEntity(String img, String username) {
        this.img = img;
        this.username = username;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
