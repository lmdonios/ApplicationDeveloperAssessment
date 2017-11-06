package com.louiedonios.android.applicationdeveloperassessment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Louie on 11/6/2017.
 */

public class AccessNetworkState {

    private static final int[] networkTypes = {ConnectivityManager.TYPE_BLUETOOTH,
            ConnectivityManager.TYPE_DUMMY,
            ConnectivityManager.TYPE_ETHERNET,
            ConnectivityManager.TYPE_MOBILE,
            ConnectivityManager.TYPE_MOBILE_DUN,
            ConnectivityManager.TYPE_MOBILE_HIPRI,
            ConnectivityManager.TYPE_MOBILE_MMS,
            ConnectivityManager.TYPE_MOBILE_SUPL,
            ConnectivityManager.TYPE_VPN,
            ConnectivityManager.TYPE_WIFI,
            ConnectivityManager.TYPE_WIMAX};

    public static boolean isNetworkAvailable(Context context) {

        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : networkTypes) {
                NetworkInfo netInfo = cm.getNetworkInfo(networkType);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
