package com.covid.util;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AndroidUtilsTest {

    private Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.

        assertEquals("com.covid.util.test", getContext().getPackageName());
    }

    @Test
    public void getCarrierCountry() {
        assertEquals(AndroidUtils.getCarrierCountry(getContext()).toLowerCase(), "US".toLowerCase());
    }
}