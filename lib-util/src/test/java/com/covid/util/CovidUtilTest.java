package com.covid.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CovidUtilTest {

    @Test
    public void formatDate() {

       /* String testUTCDate = "2018-04-10T04:00:00.000Z";
        String formattedResult = CovidUtil.formatDate(testUTCDate);

        String expectedDate = "10-04-2018 06:00:00";
        assertEquals(expectedDate, formattedResult);*/
    }

    @Test
    public void longToDateFormat() {
        /*long millisecond = 159083675;
        String date = CovidUtil.longToDateFormat(millisecond);
        String expected = "Jan, 02 1970, 21:11";
        assertEquals(date, expected);*/
    }

    @Test
    public void formatNumberText() {
        long toTest = 159083675;
        String expected = "159,083,675";
        String result = CovidUtil.formatNumberText(toTest);
        assertEquals(result, expected);
    }

    @Test
    public void formatPercent() {
        long actual = 7;
        long total = 11;
        String expected = "63.64%";
        String result = CovidUtil.formatPercent(actual, total);
        assertEquals(result, expected);
    }

    @Test
    public void formatPercentWithZeroDenominator() {
        long actual = 7;
        long total = 0;
        String expected = "0%";
        String result = CovidUtil.formatPercent(actual, total);
        assertEquals(result, expected);
    }
}