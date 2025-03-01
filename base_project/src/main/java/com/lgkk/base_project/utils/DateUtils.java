package com.lgkk.base_project.utils;

import android.content.Context;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import android.text.format.DateFormat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by thanhle on 7/14/16.
 */
public class DateUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String TIME_FORMAT = "h:mm a";
    public static final String DATE_TIME_NO_SECONDS_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_DAY_MONTH = "MM-dd h:mm a";
    public static final int Second = 0;
    public static final int Minute = 1;
    public static final int Hour = 2;
    public static final int Day = 3;

    public static Date now() {
        SimpleDateFormat formatLocal = new SimpleDateFormat(DATE_TIME_FORMAT);
        formatLocal.setTimeZone(TimeZone.getTimeZone("UTC"));
        String gtmTime = nowString();
        Date date;
        try {
            date = formatLocal.parse(gtmTime);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    public static String nowString() {
        return getDateString(new Date());
    }

    public static Date getDate(String date) throws ParseException {
        if (StringUtils.isEmptyString(date)) return null;
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.parse(date);
    }

    public static Date getDateIso8601(String date) throws ParseException {
        if (StringUtils.isEmptyString(date)) return null;
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT_ISO_8601);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        return format.parse(date);
    }

    public static String getDateTimeString(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String gtmTime = format.format(date);
        return gtmTime;
    }

    public static String getDateTimeString2(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_NO_SECONDS_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String gtmTime = format.format(date);
        return gtmTime;
    }

    public static String getDateTimeString(Date date, String FORMAT) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(FORMAT);
        //format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String gtmTime = format.format(date);
        return gtmTime;
    }

    public static String getTimeString(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String gtmTime = format.format(date);
        return gtmTime;
    }

    public static String getDateString(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        String gtmTime = format.format(date);
        return gtmTime;
    }

    public static String getTripDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    public static Long getFormattedDate(Date date) {
        long time;
        try {
            Timestamp tm = Timestamp.valueOf(getDateTimeString(date));
            time = tm.getTime();
            return time;
        } catch (Exception ignored) {
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static String formatSameDayTime(final Context context, final long timestamp) {
        if (context == null) return null;
        if (android.text.format.DateUtils.isToday(timestamp))
            return android.text.format.DateUtils.formatDateTime(context, timestamp,
                    DateFormat.is24HourFormat(context) ? android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_24HOUR
                            : android.text.format.DateUtils.FORMAT_SHOW_TIME | android.text.format.DateUtils.FORMAT_12HOUR);
        return android.text.format.DateUtils.formatDateTime(context, timestamp, android.text.format.DateUtils.FORMAT_SHOW_DATE);
    }

    /**
     * @return
     */
    public static String convertLongTimeToDateTime(Long time) {
        return new SimpleDateFormat("HH:mm").format(new Date(time));
    }

    public static int durationBetweenDate(Date targetDate) {

        long nowTime = System.currentTimeMillis();

        long targetTime = targetDate.getTime();

        long duration = targetTime - nowTime;

        return (int) duration / 86400000;
    }

    public static String switchTypeFormat(Date date) {
        Date currentDate = new Date();
        if (currentDate.getYear() - date.getYear() > 0)
            return DATE_FORMAT;
        if (currentDate.getMonth() - date.getMonth() > 0)
            return DATE_FORMAT;
        if (currentDate.getDate() - date.getDate() > 0)
            return DATE_TIME_DAY_MONTH;
        return TIME_FORMAT;
    }

    public static String reformatDate(String myDate) throws ParseException {
        SimpleDateFormat inFormat = new SimpleDateFormat(DATE_TIME_FORMAT_ISO_8601);
        SimpleDateFormat outFormat = new SimpleDateFormat("M dd");

        Date parsedInDate = inFormat.parse(myDate);
        return outFormat.format(parsedInDate);
    }

    public static long calculateDifferentSecond(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, Second);
    }

    public static long calculateDifferentMinute(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, Minute);
    }

    public static long calculateDifferentHour(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, Hour);
    }

    public static long calculateDifferentDay(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, Day);
    }

    public static long calculateDifferentSecond(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, Second);
    }

    public static long calculateDifferentMinute(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, Minute);
    }

    public static long calculateDifferentHour(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, Hour);
    }

    public static long calculateDifferentDay(long startTimeMillis, long endTimeMillis) {
        return calculateDifference(startTimeMillis, endTimeMillis, Day);
    }

    /**
     * 计算两个时间戳之间相差的时间戳数
     */
    public static long calculateDifference(long startTimeMillis, long endTimeMillis, @DifferenceMode int mode) {
        return calculateDifference(new Date(startTimeMillis), new Date(endTimeMillis), mode);
    }

    /**
     * 计算两个日期之间相差的时间戳数
     */
    public static long calculateDifference(Date startDate, Date endDate, @DifferenceMode int mode) {
        long[] different = calculateDifference(startDate, endDate);
        if (mode == Minute) {
            return different[2];
        } else if (mode == Hour) {
            return different[1];
        } else if (mode == Day) {
            return different[0];
        } else {
            return different[3];
        }
    }

    private static long[] calculateDifference(Date startDate, Date endDate) {
        return calculateDifference(endDate.getTime() - startDate.getTime());
    }

    private static long[] calculateDifference(long differentMilliSeconds) {
        long secondsInMilli = 1000;//1s==1000ms
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = differentMilliSeconds / daysInMilli;
        differentMilliSeconds = differentMilliSeconds % daysInMilli;
        long elapsedHours = differentMilliSeconds / hoursInMilli;
        differentMilliSeconds = differentMilliSeconds % hoursInMilli;
        long elapsedMinutes = differentMilliSeconds / minutesInMilli;
        differentMilliSeconds = differentMilliSeconds % minutesInMilli;
        long elapsedSeconds = differentMilliSeconds / secondsInMilli;
        LogUtils.verbose(String.format(Locale.CHINA, "different: %d ms, %d days, %d hours, %d minutes, %d seconds",
                differentMilliSeconds, elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds));
        return new long[]{elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds};
    }

    /**
     * 计算每月的天数
     */
    public static int calculateDaysInMonth(int month) {
        return calculateDaysInMonth(0, month);
    }

    /**
     * 根据年份及月份计算每月的天数
     */
    public static int calculateDaysInMonth(int year, int month) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] bigMonths = {"1", "3", "5", "7", "8", "10", "12"};
        String[] littleMonths = {"4", "6", "9", "11"};
        List<String> bigList = Arrays.asList(bigMonths);
        List<String> littleList = Arrays.asList(littleMonths);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (bigList.contains(String.valueOf(month))) {
            return 31;
        } else if (littleList.contains(String.valueOf(month))) {
            return 30;
        } else {
            if (year <= 0) {
                return 29;
            }
            // 是否闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
    }

    /**
     * 月日时分秒，0-9前补0
     */
    @NonNull
    public static String fillZero(int number) {
        return number < 10 ? "0" + number : "" + number;
    }

    /**
     * 截取掉前缀0以便转换为整数
     *
     * @see #fillZero(int)
     */
    public static int trimZero(@NonNull String text) {
        try {
            if (text.startsWith("0")) {
                text = text.substring(1);
            }
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            LogUtils.warn(e);
            return 0;
        }
    }

    /**
     * 功能：判断日期是否和当前date对象在同一天。
     * 参见：http://www.cnblogs.com/myzhijie/p/3330970.html
     *
     * @param date 比较的日期
     * @return boolean 如果在返回true，否则返回false。
     */
    public static boolean isSameDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        Calendar nowCalendar = Calendar.getInstance();
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        return (nowCalendar.get(Calendar.ERA) == newCalendar.get(Calendar.ERA) &&
                nowCalendar.get(Calendar.YEAR) == newCalendar.get(Calendar.YEAR) &&
                nowCalendar.get(Calendar.DAY_OF_YEAR) == newCalendar.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss字符串转换成日期<br/>
     *
     * @param dateStr    时间字符串
     * @param dataFormat 当前时间字符串的格式。
     * @return Date 日期 ,转换异常时返回null。
     */
    public static Date parseDate(String dateStr, String dataFormat) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dataFormat, Locale.PRC);
            Date date = dateFormat.parse(dateStr);
            return new Date(date.getTime());
        } catch (ParseException e) {
            LogUtils.warn(e);
            return null;
        }
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss字符串转换成日期<br/>
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss字符串
     * @return Date 日期 ,转换异常时返回null。
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将指定的日期转换为一定格式的字符串
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.PRC);
        return sdf.format(date);
    }

    /**
     * 将当前日期转换为一定格式的字符串
     */
    public static String formatDate(String format) {
        return formatDate(Calendar.getInstance(Locale.CHINA).getTime(), format);
    }

    public static int formatDayToWeek(long day) {
        return (int) Math.ceil(day / 7) + 1;
    }

    @IntDef(value = {Second, Minute, Hour, Day})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DifferenceMode {
    }
}
