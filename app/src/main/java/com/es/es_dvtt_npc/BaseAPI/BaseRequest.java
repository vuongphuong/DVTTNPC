package com.es.es_dvtt_npc.BaseAPI;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by My_PC on 9/5/2017.
 */

public interface BaseRequest {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    MediaType FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    MediaType IMAGE = MediaType.parse("image/*");

    String getUrl();

    String toJson();

    Headers getHeaders();

    String getMethod();

    RequestBody getBody();
}
