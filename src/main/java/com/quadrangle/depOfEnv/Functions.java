package com.quadrangle.depOfEnv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Functions {
    public static Date getTimeStamp() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");
        simpleDateFormat.setTimeZone(timeZone);
        Date date = new Date();

        String sDate = simpleDateFormat.format(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");

        return format.parse(sDate);
    }
}
