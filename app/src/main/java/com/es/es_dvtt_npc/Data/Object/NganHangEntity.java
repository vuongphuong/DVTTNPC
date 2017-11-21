package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/15/2017.
 */

public class NganHangEntity implements Parcelable {
    @SerializedName("MA_NHANG")
    String MA_NHANG;
    @SerializedName("TEN_NHANG")
    String TEN_NHANG;

    protected NganHangEntity(Parcel in) {
        MA_NHANG = in.readString();
        TEN_NHANG = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MA_NHANG);
        dest.writeString(TEN_NHANG);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NganHangEntity> CREATOR = new Creator<NganHangEntity>() {
        @Override
        public NganHangEntity createFromParcel(Parcel in) {
            return new NganHangEntity(in);
        }

        @Override
        public NganHangEntity[] newArray(int size) {
            return new NganHangEntity[size];
        }
    };

    public String getMA_NHANG() {
        return MA_NHANG;
    }

    public void setMA_NHANG(String MA_NHANG) {
        this.MA_NHANG = MA_NHANG;
    }

    public String getTEN_NHANG() {
        return TEN_NHANG;
    }

    public void setTEN_NHANG(String TEN_NHANG) {
        this.TEN_NHANG = TEN_NHANG;
    }

    @Override
    public String toString() {
        return TEN_NHANG;
    }
}
