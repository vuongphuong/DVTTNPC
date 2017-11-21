package com.es.es_dvtt_npc.BaseAPI;

import android.widget.Toast;

import com.es.es_dvtt_npc.Base.App;
import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiDienNgoaiSinhHoatRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiDienSinhHoatRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiSinhHoatMutiRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.CustomerInfoRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.DVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetChildDVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetNganHangRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.LoginRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.ThayDoiCongSuatRequest;
import com.es.es_dvtt_npc.Helper.AppAlertDialog;
import com.es.es_dvtt_npc.Helper.Common;

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
    public void getNganHang(int requestId, GetNganHangRequest getNganHangRequest, ResponseListener listener) {
        callApi(requestId,getNganHangRequest,listener);
    }

    @Override
    public void login(int requestId, LoginRequest loginRequest, ResponseListener listener) {
        callApi(requestId,loginRequest,listener);
    }

    @Override
    public void customerInfo(int requestId, CustomerInfoRequest customerInfoRequest, ResponseListener listener) {
        callApi(requestId,customerInfoRequest,listener);
    }

    @Override
    public void postCapMoiDienNgoaiSinhHoat(int requestId, CapMoiDienNgoaiSinhHoatRequest capMoiDienSinhHoatRequest, ResponseListener listener) {
        callApi(requestId,capMoiDienSinhHoatRequest,listener);
    }

    @Override
    public void postCapMoiDienSinhHoat2(int requestId, CapMoiSinhHoatMutiRequest capMoiDienSinhHoatRequest, ResponseListener listener) {
        callApi(requestId,capMoiDienSinhHoatRequest,listener);
    }

    @Override
    public void postThayDoiCongSuat(int requestId, ThayDoiCongSuatRequest thayDoiCongSuatRequest, ResponseListener listener) {
        callApi(requestId,thayDoiCongSuatRequest,listener);
    }
}
