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

public class DVDChinhResponse extends BaseResponse {

    private ArrayList<CityEntity> cityEntities;
    private String result,message;

    public DVDChinhResponse(Response data) throws IOException, JSONException {
        super(data);
    }

    @Override
    protected void parseData(Response data) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<CityEntity>>() {}.getType();
        String jsonData = data.body().string();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonData);
            result = jsonObject.optString(Common.RESULT);
            if (result.equals(Common.FALSE))
                message = jsonObject.optString(Common.MESSAGE);
            else
                cityEntities = gson.fromJson(jsonObject.optString(Common.DATA), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CityEntity> getCityEntities() {
        return cityEntities;
    }

    public String getMessage(){return message;}
}
