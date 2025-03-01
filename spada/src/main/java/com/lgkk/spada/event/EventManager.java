package com.lgkk.spada.event;

import android.net.Uri;

import com.lgkk.spada.model.AddPostResult;
import com.lgkk.spada.model.community.post.PostDetails;


public class EventManager {

    public static class StepChooseEvent {
        public String type;

        public StepChooseEvent(String type) {
            this.type = type;
        }
    }

    public static class ChangeUserIdEvent {
    }
}
