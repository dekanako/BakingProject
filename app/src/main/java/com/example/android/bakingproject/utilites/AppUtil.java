package com.example.android.bakingproject.utilites;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class AppUtil {

    public static String capitalizeFirstLetter(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * source https://medium.com/code-better/check-network-connectivity-on-android-in-10-lines-60d10361b73
     * @param context
     * @return
     */
    public static boolean isConnectedToNetwork(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isConnected = false;
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
        }

        return isConnected;
    }
}
