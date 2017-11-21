package com.es.es_dvtt_npc.Base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;


import com.es.es_dvtt_npc.BaseAPI.Api;
import com.es.es_dvtt_npc.BaseAPI.OkHttpApiImpl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by PhuongVV on 3/3/2017.
 */

public class App extends Application {

    public static App instance;
    private static ConnectivityManager sConnectivityManager;
    Api mApi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        mApi = new OkHttpApiImpl(okHttpClient);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public static App get() {
        return instance;
    }

    public Api getApi() {
        return mApi;
    }

    public static boolean isInternetAvailable() {
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
