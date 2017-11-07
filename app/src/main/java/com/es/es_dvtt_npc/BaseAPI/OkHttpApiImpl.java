package com.es.es_dvtt_npc.BaseAPI;

import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiDienSinhHoatRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.DVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetChildDVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.test;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class OkHttpApiImpl implements Api {

    private OkHttpClient mOkHttpClient;

    public OkHttpApiImpl(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    private void callApi(int requestId, BaseRequest baseRequest, ResponseListener listener) {
        Request.Builder request = new Request.Builder()
                .url(baseRequest.getUrl())
                .method(baseRequest.getMethod(), baseRequest.getBody());
        Headers headers = baseRequest.getHeaders();
        if (headers != null) {
            request.headers(headers);
        }
        mOkHttpClient.newCall(request.build()).enqueue(new CallBackWrapper(requestId, listener));
    }

    @Override
    public void getDVDChinh(int requestId, DVDChinhRequest dvdChinhRequest, ResponseListener listener) {
        callApi(requestId,dvdChinhRequest,listener);
    }

    @Override
    public void getChildDVDChinh(int requestId, GetChildDVDChinhRequest dvdChinhRequest, ResponseListener listener) {
        callApi(requestId,dvdChinhRequest,listener);
    }

    @Override
    public void postCapMoiDienSinhHoat(int requestId, CapMoiDienSinhHoatRequest capMoiDienSinhHoatRequest, ResponseListener listener) {
        callApi(requestId,capMoiDienSinhHoatRequest,listener);
    }

    @Override
    public void postCapMoiDienSinhHoat2(int requestId, test capMoiDienSinhHoatRequest, ResponseListener listener) {
        callApi(requestId,capMoiDienSinhHoatRequest,listener);
    }
}
