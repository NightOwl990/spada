package com.lgkk.spada.model;

import lombok.Data;

@Data
public class TrackingUser {
    private String userId;
    private long userTime;
    private String userScreen;
    private String userType;

    public TrackingUser(String userId, long userTime, String userScreen, String userType) {
        this.userId = userId;
        this.userTime = userTime;
        this.userScreen = userScreen;
        this.userType = userType;
    }
}
