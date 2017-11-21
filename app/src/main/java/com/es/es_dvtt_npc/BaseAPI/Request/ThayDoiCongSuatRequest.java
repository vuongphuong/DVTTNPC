package com.es.es_dvtt_npc.BaseAPI.Request;

import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;

import java.io.File;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by PhuongVV on 5/7/2017.
 */

public class ThayDoiCongSuatRequest implements BaseRequest {
    File fileDangKyCongSuat, fileDangKyPhuTai,fileXacDinhChuThe,fileVanBanCuaCoQuan;
    private String model;

    @Override
    public String getUrl() {
        return Common.URL +"DV_YCAU_KNAI_WEB/" + "ThayDoiCongSuat";
    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder()
                .add("Content-Type", "application/json");
        return builder.build();
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public RequestBody getBody() {
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        requestBody.addFormDataPart("model",model);
        if (fileDangKyCongSuat != null)
            requestBody.addFormDataPart("fileDangKyCongSuat",fileDangKyCongSuat.getName(),RequestBody.create(IMAGE,fileDangKyCongSuat));
        if (fileDangKyPhuTai != null)
            requestBody.addFormDataPart("fileDangKyPhuTai",fileDangKyPhuTai.getName(),RequestBody.create(IMAGE,fileDangKyPhuTai));
        if (fileXacDinhChuThe != null)
            requestBody.addFormDataPart("fileXacDinhChuThe",fileXacDinhChuThe.getName(),RequestBody.create(IMAGE,fileXacDinhChuThe));
        if (fileVanBanCuaCoQuan != null)
            requestBody.addFormDataPart("fileVanBanCuaCoQuan",fileVanBanCuaCoQuan.getName(),RequestBody.create(IMAGE,fileVanBanCuaCoQuan));
        return requestBody.build();
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFileDangKyCongSuat(File fileDangKyCongSuat) {
        this.fileDangKyCongSuat = fileDangKyCongSuat;
    }

    public void setFileDangKyPhuTai(File fileDangKyPhuTai) {
        this.fileDangKyPhuTai = fileDangKyPhuTai;
    }

    public void setFileXacDinhChuThe(File fileXacDinhChuThe) {
        this.fileXacDinhChuThe = fileXacDinhChuThe;
    }

    public void setFileVanBanCuaCoQuan(File fileVanBanCuaCoQuan) {
        this.fileVanBanCuaCoQuan = fileVanBanCuaCoQuan;
    }
}