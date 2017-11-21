package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/8/2017.
 */

public class HoDungChungEntity implements Parcelable {
    @SerializedName("TEN_CHUHO")
    String TEN_CHUHO;
    @SerializedName("DIA_CHI")
    String DIA_CHI;
    @SerializedName("SDT_LIENHE")
    String SDT_LIENHE;

    public HoDungChungEntity() {
    }

    protected HoDungChungEntity(Parcel in) {
        TEN_CHUHO = in.readString();
        DIA_CHI = in.readString();
        SDT_LIENHE = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TEN_CHUHO);
        dest.writeString(DIA_CHI);
        dest.writeString(SDT_LIENHE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HoDungChungEntity> CREATOR = new Creator<HoDungChungEntity>() {
        @Override
        public HoDungChungEntity createFromParcel(Parcel in) {
            return new HoDungChungEntity(in);
        }

        @Override
        public HoDungChungEntity[] newArray(int size) {
            return new HoDungChungEntity[size];
        }
    };

    public String getTEN_CHUHO() {
        return TEN_CHUHO;
    }

    public void setTEN_CHUHO(String TEN_CHUHO) {
        this.TEN_CHUHO = TEN_CHUHO;
    }

    public String getDIA_CHI() {
        return DIA_CHI;
    }

    public void setDIA_CHI(String DIA_CHI) {
        this.DIA_CHI = DIA_CHI;
    }

    public String getSDT_LIENHE() {
        return SDT_LIENHE;
    }

    public void setSDT_LIENHE(String SDT_LIENHE) {
        this.SDT_LIENHE = SDT_LIENHE;
    }
}
