package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/17/2017.
 */

public class TTThanhToanEntity implements Parcelable{
    @SerializedName("chuoigia")
    public String chuoiGia;

    @SerializedName("dientieuthu")
    public String dienTieuThu;

    public String ky;

    @SerializedName("lichghichiso")
    public String lichGhiChiSo;

    public String nam;

    @SerializedName("sotien")
    public String soTien;

    @SerializedName("thang")
    public String thang;

    @SerializedName("tinhtrang")
    public String tinhTrang;

    @SerializedName("title")
    public String title;

    @SerializedName("trangthai")
    public String trangThai;

    protected TTThanhToanEntity(Parcel in) {
        chuoiGia = in.readString();
        dienTieuThu = in.readString();
        ky = in.readString();
        lichGhiChiSo = in.readString();
        nam = in.readString();
        soTien = in.readString();
        thang = in.readString();
        tinhTrang = in.readString();
        title = in.readString();
        trangThai = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chuoiGia);
        dest.writeString(dienTieuThu);
        dest.writeString(ky);
        dest.writeString(lichGhiChiSo);
        dest.writeString(nam);
        dest.writeString(soTien);
        dest.writeString(thang);
        dest.writeString(tinhTrang);
        dest.writeString(title);
        dest.writeString(trangThai);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TTThanhToanEntity> CREATOR = new Creator<TTThanhToanEntity>() {
        @Override
        public TTThanhToanEntity createFromParcel(Parcel in) {
            return new TTThanhToanEntity(in);
        }

        @Override
        public TTThanhToanEntity[] newArray(int size) {
            return new TTThanhToanEntity[size];
        }
    };

    public String getChuoiGia() {
        return chuoiGia;
    }

    public void setChuoiGia(String chuoiGia) {
        this.chuoiGia = chuoiGia;
    }

    public String getDienTieuThu() {
        return dienTieuThu;
    }

    public void setDienTieuThu(String dienTieuThu) {
        this.dienTieuThu = dienTieuThu;
    }

    public String getKy() {
        return ky;
    }

    public void setKy(String ky) {
        this.ky = ky;
    }

    public String getLichGhiChiSo() {
        return lichGhiChiSo;
    }

    public void setLichGhiChiSo(String lichGhiChiSo) {
        this.lichGhiChiSo = lichGhiChiSo;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
