package com.tatipati.file.util.date;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String toPersianString(Date date){//20 تیر 1399
        DateTime dt = new DateTime(date);
        CalendarTool calendarTool=new CalendarTool(dt.getYear(),dt.getMonthOfYear(),dt.getDayOfMonth());
        return calendarTool.getIranianDay()+" "+calendarTool.getMonthStrFa()+" "+calendarTool.getIranianYear();
    }
    public static String getDateTimeWithTimeZone(Date date){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ").format(date);
    }
}
