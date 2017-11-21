package com.es.es_dvtt_npc.BaseAPI.Request;


import android.net.Uri;
import android.text.TextUtils;

import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;

import okhttp3.Headers;
import okhttp3.RequestBody;

/**
 * Created by PhuongVV on 4/22/2017.
 */

public class CustomerInfoRequest implements BaseRequest {
    String User;

    @Override
    public String getUrl() {
        Uri.Builder builder = Uri.parse(Common.BASE_URL_SERVICE + "ttcanhan?").buildUpon();
        if (!TextUtils.isEmpty(User)) {
            builder.appendQueryParameter("makh", User);
        }
        return builder.toString();
    }

    @Override
    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder()
                .add("Content-Type", "application/json");
        return builder.build();
    }

    @Override
    public String getMethod() {
        return Common.GET;
    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public RequestBody getBody() {
        return null;
    }

    public void setUser(String user) {
        User = user;
    }

}
