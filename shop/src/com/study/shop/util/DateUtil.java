package com.study.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //当天日期字符串
    public static String getCurrentDate() {
        Date today = new Date();
        String now = formatDate(today);
        return now.substring(0, 11);
    }
}
