package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/17/2017.
 */

public class TTCatDienEntity implements Parcelable{
    @SerializedName("lido")
    public String liDo;

    @SerializedName("ngaydukien")
    public String ngayDuKien;

    @SerializedName("thoigiancat")
    public String thoiGianCat;

    protected TTCatDienEntity(Parcel in) {
        liDo = in.readString();
        ngayDuKien = in.readString();
        thoiGianCat = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(liDo);
        dest.writeString(ngayDuKien);
        dest.writeString(thoiGianCat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TTCatDienEntity> CREATOR = new Creator<TTCatDienEntity>() {
        @Override
        public TTCatDienEntity createFromParcel(Parcel in) {
            return new TTCatDienEntity(in);
        }

        @Override
        public TTCatDienEntity[] newArray(int size) {
            return new TTCatDienEntity[size];
        }
    };

    public String getLiDo() {
        return liDo;
    }

    public void setLiDo(String liDo) {
        this.liDo = liDo;
    }

    public String getNgayDuKien() {
        return ngayDuKien;
    }

    public void setNgayDuKien(String ngayDuKien) {
        this.ngayDuKien = ngayDuKien;
    }

    public String getThoiGianCat() {
        return thoiGianCat;
    }

    public void setThoiGianCat(String thoiGianCat) {
        this.thoiGianCat = thoiGianCat;
    }
}
