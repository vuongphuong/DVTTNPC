package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/2/2017.
 */

public class CityEntityRelease implements Parcelable {
    @SerializedName("khuvuc")
    public String khuVuc;
    @SerializedName("madvdiachinh")
    public String madvDiaChinh;
    @SerializedName("tendvidiachinh")
    public String tenDviDiaChinh;

    protected CityEntityRelease(Parcel in) {
        khuVuc = in.readString();
        madvDiaChinh = in.readString();
        tenDviDiaChinh = in.readString();
    }

    public static final Creator<CityEntityRelease> CREATOR = new Creator<CityEntityRelease>() {
        @Override
        public CityEntityRelease createFromParcel(Parcel in) {
            return new CityEntityRelease(in);
        }

        @Override
        public CityEntityRelease[] newArray(int size) {
            return new CityEntityRelease[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(khuVuc);
        parcel.writeString(madvDiaChinh);
        parcel.writeString(tenDviDiaChinh);
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public String getMadvDiaChinh() {
        return madvDiaChinh;
    }

    public void setMadvDiaChinh(String madvDiaChinh) {
        this.madvDiaChinh = madvDiaChinh;
    }

    public String getTenDviDiaChinh() {
        return tenDviDiaChinh;
    }

    public void setTenDviDiaChinh(String tenDviDiaChinh) {
        this.tenDviDiaChinh = tenDviDiaChinh;
    }

    @Override
    public String toString() {
        return tenDviDiaChinh;
    }
}
