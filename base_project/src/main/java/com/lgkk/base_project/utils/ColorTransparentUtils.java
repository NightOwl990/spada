package com.lgkk.base_project.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;

import com.lgkk.base_project.R;

public class ColorTransparentUtils {
    // This default color int
    public static final int defaultColor = R.color.colorAccent;
    public static final String TAG = "ColorTransparentUtils";

    /**
     * This method convert numver into hexa number or we can say transparent code
     * @param trans number of transparency you want
     * @return it return hex decimal number or transparency code
     */
    public static String convert(int trans) {
        String hexString = Integer.toHexString(Math.round(255 * trans/100));
        return (hexString.length()<2 ? "0" : "") + hexString;
    }

    public static String transparentColor10(int colorCode) {
        return convertIntoColor(colorCode,10);
    }

    public static String transparentColor20(int colorCode) {
        return convertIntoColor(colorCode,20);
    }

    public static String transparentColor30(int colorCode) {
        return convertIntoColor(colorCode,30);
    }

    public static String transparentColor40(int colorCode) {
        return convertIntoColor(colorCode,40);
    }

    public static String transparentColor50(int colorCode) {
        return convertIntoColor(colorCode,50);
    }

    public static String transparentColor60(int colorCode) {
        return convertIntoColor(colorCode,60);
    }

    public static String transparentColor70(int colorCode) {
        return convertIntoColor(colorCode,70);
    }

    public static String transparentColor80(int colorCode) {
        return convertIntoColor(colorCode,80);
    }

    public static String transparentColor90(int colorCode) {
        return convertIntoColor(colorCode,90);
    }

    public static String transparentColor100(int colorCode) {
        return convertIntoColor(colorCode,100);
    }

    /**
     * Convert color code into transparent color code
     * @param colorCode color code
     * @param transCode transparent number
     * @return transparent color code
     */
    public static String convertIntoColor(int colorCode,int transCode) {
        // convert color code into hexa string and remove starting 2 digit
        String color = Integer.toHexString(colorCode).toUpperCase().substring(2);
        if(!color.isEmpty() && transCode > 100) {
            if(color.trim().length() == 6) {
                return "#"+convert(transCode) + color;
            } else {
                return convert(transCode) + color;
            }
        }
        // if color is empty or any other problem occur then we return deafult color;
        return "#"+Integer.toHexString(defaultColor).toUpperCase().substring(2);
    }

    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public static void setBackgroundColorAndRetainShape(final int color, final Drawable background) {

        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background.mutate()).getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background.mutate()).setColor(color);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background.mutate()).setColor(color);
        }else{

        }

    }
}
