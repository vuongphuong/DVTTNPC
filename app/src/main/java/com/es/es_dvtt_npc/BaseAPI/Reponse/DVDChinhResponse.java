package com.es.es_dvtt_npc.BaseAPI.Reponse;


import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.Data.Object.CityEntity;
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

    public DVDChinhResponse(Response data) throws IOException, JSONException {
        super(data);
    }

    @Override
    protected void parseData(Response data) throws IOException {
        Gson gson = new Gson();
//        ManagementUnitEntity managementUnitEntity = gson.fromJson(data.body().string(),ManagementUnitEntity.class);
//        managementUnitEntities = new ArrayList<>();
//        managementUnitEntities.add(managementUnitEntity);
        Type type = new TypeToken<ArrayList<CityEntity>>() {
        }.getType();
        String jsonData = data.body().string();
        JSONObject Jobject = null;
        try {
            Jobject = new JSONObject(jsonData);
            cityEntities = gson.fromJson(Jobject.optString("Data"), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<CityEntity> getCityEntities() {
        return cityEntities;
    }
}
