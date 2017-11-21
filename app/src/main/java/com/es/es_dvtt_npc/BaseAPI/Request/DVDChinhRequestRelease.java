package com.es.es_dvtt_npc.BaseAPI.Request;


import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;

import okhttp3.Headers;
import okhttp3.RequestBody;

/**
 * Created by PhuongVV on 4/22/2017.
 */

public class DVDChinhRequestRelease implements BaseRequest {

    @Override
    public String getUrl() {
        return "http://116.212.37.61:8000/Service1.svc/dstinhthanh";
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
}
