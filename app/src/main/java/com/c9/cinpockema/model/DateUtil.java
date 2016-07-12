package com.c9.cinpockema.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marionette on 2016/6/16.
 */
public class DateUtil {
    public static String longToDateStr(Long longNum) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        date.setTime(longNum);
        return sdf.format(date);
    }
}
