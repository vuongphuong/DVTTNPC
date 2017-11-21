package com.es.es_dvtt_npc.BaseAPI.errors;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by PhuongVV on 9/5/2017.
 */
public abstract class BaseError extends Exception {
    protected Call call;
    protected Response response;

    public BaseError(Call call, Response response) {
        this.call = call;
        this.response = response;
    }

    public Call getCall() {
        return call;
    }

    public Response getResponse() {
        return response;
    }
}
