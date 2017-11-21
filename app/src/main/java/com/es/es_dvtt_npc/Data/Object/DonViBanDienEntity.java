package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/17/2017.
 */

public class DonViBanDienEntity implements Parcelable{
    @SerializedName("dchidienluc")
    public String diaChiDienLuc;

    @SerializedName("tendienluc")
    public String tenDienLuc;

    protected DonViBanDienEntity(Parcel in) {
        diaChiDienLuc = in.readString();
        tenDienLuc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diaChiDienLuc);
        dest.writeString(tenDienLuc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DonViBanDienEntity> CREATOR = new Creator<DonViBanDienEntity>() {
        @Override
        public DonViBanDienEntity createFromParcel(Parcel in) {
            return new DonViBanDienEntity(in);
        }

        @Override
        public DonViBanDienEntity[] newArray(int size) {
            return new DonViBanDienEntity[size];
        }
    };

    public String getDiaChiDienLuc() {
        return diaChiDienLuc;
    }

    public void setDiaChiDienLuc(String diaChiDienLuc) {
        this.diaChiDienLuc = diaChiDienLuc;
    }

    public String getTenDienLuc() {
        return tenDienLuc;
    }

    public void setTenDienLuc(String tenDienLuc) {
        this.tenDienLuc = tenDienLuc;
    }
}
