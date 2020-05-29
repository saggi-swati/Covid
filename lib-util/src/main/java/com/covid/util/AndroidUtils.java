package com.covid.util;

import android.content.Context;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;

import static com.covid.util.SystemUtils.telephonyManager;

public class AndroidUtils {

    @NonNull
    public static String getCarrierCountry(@NonNull Context context) {
        String carrierCountryCode = "";
        TelephonyManager telephonyService = telephonyManager(context);

        if (telephonyService != null && telephonyService.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE) {
            carrierCountryCode = telephonyService.getNetworkCountryIso();
        }

        return carrierCountryCode;
    }
}
