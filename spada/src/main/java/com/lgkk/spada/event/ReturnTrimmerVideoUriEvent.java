package com.lgkk.spada.event;

import android.net.Uri;

public class ReturnTrimmerVideoUriEvent {

    public Uri trimmerUri;


    public ReturnTrimmerVideoUriEvent(Uri trimmerUri) {
        this.trimmerUri = trimmerUri;
    }
}
