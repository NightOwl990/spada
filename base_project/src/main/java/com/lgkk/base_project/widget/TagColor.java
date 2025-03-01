package com.lgkk.base_project.widget;

import android.graphics.Color;

import com.lgkk.base_project.base.Constants;

import java.util.ArrayList;
import java.util.List;

public class TagColor {

    public int borderColor = Color.parseColor("#49C120");
    public int backgroundColor = Color.parseColor("#49C120");
    public int textColor = Color.WHITE;

    public static List<TagColor> getRandomColors(int size) {

        List<TagColor> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TagColor color = new TagColor();
            color.borderColor = color.backgroundColor = Constants.tagColors[i % Constants.tagColors.length];
            list.add(color);
        }
        return list;
    }
}
