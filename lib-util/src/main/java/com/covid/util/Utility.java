package com.covid.util;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utility {

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


    @NotNull
    public static String formatPercentString(String closedCasesDeathPercentage) {
        return String.format("%s%s",
                closedCasesDeathPercentage, "%");
    }

    public static long getRandomNumber() {
        long x = (long) ((Math.random() * ((100000 - 0) + 1)) + 0);
        return x;
    }
}
