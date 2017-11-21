package com.es.es_dvtt_npc.BaseAPI.Reponse;


import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.BaseAPI.Request.CustomerInfoRequest;
import com.es.es_dvtt_npc.Data.Object.CustomerInfoEntity;
import com.es.es_dvtt_npc.Data.Object.LoginEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by PhuongVV on 5/1/2017.
 */

public class CustomerInfoReponse extends BaseResponse {

    private CustomerInfoEntity customerInfoEntity;
    private String message;

    public CustomerInfoReponse(Response data) throws IOException, JSONException {
        super(data);
    }

    @Override
    protected void parseData(Response data) throws IOException {
        Gson gson = new Gson();
        String jsonData = data.body().string();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonData);
            if (jsonObject.optInt("status")!= 200)
                message = jsonObject.optString("content");
            else
                customerInfoEntity = gson.fromJson(jsonObject.optString("data"),CustomerInfoEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CustomerInfoEntity customerInfoEntity() {
        return customerInfoEntity;
    }

    public String getMessage(){return message;}
}
