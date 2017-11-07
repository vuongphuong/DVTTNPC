package com.es.es_dvtt_npc.BaseAPI;


import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiDienSinhHoatRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.DVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetChildDVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.test;

/**
 * Created by My_PC on 9/5/2017.
 */
public interface Api {

//    void register(int requestId, RegisterRequest registerRequest, ResponseListener listener);
void getDVDChinh(int requestId, DVDChinhRequest dvdChinhRequest, ResponseListener listener);
void getChildDVDChinh(int requestId, GetChildDVDChinhRequest dvdChinhRequest, ResponseListener listener);
void postCapMoiDienSinhHoat(int requestId, CapMoiDienSinhHoatRequest capMoiDienSinhHoatRequest, ResponseListener listener);
void postCapMoiDienSinhHoat2(int requestId, test capMoiDienSinhHoatRequest, ResponseListener listener);
}
