package com.es.es_dvtt_npc.BaseAPI.Reponse;


import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.Data.Object.CityEntity;
import com.es.es_dvtt_npc.Data.Object.LoginEntity;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.CurrentPrefers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by PhuongVV on 5/1/2017.
 */

public class LoginReponse extends BaseResponse {

    private LoginEntity loginEntity;
    private String message;

    public LoginReponse(Response data) throws IOException, JSONException {
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
                loginEntity = gson.fromJson(jsonObject.optString("data"),LoginEntity.class);
                CurrentPrefers.getInstance().saveUserInfo(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public LoginEntity loginEntity() {
        return loginEntity;
    }

    public String getMessage(){return message;}
}
