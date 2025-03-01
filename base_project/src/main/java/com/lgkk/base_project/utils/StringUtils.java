package com.lgkk.base_project.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lgkk.base_project.R;
import com.lgkk.base_project.widget.VerticalImageSpan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thanhle on 11/6/15.
 */
public class StringUtils {

    public static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date stringToDateNotT(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToString(Date date, String fomat) {
        SimpleDateFormat sdf = new SimpleDateFormat(fomat, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateString = null;
        try {
            dateString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateString = null;
        try {
            dateString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static String parseDateToString(Date date, String pattern) {
        return date != null && pattern != null ? new SimpleDateFormat(pattern,
                Locale.getDefault()).format(date) : null;
    }

    public static Date parseStringToDate(String data, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * Check string 's empty or no
     *
     * @param string
     * @return true if string's null or length = 0, false otherwise
     */
    public static boolean isEmptyString(String string) {
        return string == null || string.trim().equals("") || string.trim().length() <= 0;
    }

    public static boolean validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(password);
        return !matcher.find() && password.length() > 5;
    }

    public static boolean validateBirthday(String strDate, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 15);
        try {
            Date date = dateFormat.parse(strDate);
            return (date.before(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<String> getColorIdList(String str) {
        List<String> list = new ArrayList<>();
        if (str != null && !str.equals("")) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ',') {
                    list.add(str.substring(0, i));
                    str = str.substring(i + 1);
                    i = 0;
                }
                if (i == str.length() - 1) {
                    list.add(str);
                }
            }
        }
        return list;
    }

    public static String formatPrice(String price) {
        NumberFormat numberFormat = DecimalFormat.getNumberInstance(Locale.GERMAN);
        return numberFormat.format(Double.valueOf(price));

    }

    public static Map<String, Long> getTimeBetweenDateWithCurrent(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        //TimeZone.getTimeZone()
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date currentDate = calendar.getTime();
//        Date currentDate = new Date();
        Date d2 = null;

        Map<String, Long> result = new HashMap<>();

        try {
            d2 = format.parse(strDate);

            //in milliseconds
            long diff = d2.getTime() - currentDate.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            result.put("days", diffDays);
            result.put("hours", diffHours);
            result.put("minutes", diffMinutes);
            result.put("seconds", diffSeconds);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public synchronized static Map<String, Long> getTimeLeftOfClaimed(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        //TimeZone.getTimeZone()
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date currentDate = calendar.getTime();
//        Date currentDate = new Date();
        Date d2 = null;

        Map<String, Long> result = new HashMap<>();

        try {
            d2 = format.parse(strDate);

            //in milliseconds
            long diff = d2.getTime() - currentDate.getTime();

            long leftTime = (24 * 60 * 60 * 1000) + diff;

            long diffSeconds = leftTime / 1000 % 60;
            long diffMinutes = leftTime / (60 * 1000) % 60;
            long diffHours = leftTime / (60 * 60 * 1000) % 24;
            long diffDays = leftTime / (24 * 60 * 60 * 1000);

            result.put("days", diffDays);
            result.put("hours", diffHours);
            result.put("minutes", diffMinutes);
            result.put("seconds", diffSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static Long getFormattedDate(Date created) {
        long time;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse(dateToString(created));
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Timestamp tm = Timestamp.valueOf(dateFormat.format(date));
            time = tm.getTime();
            return time;
        } catch (ParseException ignored) {
        }
        return null;
    }

    //hiển thị ngày tháng năm của gói dịch vụ
    public static String convertDate(String date) {
        String dateString = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date tempDate = simpleDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dateString = outputDateFormat.format(tempDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return dateString;
    }

    public static String convertDate2(String date) {
        String dateString = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            Date tempDate = simpleDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateString = outputDateFormat.format(tempDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return dateString;
    }

    public static String reConvertDate(String date) {
        String dateString = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date tempDate = simpleDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            dateString = outputDateFormat.format(tempDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return dateString;
    }

    public static File createCaptureFilename(Context context) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "capture_" + timeStamp + "_";
        File storageDir = new File(context.getExternalCacheDir() + "/pictures");
        if (!storageDir.exists()) storageDir.mkdirs();
        File captureFilename = null;
        try {
            captureFilename = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException ignored) {
        }
        return captureFilename;
    }

    public static String getPath2(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static Date getDateNew(String startDateString) {
        String[] dateTime = startDateString.split("T");
        String[] time = dateTime[1].split(":");
        String[] day = dateTime[0].split("-");

        int house = 0;
        if (Integer.valueOf(time[0]) > 17) {
            house = (Integer.valueOf(time[0]) - 24) + 7;
        } else {
            house = (Integer.valueOf(time[0]) + 7);
        }

        Date date = new Date();
        date.setDate(Integer.valueOf(day[2]));
        date.setMonth(Integer.valueOf(day[1]));
        date.setYear(Integer.valueOf(day[0]));
        date.setHours(house);
        date.setMinutes(Integer.valueOf(time[1]));

        return date;
    }

    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;    }


    public static String textAlreadyRemoveUselessSpace(String text) {
        String result = text.trim();
        result.replaceAll("\\s{2,}", " ");
        return result;
    }

    public static void setColorOfContentWithBackground(Context mContext, TextView txtView, int positionBackground) {
        if ((0 < positionBackground) && (positionBackground <= 3)) {
            txtView.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        if ((4 <= positionBackground) && (positionBackground <= 5)) {
            txtView.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        if ((6 <= positionBackground) && (positionBackground <= 9)) {
            txtView.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        if (positionBackground == 10) {
            txtView.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        if (positionBackground == 11) {
            txtView.setTextColor(mContext.getResources().getColor(R.color.black));
        }

    }

    public static void getHashKey(Activity context) {
        try {
            PackageInfo info = null;
            try {
                info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_SIGNATURES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NoSuchAlgorithmException e) {

        }
    }

    public static int getDrawableTypeNutrition(int type) {
        switch (type) {
            case 1:
                return R.drawable.ic_tool_candu;
            case 2:
                return R.drawable.ic_tool_forbit;
            case 3:
                return R.drawable.ic_tool_notice;
        }
        return R.drawable.ic_tool_candu;
    }

    public static String removeUselessSpace(String text) {
        return text.replaceAll("\\s{2,}", " ").trim();
    }


    public static boolean isMatchLink(String s) {
        String pattern = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{2}\\.([a-z]+)?$";
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }

        return count;
    }

    public static int countDot(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '.':
                    count++;
                    break;
            }

        }
        return count;
    }

    public static SpannableString wrapTextToDrawable(Activity activity, String text, int drawable) {
        SpannableString spanText = new SpannableString("   " + text);
        Drawable d = activity.getResources().getDrawable(drawable);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//        d.setBounds(0, 0, d.getIntrinsicWidth(),
//                d.getIntrinsicHeight());

// 替换0,1的字符
        VerticalImageSpan span = new VerticalImageSpan(d);
        spanText.setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanText;
    }

    public static String getJsonFromAssets(Context context, String fileName) {
        try {
            InputStream stream = context.getAssets().open(fileName);
            int size = stream.available();

            byte[] bytes = new byte[size];
            stream.read(bytes);
            stream.close();

            return new String(bytes);

        } catch (IOException e) {
            Log.i("GuiFormData", "IOException: " + e.getMessage());
        }
        return null;
    }
}


