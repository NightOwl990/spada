package com.lgkk.spada.base;

import com.lgkk.base_project.utils.DateUtils;
import com.lgkk.base_project.widget.TitleFlashSale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBLocalData {

    public static List<TitleFlashSale> getTitleFlashSaleList(long milisecond, int rangeHour) {
        List<TitleFlashSale> list = new ArrayList<>();
        Date date = new Date();
        date.setTime(milisecond);
        int currentHour = Integer.parseInt(DateUtils.formatDate(date, "HH"));
        int startingHour = currentHour / rangeHour ;
        list.add(new TitleFlashSale(startingHour * rangeHour,  "Đang diễn ra" ));
        int maxTitleCount = 24 / rangeHour;
        int startTitleCount = 1;
        for (int i=1; i <= 24; i++) {
            list.add(new TitleFlashSale(((startingHour+i) * rangeHour) % 24,  "Sắp diễn ra" ));
            startTitleCount++;
            if (startTitleCount == maxTitleCount) break;
        }
        return list;
    }
}
