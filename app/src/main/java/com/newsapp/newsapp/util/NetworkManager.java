package com.newsapp.newsapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkManager {

  private static final String TAG = NetworkManager.class.getSimpleName();

  public static boolean isNetworkAvailable(Context context) {

    ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    try {
      if (connectivityManager != null) {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
          return true;
        } else {
          return false;
        }
      }
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
    return false;
  }
}
