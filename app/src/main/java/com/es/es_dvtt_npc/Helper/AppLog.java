package com.es.es_dvtt_npc.Helper;

import android.util.Log;

public class AppLog {

    private static final boolean isEnabled = false ;

    private static final String TAG = "My App";

    public static void d(String tag, String message) {
        if (isEnabled) return;
        if (message == null || tag == null) return;
        Log.d(tag, message);
    }

    public static void d(String message) {
        if (isEnabled) return;
        if (message == null) return;
        Log.d(TAG, message);
    }

    public static void e(String message) {
        if (isEnabled) return;
        if (message == null) return;
        Log.e(TAG, message);
    }

    public static void i(String message) {
        if (isEnabled) return;
        if (message == null) return;
        Log.i(TAG, message);
    }

    public static void i(String tag, String message) {
        if (isEnabled) return;
        if (message == null || tag == null) return;
        Log.i(tag, message);
    }

    public static void e(String tag, String message) {
        if (isEnabled) return;
        if (message == null || tag == null) return;
        Log.e(tag, message);
    }

    public static void w(String tag, String message) {
        if (isEnabled) return;
        if (message == null || tag == null) return;
        Log.w(tag, message);
    }

    public static void w(String message) {
        if (isEnabled) return;
        if (message == null) return;
        Log.w(TAG, message);
    }
}
