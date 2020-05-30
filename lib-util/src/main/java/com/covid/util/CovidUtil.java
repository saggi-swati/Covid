package com.covid.util;

import android.util.Log;

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

    private static final String TAG = CovidUtil.class.getSimpleName();

    public static String formatDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) return "";

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException e) {
            Log.d(TAG, "formatDate : Exception occurred during parsing - " + e.getMessage());
        }

        return date == null ? "" : outputFormat.format(date);
    }

    public static String longToDateFormat(long milliSeconds) {

        DateFormat formatter = new SimpleDateFormat("MMM, dd yyyy, HH:mm", Locale.ENGLISH);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        return formatter.format(calendar.getTime());
    }

    public static String formatNumberText(long val) {
        return NumberFormat.getInstance().format(val);
    }

    public static String formatPercent(double val, double total) {
        if (total == 0 || val == 0) return "0%";
        double percent = (val * 100) / total;
        return String.format("%s%s",
                new DecimalFormat("##.00").format(percent), "%");
    }
}
