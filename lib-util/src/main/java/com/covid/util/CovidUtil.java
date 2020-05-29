package com.covid.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CovidUtil {

    public static String formatDate(String dateString) {

        SimpleDateFormat df = new SimpleDateFormat("MMM, dd yyyy, HH:mm", Locale.ENGLISH);

        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(dateString);
            df.setTimeZone(TimeZone.getDefault());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date != null ? df.format(date) : null;
    }

    public static String longToDateFormat(long milliSeconds) {

        DateFormat formatter = new SimpleDateFormat("MMM, dd yyyy, HH:mm", Locale.ENGLISH);

        System.out.println(milliSeconds);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        return formatter.format(calendar.getTime());
    }

    public static String formatNumberText(long val) {
        return NumberFormat.getInstance().format(val);
    }

    public static String formatPercent(double val, double total) {
        double percent = (val * 100) / total;
        return String.format("%s%s",
                new DecimalFormat("##.00").format(percent), "%");
    }
}
