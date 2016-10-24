package com.aiyouwai.aphotoalbum.utils;

import android.util.Log;

import com.aiyouwai.aphotoalbum.BuildConfig;

public class LogUtil {

    private static String sTAG = "APhotoAlbun";

    public static void v(String message) {
        // adb shell setprop log.tag.<YOUR_LOG_TAG> <LEVEL>
        if (BuildConfig.DEBUG /* && Log.isLoggable(sTAG, Log.VERBOSE) */) {
            Log.v(sTAG, message);
        }
    }

    public static void i(String message) {
        Log.i(sTAG, message);
    }

    public static void d(String message) {
        Log.d(sTAG, message);
    }

    public static void e(String message) {
        Log.e(sTAG, message);
    }
}

