package com.es.es_dvtt_npc.BaseAPI.Request;


import android.net.Uri;
import android.text.TextUtils;

import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.RequestBody;

/**
 * Created by PhuongVV on 4/22/2017.
 */

public class LoginRequest implements BaseRequest {
    String User,Pass;

    @Override
    public String getUrl() {
        Uri.Builder builder = Uri.parse(Common.BASE_URL_SERVICE + "login?").buildUpon();
        if (!TextUtils.isEmpty(User)) {
            builder.appendQueryParameter("makh", User);
        }
        if (!TextUtils.isEmpty(Pass)) {
            builder.appendQueryParameter("matkhau", User);
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

    public void setPass(String pass) {
        Pass = pass;
    }
}
