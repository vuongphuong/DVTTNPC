package com.es.es_dvtt_npc.BaseAPI.Request;


import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Data.Object.NganHangEntity;
import com.es.es_dvtt_npc.Helper.Common;

import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.RequestBody;

/**
 * Created by PhuongVV on 4/22/2017.
 */

public class GetNganHangRequest implements BaseRequest {

    @Override
    public String getUrl() {
        return Common.URL + "D_NGAN_HANG/Get_D_NGAN_HANG";
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
