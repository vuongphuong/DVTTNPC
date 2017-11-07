package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/2/2017.
 */

public class CityEntity implements Parcelable {
    @SerializedName("ID_DIA_CHINH")
    private String ID_DIA_CHINH;
    @SerializedName("MA_DVIDCHINH")
    private String MA_DVIDCHINH;
    @SerializedName("TEN_DVIDCHINH")
    private String TEN_DVIDCHINH;
    @SerializedName("KHU_VUC")
    private String KHU_VUC;
    @SerializedName("MA_DVICTREN")
    private String MA_DVICTREN;
    @SerializedName("CAP_DVI")
    private String CAP_DVI;
    @SerializedName("NGAY_HLUC")
    private String NGAY_HLUC;
    @SerializedName("TRANG_THAI")
    private String TRANG_THAI;

    protected CityEntity(Parcel in) {
        ID_DIA_CHINH = in.readString();
        MA_DVIDCHINH = in.readString();
        TEN_DVIDCHINH = in.readString();
        KHU_VUC = in.readString();
        MA_DVICTREN = in.readString();
        CAP_DVI = in.readString();
        NGAY_HLUC = in.readString();
        TRANG_THAI = in.readString();
    }

    public static final Creator<CityEntity> CREATOR = new Creator<CityEntity>() {
        @Override
        public CityEntity createFromParcel(Parcel in) {
            return new CityEntity(in);
        }

        @Override
        public CityEntity[] newArray(int size) {
            return new CityEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getID_DIA_CHINH() {
        return ID_DIA_CHINH;
    }

    public void setID_DIA_CHINH(String ID_DIA_CHINH) {
        this.ID_DIA_CHINH = ID_DIA_CHINH;
    }

    public String getMA_DVIDCHINH() {
        return MA_DVIDCHINH;
    }

    public void setMA_DVIDCHINH(String MA_DVIDCHINH) {
        this.MA_DVIDCHINH = MA_DVIDCHINH;
    }

    public String getTEN_DVIDCHINH() {
        return TEN_DVIDCHINH;
    }

    public void setTEN_DVIDCHINH(String TEN_DVIDCHINH) {
        this.TEN_DVIDCHINH = TEN_DVIDCHINH;
    }

    public String getKHU_VUC() {
        return KHU_VUC;
    }

    public void setKHU_VUC(String KHU_VUC) {
        this.KHU_VUC = KHU_VUC;
    }

    public String getMA_DVICTREN() {
        return MA_DVICTREN;
    }

    public void setMA_DVICTREN(String MA_DVICTREN) {
        this.MA_DVICTREN = MA_DVICTREN;
    }

    public String getCAP_DVI() {
        return CAP_DVI;
    }

    public void setCAP_DVI(String CAP_DVI) {
        this.CAP_DVI = CAP_DVI;
    }

    public String getNGAY_HLUC() {
        return NGAY_HLUC;
    }

    public void setNGAY_HLUC(String NGAY_HLUC) {
        this.NGAY_HLUC = NGAY_HLUC;
    }

    public String getTRANG_THAI() {
        return TRANG_THAI;
    }

    public void setTRANG_THAI(String TRANG_THAI) {
        this.TRANG_THAI = TRANG_THAI;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID_DIA_CHINH);
        parcel.writeString(MA_DVIDCHINH);
        parcel.writeString(TEN_DVIDCHINH);
        parcel.writeString(KHU_VUC);
        parcel.writeString(MA_DVICTREN);
        parcel.writeString(CAP_DVI);
        parcel.writeString(NGAY_HLUC);
        parcel.writeString(TRANG_THAI);
    }

    @Override
    public String toString() {
        return  TEN_DVIDCHINH;
    }
}
