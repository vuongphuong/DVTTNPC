package com.es.es_dvtt_npc.BaseAPI.Reponse;


import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.Data.Object.CityEntity;
import com.es.es_dvtt_npc.Helper.Common;
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

public class CapMoiSinhHoatMultiReponse extends BaseResponse {

    private String message;
    private String result;

    public CapMoiSinhHoatMultiReponse(Response data) throws IOException, JSONException {
        super(data);
    }

    @Override
    protected void parseData(Response data) throws IOException {
        String jsonData = data.body().string();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonData);
            result = jsonObject.optString(Common.RESULT);
            message = jsonObject.optString(Common.MESSAGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}
