package com.lgkk.spada.model;

import lombok.Data;

@Data
public class UserTracking {
    private String userId;
    private TrackingUser trackingUser;

    public UserTracking(String userId, TrackingUser trackingUser) {
        this.userId = userId;
        this.trackingUser = trackingUser;
    }
}
