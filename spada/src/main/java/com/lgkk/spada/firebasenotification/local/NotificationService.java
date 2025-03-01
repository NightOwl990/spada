package com.lgkk.spada.firebasenotification.local;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.widget.RemoteViews;

import com.lgkk.spada.R;
import com.lgkk.spada.api.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by hoang on 28/08/2017.
 */

public class NotificationService extends Service {
    private final String LOG_TAG = "NotificationService";
    boolean checkFirst = false;
    String name;
    String artist;
    String image;
    boolean isPause;
    Notification status;

    public static Bitmap getDefaultAlbumArt(Context context) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_action_pause, options);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        name = intent.getStringExtra("name");
        artist = intent.getStringExtra("artist");
        image = intent.getStringExtra("image");
        isPause = intent.getBooleanExtra("isPause", false);


        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            showNotification();
            if (checkFirst) {
                Intent broadcastIntent = new Intent();
//                broadcastIntent.setAction(ListMusicActivity.mBroadcastStringAction);
                broadcastIntent.putExtra("Data", "start");
                sendBroadcast(broadcastIntent);
            }
            checkFirst = true;
        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            Intent broadcastIntent = new Intent();
//            broadcastIntent.setAction(ListMusicActivity.mBroadcastStringAction);
            broadcastIntent.putExtra("Data", "previous");
            sendBroadcast(broadcastIntent);
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            Intent broadcastIntent = new Intent();
//            broadcastIntent.setAction(ListMusicActivity.mBroadcastStringAction);
            broadcastIntent.putExtra("Data", "play");
            sendBroadcast(broadcastIntent);
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {

            Intent broadcastIntent = new Intent();
//            broadcastIntent.setAction(ListMusicActivity.mBroadcastStringAction);
            broadcastIntent.putExtra("Data", "next");
            sendBroadcast(broadcastIntent);

        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            checkFirst = false;

            Intent broadcastIntent = new Intent();
//            broadcastIntent.setAction(ListMusicActivity.mBroadcastStringAction);
            broadcastIntent.putExtra("Data", "stop");
            sendBroadcast(broadcastIntent);

            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification() {
// Using RemoteViews to bind custom layouts into NotificationBean

        RemoteViews views = new RemoteViews(getPackageName(),
                R.layout.status_bar_mp3);
        RemoteViews bigViews = new RemoteViews(getPackageName(),
                R.layout.status_bar_expanded_mp3);

// showing default album image
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);


//        Intent notificationIntent = new Intent(this, ListMusicActivity.class);
//        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);

        Intent previousIntent = new Intent(this, NotificationService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, NotificationService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, NotificationService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(this, NotificationService.class);
        closeIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);

        views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);

        views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);

        views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);

        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

        if (!isPause) {
            views.setImageViewResource(R.id.status_bar_play,
                    R.drawable.ic_action_pause);
            bigViews.setImageViewResource(R.id.status_bar_play,
                    R.drawable.ic_action_pause);
        } else {
            views.setImageViewResource(R.id.status_bar_play,
                    R.drawable.ic_action_play_arrow);
            bigViews.setImageViewResource(R.id.status_bar_play,
                    R.drawable.ic_action_play_arrow);
        }

        views.setTextViewText(R.id.status_bar_track_name, name);
        bigViews.setTextViewText(R.id.status_bar_track_name, name);

        views.setTextViewText(R.id.status_bar_artist_name, artist);
        bigViews.setTextViewText(R.id.status_bar_artist_name, artist);

//        bigViews.setTextViewText(R.id.status_bar_album_name, "Album NameWebView");

        status = new Notification.Builder(this).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.mipmap.ic_launcher;

        Picasso.get()
                .load(Constants.BASE_API_URL + image)
                .into(bigViews, R.id.status_bar_album_art, 101, status);

        Picasso.get()
                .load(Constants.BASE_API_URL + image)
                .into(views, R.id.status_bar_album_art, 101, status);

//        status.contentIntent = pendingIntent;
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }
}
