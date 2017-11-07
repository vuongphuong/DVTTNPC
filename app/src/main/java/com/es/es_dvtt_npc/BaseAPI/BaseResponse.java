package com.es.es_dvtt_npc.BaseAPI;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by My_PC on 9/5/2017.
 */

public abstract class BaseResponse {
    public BaseResponse(Response response) throws IOException, JSONException {
        parseData(response);
    }

    protected abstract void parseData(Response data) throws IOException, JSONException;
}
