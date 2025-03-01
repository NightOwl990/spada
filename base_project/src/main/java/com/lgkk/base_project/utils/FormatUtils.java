package com.lgkk.base_project.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lgkk.base_project.R;
import com.lgkk.base_project.base.Constants;
import com.lgkk.base_project.widget.SimpleEvaluateStarView;
import com.lgkk.base_project.widget.datepicker.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FormatUtils {

    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String FORMAT_DATE_TIME_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;
    private static final String ONE_SECOND_AGO = " giây";
    private static final String ONE_MINUTE_AGO = " phút";
    private static final String ONE_HOUR_AGO = " giờ";
    private static final String ONE_DAY_AGO = " ngày";
    private static final String ONE_MONTH_AGO = " tháng";
    private static final String ONE_YEAR_AGO = " năm";
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static String yearPick, monthPick, dayPick,
            yearPickOld, monthPickOld, dayPickOld,
            datePickOld, datePickNew;
    private static int oldWeek, weekPick;

    public static String getCurrentTimeString(String text) {
        sdf.applyPattern(FORMAT_DATE_TIME_2);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(text);
    }

    /**
     * 根据时间字符串获取描述性时间，如3分钟前，1天前
     *
     * @param dateString 时间字符串
     * @return
     */
    public static String getDescriptionTimeFromDateString(String dateString) {
        if (TextUtils.isEmpty(dateString))
            return "";
        sdf.applyPattern(FORMAT_DATE_TIME);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return getDescriptionTimeFromDate(sdf.parse(formatZhuiShuDateString(dateString)));
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 格式化追书神器返回的时间字符串
     *
     * @param dateString 时间字符串
     * @return
     */
    public static String formatZhuiShuDateString(String dateString) {
        return dateString.replaceAll("T", " ").replaceAll("Z", "");
    }

    /**
     * 根据Date获取描述性时间，如3分钟前，1天前
     *
     * @param date
     * @return
     */
    public static String getDescriptionTimeFromDate(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "Hôm qua";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp - 1));
    }

    public static String formatAstrology(int day, int month) {
        String astrology = "";
        switch (month) {
            case 1:
                if (day >= 1 && day <= 20) {
                    astrology = "Ma Kết";
                } else {
                    astrology = "Bảo Bình";
                }
                break;
            case 2:
                if (day >= 1 && day <= 19) {
                    astrology = "Bảo Bình";
                } else {
                    astrology = "Song Ngư";
                }
                break;
            case 3:
                if (day >= 1 && day <= 20) {
                    astrology = "Song Ngư";
                } else {
                    astrology = "Bạch Dương";
                }
                break;
            case 4:
                if (day >= 1 && day <= 20) {
                    astrology = "Bạch Dương";
                } else {
                    astrology = "Kim Ngưu";
                }
                break;
            case 5:
                if (day >= 1 && day <= 21) {
                    astrology = "Kim Ngưu";
                } else {
                    astrology = "Song Tử";
                }
                break;
            case 6:
                if (day >= 1 && day <= 21) {
                    astrology = "Song Tử";
                } else {
                    astrology = "Cự Giải";
                }
                break;
            case 7:
                if (day >= 1 && day <= 23) {
                    astrology = "Cự Giải";
                } else {
                    astrology = "Sư Tử";
                }
                break;
            case 8:
                if (day >= 1 && day <= 23) {
                    astrology = "Cự Giải";
                } else {
                    astrology = "Xử Nữ";
                }
                break;
            case 9:
                if (day >= 1 && day <= 23) {
                    astrology = "Xử Nữ";
                } else {
                    astrology = "Thiên Bình";
                }
                break;
            case 10:
                if (day >= 1 && day <= 23) {
                    astrology = "Thiên Bình";
                } else {
                    astrology = "Bọ Cạp";
                }
                break;
            case 11:
                if (day >= 1 && day <= 22) {
                    astrology = "Bọ Cạp";
                } else {
                    astrology = "Nhân Mã";
                }
                break;
            case 12:
                if (day >= 1 && day <= 21) {
                    astrology = "Nhân Mã";
                } else {
                    astrology = "Ma Kết";
                }
                break;
        }
        return astrology;
    }

    public static String formatTimeTZToDate(String text) {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        Calendar cal = Calendar.getInstance(tz);
        SimpleDateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat destDf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        srcDf.setTimeZone(tz);
        Date date = new Date();
        try {
            date = srcDf.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return destDf.format(date);
    }

    public static String formatTimeTZToDateWithoutHour(String text) {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        Calendar cal = Calendar.getInstance(tz);
        SimpleDateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat destDf = new SimpleDateFormat("dd-MM-yyyy");
        srcDf.setTimeZone(tz);
        Date date = new Date();
        try {
            date = srcDf.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return destDf.format(date);
    }

    public static String formatMilisecondTZToDateWithoutHour(long time) {

        SimpleDateFormat destDf = new SimpleDateFormat("dd - MM - yyyy");
        destDf.setTimeZone(TimeZone.getTimeZone("GMT"));

        return destDf.format(new Date(time));
    }

    public static String formatMilisecondTZToDateWithoutHourAndSpace(long time) {

        SimpleDateFormat destDf = new SimpleDateFormat("dd-MM-yyyy");
        destDf.setTimeZone(TimeZone.getTimeZone("GMT"));

        return destDf.format(new Date(time));
    }

    public static String formatTime(int hour, int minute) {
        return addZero(hour) + ":" + addZero(minute);
    }

    public static String addZero(int time) {
        if (String.valueOf(time).length() == 1) {
            return "0" + time;
        }

        return String.valueOf(time);
    }

    public static void changeSizeRatingBar(Context activity, RatingBar ratingBar, double size) {
        Drawable starDrawable = activity.getResources().getDrawable(R.drawable.evaluate_star_10);
        int height = starDrawable.getMinimumHeight();
        ViewGroup.LayoutParams params = ratingBar.getLayoutParams();
        params.height = (int) (height * size);
        ratingBar.setLayoutParams(params);
    }

    public static void changeSizeSimpleValuateRatingBar(Context activity, SimpleEvaluateStarView ratingBar, double size) {
        Drawable starDrawable = activity.getResources().getDrawable(R.drawable.evaluate_star_10);
        int height = starDrawable.getMinimumHeight();
        ViewGroup.LayoutParams params = ratingBar.getLayoutParams();
        params.height = (int) (height * size);
        ratingBar.setLayoutParams(params);
    }

    public static void setDefaultDatePicker(DatePicker picker, Activity activity, TextView textView) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -Constants.MAX_BABY_GROW + 1);
        int startPregnantYear = calendar.get(Calendar.YEAR);
        int startPregnantMonth = calendar.get(Calendar.MONTH) + 1;
        int startPregnantDay = calendar.get(Calendar.DAY_OF_MONTH);
        Window window = picker.getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);

        picker.setLabelTextColor(Color.WHITE);
        picker.setTopHeight((int) activity.getResources().getDimension(R.dimen.length_40));
        picker.setTopLineColor(Color.WHITE);

        picker.setTitleTextColor(activity.getResources().getColor(R.color.text_dark));
        picker.setTextColor(activity.getResources().getColor(R.color.text_dark));
        picker.setDividerColor(activity.getResources().getColor(R.color.white));
        picker.setBackgroundColor(activity.getResources().getColor(R.color.white));
        picker.setTextSize((int) activity.getResources().getDimension(R.dimen.length_8));
        picker.setCancelTextColor(activity.getResources().getColor(R.color.stpi_default_primary_color));
        picker.setCancelText("Hủy");
        picker.setCancelTextSize((int) activity.getResources().getDimension(R.dimen.length_8));
        picker.setSubmitTextColor(activity.getResources().getColor(R.color.stpi_default_primary_color));
        picker.setSubmitTextSize((int) activity.getResources().getDimension(R.dimen.length_8));
        picker.setSubmitText("Chọn");
        picker.setResetWhileWheel(true);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(activity, 10));
//
//        switch (textView.getId()) {
//            case R.id.txtBirthday:
//                picker.setTitleText("Ngày sinh của bạn");
//                picker.setRangeEnd(mYear - 18, 12, 31);
//                picker.setRangeStart(mYear - 50, 1, 1);
//                picker.setSelectedItem(mYear - 23, 6, 15);
//                datePickOld = datePickNew = "";
//                yearPickOld = yearPick = mYear - 23 + "";
//                monthPickOld = monthPick = "6";
//                dayPickOld = dayPick = "15";
//                break;
//            case R.id.txtPregnant:
//                picker.setTitleText("Ngày đầu chu kỳ kinh cuối");
//                picker.setRangeEnd(mYear, mMonth, mDay);
//                picker.setRangeStart(startPregnantYear, startPregnantMonth, startPregnantDay);
//                picker.setSelectedItem(mYear, mMonth, mDay);
//                datePickOld = datePickNew = "";
//                yearPickOld = yearPick = mYear + "";
//                monthPickOld = monthPick = mMonth + "";
//                dayPickOld = dayPick = mDay + "";
//                break;
//        }
//
//
//        picker.setOnDatePickListener((DatePicker.OnYearMonthDayPickListener) (year, month, day) -> {
//            datePickNew = dayPick+ " - " + monthPick + " - " +yearPick;
//            if (textView.getId() == R.id.txtPregnant) {
//                Data.isChoosePregnant = true;
//                Data.currentWeek = oldWeek = weekPick = getWeek(datePickNew);
//                SharedPreferencesUtil.getInstance().putInt("CurrentWeek", Data.currentWeek);
//                datePickNew = datePickNew + " ( Tuần " + oldWeek + " )";
//                Data.datePregnant = datePickNew;
//            } else {
//                datePickNew = dayPick + " - " + monthPick + " - " + yearPick;
//                Data.isChooseBirthday = true;
//                textView.setVisibility(View.VISIBLE);
//                Data.dateBirthday = datePickNew;
//            }
//
//            datePickOld = datePickNew;
//            textView.setText(datePickNew);
//            yearPickOld = yearPick;
//            monthPickOld = monthPick;
//            dayPickOld = dayPick;
//            picker.setSelectedItem(Integer.parseInt(yearPick), Integer.parseInt(monthPick), Integer.parseInt(dayPick));
//        });
//
//
//        picker.setOnDismissListener(dialog -> {
//            textView.setText(textView.getId() == R.id.txtBirthday ? Data.dateBirthday : Data.datePregnant);
//            picker.dismiss();
//        });
//
//        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
//            @Override
//            public void onYearWheeled(int index, String year) {
//                textView.setVisibility(View.VISIBLE);
//                yearPick = year;
//                String text = picker.getSelectedDay() + " - " + picker.getSelectedMonth() + " - " + year;
//                textView.setText(textView.getId() == R.id.txtBirthday ? text : text + " ( Tuần " + getWeek(text) + " )");
//            }
//
//            @Override
//            public void onMonthWheeled(int index, String month) {
//                textView.setVisibility(View.VISIBLE);
//                monthPick = month;
//                String text = picker.getSelectedDay() + " - " + month + " - " + picker.getSelectedYear();
//                textView.setText(textView.getId() == R.id.txtBirthday ? text : text + " ( Tuần " + getWeek(text) + " )");
//            }
//
//            @Override
//            public void onDayWheeled(int index, String day) {
//                textView.setVisibility(View.VISIBLE);
//                dayPick = day;
//                String text = day + " - " + picker.getSelectedMonth() + " - " + picker.getSelectedYear();
//                textView.setText(textView.getId() == R.id.txtBirthday ? text : text + " ( Tuần " + getWeek(text) + " )");
//            }
//        });

    }

    private static int getWeek(String datePick) {
        SimpleDateFormat format = new SimpleDateFormat("dd - MM - yyyy");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date1 = format.parse(datePick);
            int week = DateUtils.formatDayToWeek(DateUtils.calculateDifferentDay(date1, new Date()));
            return week;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}