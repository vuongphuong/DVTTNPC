package com.es.es_dvtt_npc.BaseAPI.Request;


import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;

import java.io.File;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by hungh on 5/7/2017.
 */

public class test implements BaseRequest {
    private File imageFile;
    private String model;

    @Override
    public String getUrl() {
        return Common.URL +"TestHttp/PostDataWithModel";
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
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("json","model",RequestBody.create(JSON,model)).build();
        return RequestBody.create(JSON,model);
    }

    public void setModel(File model) {
        this.imageFile = model;
    }

    public void setImageFile(String model){
        this.model = model;
    }
}