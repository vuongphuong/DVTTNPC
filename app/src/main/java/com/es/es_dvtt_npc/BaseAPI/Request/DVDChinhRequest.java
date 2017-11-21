package com.es.es_dvtt_npc.BaseAPI.Request;


import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.Singleton;

import okhttp3.Headers;
import okhttp3.RequestBody;

/**
 * Created by PhuongVV on 4/22/2017.
 */

public class DVDChinhRequest implements BaseRequest {

    @Override
    public String getUrl() {
        return Common.URL + "D_DVI_DCHINH/GetActiveRoots";
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
