package com.es.es_dvtt_npc.BaseAPI;


import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiDienNgoaiSinhHoatRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiSinhHoatMutiRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.CustomerInfoRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.DVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetChildDVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetNganHangRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.LoginRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.ThayDoiCongSuatRequest;
import com.es.es_dvtt_npc.Data.Object.CustomerInfoEntity;

/**
 * Created by PhuongVV on 9/5/2017.
 */
public interface Api {

void getDVDChinh(int requestId, DVDChinhRequest dvdChinhRequest, ResponseListener listener);
void getChildDVDChinh(int requestId, GetChildDVDChinhRequest dvdChinhRequest, ResponseListener listener);
void getNganHang(int requestId, GetNganHangRequest getNganHangRequest, ResponseListener listener);
void login(int requestId, LoginRequest loginRequest, ResponseListener listener);
void customerInfo(int requestId, CustomerInfoRequest customerInfoRequest, ResponseListener listener);
void postCapMoiDienNgoaiSinhHoat(int requestId, CapMoiDienNgoaiSinhHoatRequest capMoiDienSinhHoatRequest, ResponseListener listener);
void postCapMoiDienSinhHoat2(int requestId, CapMoiSinhHoatMutiRequest capMoiDienSinhHoatRequest, ResponseListener listener);
void postThayDoiCongSuat(int requestId, ThayDoiCongSuatRequest thayDoiCongSuatRequest, ResponseListener listener);
}
