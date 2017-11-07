package com.es.es_dvtt_npc.BaseAPI.Request;

import android.net.Uri;


import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by hungh on 5/7/2017.
 */

public class CapMoiDienSinhHoatRequest implements BaseRequest {
    private String model;

    @Override
    public String getUrl() {
        return Common.URL +"DV_YCAU_KNAI_WEB/" + "TNhanSH";
    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder()
                .add("Content-Type", "application/json");
        return builder.build();
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public RequestBody getBody() {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file","model",RequestBody.create(IMAGE,model)).build();
        return RequestBody.create(JSON,model);
    }

    public void setModel(String model) {
        this.model = model;
    }
}