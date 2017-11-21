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

public class CapMoiDienNgoaiSinhHoatRequest implements BaseRequest {
    File fileGiayToTuyThan, fileXacDinhChuThe, fileXacDinhMucDich, fileDangKyPhuTai, fileGiayBaoLanh, fileThongTinDauNoi, fileGiayPhepHoatDong;
    private String model;

    @Override
    public String getUrl() {
        return Common.URL +"DV_YCAU_KNAI_WEB/" + "TNhanNSHMultiplePart";
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
        if (fileGiayToTuyThan != null)
            requestBody.addFormDataPart("fileGiayToTuyThan",fileGiayToTuyThan.getName(),RequestBody.create(IMAGE,fileGiayToTuyThan));
        if (fileXacDinhChuThe != null)
            requestBody.addFormDataPart("fileXacDinhChuThe",fileXacDinhChuThe.getName(),RequestBody.create(IMAGE,fileXacDinhChuThe));
        if (fileXacDinhMucDich != null)
            requestBody.addFormDataPart("fileXacDinhMucDich",fileXacDinhMucDich.getName(),RequestBody.create(IMAGE,fileXacDinhMucDich));
        if (fileDangKyPhuTai != null)
            requestBody.addFormDataPart("fileDangKyPhuTai",fileDangKyPhuTai.getName(),RequestBody.create(IMAGE,fileDangKyPhuTai));
        if (fileGiayBaoLanh != null)
            requestBody.addFormDataPart("fileGiayBaoLanh",fileGiayBaoLanh.getName(),RequestBody.create(IMAGE,fileGiayBaoLanh));
        if (fileThongTinDauNoi != null)
            requestBody.addFormDataPart("fileThongTinDauNoi",fileThongTinDauNoi.getName(),RequestBody.create(IMAGE,fileThongTinDauNoi));
        if (fileGiayPhepHoatDong != null)
            requestBody.addFormDataPart("fileGiayPhepHoatDong",fileGiayPhepHoatDong.getName(),RequestBody.create(IMAGE,fileGiayPhepHoatDong));
        return requestBody.build();
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFileGiayToTuyThan(File fileGiayToTuyThan) {
        this.fileGiayToTuyThan = fileGiayToTuyThan;
    }

    public void setFileXacDinhChuThe(File fileXacDinhChuThe) {
        this.fileXacDinhChuThe = fileXacDinhChuThe;
    }

    public void setFileXacDinhMucDich(File fileXacDinhMucDich) {
        this.fileXacDinhMucDich = fileXacDinhMucDich;
    }

    public void setFileDangKyPhuTai(File fileDangKyPhuTai) {
        this.fileDangKyPhuTai = fileDangKyPhuTai;
    }

    public void setFileGiayBaoLanh(File fileGiayBaoLanh) {
        this.fileGiayBaoLanh = fileGiayBaoLanh;
    }

    public void setFileThongTinDauNoi(File fileThongTinDauNoi) {
        this.fileThongTinDauNoi = fileThongTinDauNoi;
    }

    public void setFileGiayPhepHoatDong(File fileGiayPhepHoatDong) {
        this.fileGiayPhepHoatDong = fileGiayPhepHoatDong;
    }
}