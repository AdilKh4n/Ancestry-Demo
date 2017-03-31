package com.example.adilkhan.ancestry_demo;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by adilkhan on 2/5/17.
 */

public class NetworkConnectivity {
    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
