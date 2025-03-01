package com.lgkk.base_project.utils;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import java.io.IOException;

public class VideoUtils {

    public static long videoDuration(Context context, Uri uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//use one of overloaded setDataSource() functions to set your data source
        retriever.setDataSource(context, uri);
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInMillisec = Long.parseLong(time);

        try {
            retriever.release();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return timeInMillisec;
    }
}
