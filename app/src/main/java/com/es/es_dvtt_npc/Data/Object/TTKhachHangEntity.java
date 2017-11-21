package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/17/2017.
 */

public class TTKhachHangEntity implements Parcelable{
    @SerializedName("dchikhachhang")
    public String diaChi;

    @SerializedName("makhachhang")
    public String maKhachHang;

    @SerializedName("socongto")
    public String soCongTo;

    @SerializedName("sohopdong")
    public String soHopDong;

    @SerializedName("tenkhachhang")
    public String tenKhachHang;

    protected TTKhachHangEntity(Parcel in) {
        diaChi = in.readString();
        maKhachHang = in.readString();
        soCongTo = in.readString();
        soHopDong = in.readString();
        tenKhachHang = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diaChi);
        dest.writeString(maKhachHang);
        dest.writeString(soCongTo);
        dest.writeString(soHopDong);
        dest.writeString(tenKhachHang);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TTKhachHangEntity> CREATOR = new Creator<TTKhachHangEntity>() {
        @Override
        public TTKhachHangEntity createFromParcel(Parcel in) {
            return new TTKhachHangEntity(in);
        }

        @Override
        public TTKhachHangEntity[] newArray(int size) {
            return new TTKhachHangEntity[size];
        }
    };

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getSoCongTo() {
        return soCongTo;
    }

    public void setSoCongTo(String soCongTo) {
        this.soCongTo = soCongTo;
    }

    public String getSoHopDong() {
        return soHopDong;
    }

    public void setSoHopDong(String soHopDong) {
        this.soHopDong = soHopDong;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
}
