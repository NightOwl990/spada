package com.lgkk.base_project.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Base64;

import com.lgkk.base_project.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;
import java.util.Set;

public class SharedPreferencesUtil {

    public static final String RIZE_SHARE_PRE = "BIBO_SHARE_PRE";
    public static final String PASSWORD = "PASSWORD";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String CURRENT_AVATAR = "avatar";
    private static final String USERNAME = "USERNAME";
    private static final String PLAY_POSITION = "play_position";
    private static final String PLAY_MODE = "play_mode";
    private static final String SPLASH_URL = "splash_url";
    private static final String NIGHT_MODE = "night_mode";

    private static SharedPreferencesUtil prefsUtil;
    public Context context;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    private SharedPreferencesUtil() {
    }

    public int getPlayPosition() {
        return getInt(PLAY_POSITION, 0);
    }

    public void savePlayPosition(int position) {
        putInt(PLAY_POSITION, position);
    }

    public int getPlayMode() {
        return getInt(PLAY_MODE, 0);
    }

    public void savePlayMode(int mode) {
        putInt(PLAY_MODE, mode);
    }

    public String getSplashUrl() {
        return getString(SPLASH_URL, "");
    }

    public void saveSplashUrl(String url) {
        putString(SPLASH_URL, url);
    }

    public boolean enableMobileNetworkPlay() {
        return getBoolean(context.getString(R.string.setting_key_mobile_network_play), false);
    }

    public void saveMobileNetworkPlay(boolean enable) {
        putBoolean(context.getString(R.string.setting_key_mobile_network_play), enable);
    }

    public boolean enableMobileNetworkDownload() {
        return getBoolean(context.getString(R.string.setting_key_mobile_network_download), false);
    }

    public boolean isNightMode() {
        return getBoolean(NIGHT_MODE, false);
    }

    public void saveNightMode(boolean on) {
        putBoolean(NIGHT_MODE, on);
    }

    public String getFilterSize() {
        return getString(context.getString(R.string.setting_key_filter_size), "0");
    }

    public void saveFilterSize(String value) {
        putString(context.getString(R.string.setting_key_filter_size), value);
    }

    public String getFilterTime() {
        return getString(context.getString(R.string.setting_key_filter_time), "0");
    }

    public void saveFilterTime(String value) {
        putString(context.getString(R.string.setting_key_filter_time), value);
    }


    public synchronized static SharedPreferencesUtil getInstance() {
        return prefsUtil;
    }

    @SuppressLint("CommitPrefEdits")
    public static void init(Context context, String prefsname, int mode) {
        prefsUtil = new SharedPreferencesUtil();
        prefsUtil.context = context;
        prefsUtil.prefs = prefsUtil.context.getSharedPreferences(prefsname, mode);
        prefsUtil.editor = prefsUtil.prefs.edit();
    }


    public static void savePreferences(Context activity, String key, String value) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferencesUtil.RIZE_SHARE_PRE, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveBoolPreferences(Context activity, String key, boolean value) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferencesUtil.RIZE_SHARE_PRE, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean readBoolPreferences(Context activity, String key, boolean defaultValue) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferencesUtil.RIZE_SHARE_PRE, Context.MODE_APPEND);
        return sp.getBoolean(key, defaultValue);
    }

    public static String readPreferences(Context activity, String key, String defaultValue) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferencesUtil.RIZE_SHARE_PRE, Context.MODE_APPEND);
        return sp.getString(key, defaultValue);
    }

    public static void removePreference(Context activity, String string) {
        SharedPreferences sp = activity.getSharedPreferences(string, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(string);
        editor.commit();
    }

    public static void saveUsernamePassword(Context context, String userName, String password) {
        SharedPreferences sp = context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USERNAME, userName);
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public static String getUsername(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE).getString(USERNAME, null);
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE).getString(ACCESS_TOKEN, null);
    }

    public static String getPassword(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE).getString(PASSWORD, null);
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.prefs.getBoolean(key, defaultVal);
    }

    public boolean getBoolean(String key) {
        return this.prefs.getBoolean(key, false);
    }

    public String getString(String key, String defaultVal) {
        return this.prefs.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.prefs.getString(key, null);
    }

    public int getInt(String key, int defaultVal) {
        return this.prefs.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }

    public float getFloat(String key, float defaultVal) {
        return this.prefs.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.prefs.getFloat(key, 0f);
    }

    public long getLong(String key, long defaultVal) {
        return this.prefs.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.prefs.getLong(key, 0l);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultVal) {
        return this.prefs.getStringSet(key, defaultVal);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key) {
        return this.prefs.getStringSet(key, null);
    }

    public Map<String, ?> getAll() {
        return this.prefs.getAll();
    }

    public boolean exists(String key) {
        return prefs.contains(key);
    }

    public SharedPreferencesUtil putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
        return this;
    }

    public void commit() {
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public SharedPreferencesUtil putStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value);
        editor.commit();
        return this;
    }

    public void putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (prefs.contains(key)) {
            String objectVal = prefs.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public SharedPreferencesUtil remove(String key) {
        editor.remove(key);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil removeAll() {
        editor.clear();
        editor.commit();
        return this;
    }
}
