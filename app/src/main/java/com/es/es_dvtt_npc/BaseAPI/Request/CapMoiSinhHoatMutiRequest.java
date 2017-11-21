package com.es.es_dvtt_npc.BaseAPI.Request;

import com.es.es_dvtt_npc.BaseAPI.BaseRequest;
import com.es.es_dvtt_npc.Helper.Common;

import java.io.File;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by PhuongVV on 11/13/2017.
 */

public class CapMoiSinhHoatMutiRequest implements BaseRequest{
    private File fileGiayToTuyThan, fileXacDinhChuThe, fileSoHoKhau, fileUyQuyen, fileGiayCamKet, fileThongTinDauNoi;
    private String model;

    @Override
    public String getUrl() {
        return Common.URL +"DV_YCAU_KNAI_WEB/TNhanSHMultiplePart";
    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder()
                .add("Content-Type", "multipart/form-data");
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
        if (fileSoHoKhau != null)
            requestBody.addFormDataPart("fileSoHoKhau",fileSoHoKhau.getName(),RequestBody.create(IMAGE,fileSoHoKhau));
        if (fileUyQuyen != null)
            requestBody.addFormDataPart("fileUyQuyen",fileUyQuyen.getName(),RequestBody.create(IMAGE,fileUyQuyen));
        if (fileGiayCamKet != null)
            requestBody.addFormDataPart("fileGiayCamKet",fileGiayCamKet.getName(),RequestBody.create(IMAGE,fileGiayCamKet));
        if (fileThongTinDauNoi != null)
            requestBody.addFormDataPart("fileThongTinDauNoi",fileThongTinDauNoi.getName(),RequestBody.create(IMAGE,fileThongTinDauNoi));
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

    public void setFileSoHoKhau(File fileSoHoKhau) {
        this.fileSoHoKhau = fileSoHoKhau;
    }

    public void setFileUyQuyen(File fileUyQuyen) {
        this.fileUyQuyen = fileUyQuyen;
    }

    public void setFileGiayCamKet(File fileGiayCamKet) {
        this.fileGiayCamKet = fileGiayCamKet;
    }

    public void setFileThongTinDauNoi(File fileThongTinDauNoi) {
        this.fileThongTinDauNoi = fileThongTinDauNoi;
    }
}
