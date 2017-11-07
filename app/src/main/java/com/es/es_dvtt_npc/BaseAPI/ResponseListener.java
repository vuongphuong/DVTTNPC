package com.es.es_dvtt_npc.BaseAPI;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by My_PC on 9/5/2017.
 */
public interface ResponseListener {

    BaseResponse parse(int requestId, Call call, Response response) throws Exception;

    void onResponse(int requestId, BaseResponse response);

    void onError(int requestId, Exception e);
}
