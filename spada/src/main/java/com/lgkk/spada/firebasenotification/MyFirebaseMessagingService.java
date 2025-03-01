package com.lgkk.spada.firebasenotification;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lgkk.base_project.utils.RxBus;
import com.lgkk.base_project.utils.SharedPreferencesUtil;
import com.lgkk.spada.R;
import com.lgkk.spada.event.UpdateNotificationEvent;
import com.lgkk.spada.model.home.NotificationData;

import com.lgkk.spada.screen._main.MainActivity;


import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsg";
    boolean isNotification;
    boolean isNotificationSound;
    private String typeNotification = "";
    private String title = "";
    private String content = "";
    private String questionId = "";
    private String commentId = "";
    private String replyId = "";
    private String action = "";
    private String name = "";
    private String groupName = "";
    private String notificationId = "";
    private String image = "";
    private String targetId = "";
    private String url = "";
    private String subCategoryId = "";
    private String status = "";
    private Intent intent;
    private String route = "";
    private String type = "";
    private NotificationUtils notificationUtils;
    private NotificationData notificationData;


    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

        SharedPreferencesUtil.getInstance().putString("regId", token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        isNotification = SharedPreferencesUtil.readBoolPreferences(getApplicationContext(), "isNotification", true);
        isNotificationSound = SharedPreferencesUtil.readBoolPreferences(getApplicationContext(), "isNotificationSound", true);
        RxBus.getInstance().post(new UpdateNotificationEvent());

        createNotificationChannel();
        if (isNotification) {
            if (remoteMessage.getData() != null) {
                typeNotification = remoteMessage.getData().get("typeNotification");
                route = remoteMessage.getData().get("route");
                title = remoteMessage.getData().get("title");
                content = remoteMessage.getData().get("content");
                name = remoteMessage.getData().get("userName");
                url = remoteMessage.getData().get("url");
                type = remoteMessage.getData().get("type");
                if (name == null) name = "";
                if (url == null) url = "";
                groupName = remoteMessage.getData().get("groupName");
                if (groupName == null) groupName = "";
                action = remoteMessage.getData().get("action");
                questionId = remoteMessage.getData().get("questionId");
                commentId = remoteMessage.getData().get("commentId");
                replyId = remoteMessage.getData().get("replyId");
                targetId = remoteMessage.getData().get("targetId");
                notificationId = remoteMessage.getData().get("notificationId");
                subCategoryId = remoteMessage.getData().get("subCategoryId");
                if (subCategoryId == null) subCategoryId = "";
                status = remoteMessage.getData().get("status");
                if (status == null) status = "";
                image = remoteMessage.getData().get("image");
                if (image == null) image = "";

                Map<String, String> params = remoteMessage.getData();
                JSONObject object = new JSONObject(params);
                Log.e("JSON_OBJECT", object.toString());

                sendNotification();
            }
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.default_notification_channel_id), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void sendNotification() {
        Log.d(TAG, route + " - " + targetId+ " - "+ type);

        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", "sss");
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        } else {
            // app is in background, show the notification in notification tray
            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            resultIntent.putExtra("targetId", questionId);
        }

        boolean isNotificationSound;
        isNotificationSound = SharedPreferencesUtil.readBoolPreferences(getApplicationContext(), "isNotificationSound", true);
        Handler h = new Handler(Looper.getMainLooper());

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        intent = new Intent(this, MainActivity.class);
        intent.putExtra("route", route);
        intent.putExtra("targetId", targetId);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("image", image);
        intent.putExtra("status", status);
        intent.putExtra("url", url);
        intent.putExtra("type", type);
        intent.putExtra("notificationId", notificationId);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        if (isNotificationSound) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                    .setSmallIcon(getNotificationIcon())
                    .setContentTitle(title)
                    .setContentText(name + content + groupName)
                    .setSound(alarmSound)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationmanager.notify(0, builder.build());
        } else {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                    .setSmallIcon(getNotificationIcon())
                    .setContentTitle(title)
                    .setContentText(name + content + groupName)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Build NotificationBean with NotificationBean Manager
            notificationmanager.notify(0, builder.build());
        }
    }

    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.homecandy_logo_v3 : R.mipmap.homecandy_logo_v3;
    }

    private class sendNotificationImage extends AsyncTask<String, Void, Bitmap> {

        Context ctx;
        String title;
        String content;

        public sendNotificationImage(Context context) {
            super();
            this.ctx = context;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream in;
            title = params[0];
            content = params[1];
            try {
                URL url = new URL(params[2]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            super.onPostExecute(result);
            try {
                final RemoteViews remoteViews = new RemoteViews(getPackageName(),
                        R.layout.customnotification);
                String strtitle = title;
                String strtext = content;
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.putExtra("title", strtitle);
                intent.putExtra("text", strtext);

                PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                if (isNotificationSound) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, getString(R.string.default_notification_channel_id))
                            .setSmallIcon(getNotificationIcon())
                            .setTicker("sssssssss")
                            .setAutoCancel(true)
                            .setContentIntent(pIntent)
                            .setSound(alarmSound)
                            .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                            .setContentTitle(title)
                            .setContentText(name + content + groupName)
                            .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(result));
                    remoteViews.setImageViewResource(R.id.imagenotileft, R.mipmap.ic_launcher);
                    remoteViews.setImageViewBitmap(R.id.imagenotiright, result);
                    // Locate and set the Image into customnotificationtext.xml ImageViews
                    // remoteViews.setImageViewResource(R.targetId.imagenotiright,R.mipmap.a1);

                    // Locate and set the Text into customnotificationtext.xml TextViews
                    remoteViews.setTextViewText(R.id.txtTitle, title);
                    remoteViews.setTextViewText(R.id.text, name + content + groupName);

                    // Create NotificationBean Manager
                    NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    // Build NotificationBean with NotificationBean Manager
                    notificationmanager.notify(0, builder.build());
                } else {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, getString(R.string.default_notification_channel_id))
                            .setSmallIcon(getNotificationIcon())
                            .setTicker("sssssssss")
                            .setAutoCancel(true)
                            .setContentIntent(pIntent)
                            .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
//                        .setContent(remoteViews)
                            .setContentTitle(title)
                            .setContentText(name + content + groupName)
                            .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(result));
                    remoteViews.setImageViewResource(R.id.imagenotileft, R.mipmap.ic_launcher);
                    remoteViews.setImageViewBitmap(R.id.imagenotiright, result);
                    // Locate and set the Image into customnotificationtext.xml ImageViews
                    // remoteViews.setImageViewResource(R.targetId.imagenotiright,R.mipmap.a1);

                    // Locate and set the Text into customnotificationtext.xml TextViews
                    remoteViews.setTextViewText(R.id.txtTitle, title);
                    remoteViews.setTextViewText(R.id.text, name + content + groupName);

                    // Create NotificationBean Manager
                    NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    // Build NotificationBean with NotificationBean Manager
                    notificationmanager.notify(0, builder.build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
