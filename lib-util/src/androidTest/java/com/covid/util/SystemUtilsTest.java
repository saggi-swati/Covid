package com.covid.util;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SystemUtilsTest {

    private Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void locationManager() {
        assertNotNull(SystemUtils.locationManager(getContext()));
    }

    @Test
    public void alarmManager() {
        assertNotNull(SystemUtils.alarmManager(getContext()));
    }

    @Test
    public void connectivityManager() {
        assertNotNull(SystemUtils.connectivityManager(getContext()));
    }

    @Test
    public void notificationManager() {
        assertNotNull(SystemUtils.notificationManager(getContext()));
    }

    @Test
    public void jobScheduler() {
        assertNotNull(SystemUtils.jobScheduler(getContext()));
    }

    @Test
    public void telephonyManager() {
        assertNotNull(SystemUtils.telephonyManager(getContext()));
    }
}