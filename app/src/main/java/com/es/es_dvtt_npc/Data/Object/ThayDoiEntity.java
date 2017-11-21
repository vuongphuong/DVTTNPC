package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PhuongVV on 11/17/2017.
 */

public class ThayDoiEntity implements Parcelable {

    @SerializedName("MA_KHANG")
    private String MA_KHANG;

    @SerializedName("TEN_NGUOIYCAU")
    private String TEN_NGUOIYCAU;

    @SerializedName("DTHOAI")
    private String DTHOAI;

    @SerializedName("EMAIL")
    private String EMAIL;

    @SerializedName("CONG_SUAT_MOI")
    private int CONG_SUAT_MOI;

    @SerializedName("CONG_SUAT_CU")
    private int CONG_SUAT_CU;

    @SerializedName("LstUploadFiles")
    private List<LoaiGToEntity> LstUploadFiles;

    @SerializedName("MA_LOAI_YCAU_MOI")
    private String MA_LOAI_YCAU_MOI;

    @SerializedName("NOI_DUNG_YEU_CAU")
    private String NOI_DUNG_YEU_CAU;

    @SerializedName("MA_DVIDCHINH")
    private String MA_DVIDCHINH;

    @SerializedName("MA_DVIQLY")
    private String MA_DVIQLY;

    @SerializedName("DCHI_NGUOIYCAU")
    private String DCHI_NGUOIYCAU;

    @SerializedName("NGUON_TNHAN")
    private String NGUON_TNHAN;



    public ThayDoiEntity() {
    }


    protected ThayDoiEntity(Parcel in) {
        MA_KHANG = in.readString();
        TEN_NGUOIYCAU = in.readString();
        DTHOAI = in.readString();
        EMAIL = in.readString();
        CONG_SUAT_MOI = in.readInt();
        CONG_SUAT_CU = in.readInt();
        LstUploadFiles = in.createTypedArrayList(LoaiGToEntity.CREATOR);
        MA_LOAI_YCAU_MOI = in.readString();
        NOI_DUNG_YEU_CAU = in.readString();
        MA_DVIDCHINH = in.readString();
        MA_DVIQLY = in.readString();
        DCHI_NGUOIYCAU = in.readString();
        NGUON_TNHAN = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MA_KHANG);
        dest.writeString(TEN_NGUOIYCAU);
        dest.writeString(DTHOAI);
        dest.writeString(EMAIL);
        dest.writeInt(CONG_SUAT_MOI);
        dest.writeInt(CONG_SUAT_CU);
        dest.writeTypedList(LstUploadFiles);
        dest.writeString(MA_LOAI_YCAU_MOI);
        dest.writeString(NOI_DUNG_YEU_CAU);
        dest.writeString(MA_DVIDCHINH);
        dest.writeString(MA_DVIQLY);
        dest.writeString(DCHI_NGUOIYCAU);
        dest.writeString(NGUON_TNHAN);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ThayDoiEntity> CREATOR = new Creator<ThayDoiEntity>() {
        @Override
        public ThayDoiEntity createFromParcel(Parcel in) {
            return new ThayDoiEntity(in);
        }

        @Override
        public ThayDoiEntity[] newArray(int size) {
            return new ThayDoiEntity[size];
        }
    };

    public String getMA_KHANG() {
        return MA_KHANG;
    }

    public void setMA_KHANG(String MA_KHANG) {
        this.MA_KHANG = MA_KHANG;
    }

    public String getTEN_NGUOIYCAU() {
        return TEN_NGUOIYCAU;
    }

    public void setTEN_NGUOIYCAU(String TEN_NGUOIYCAU) {
        this.TEN_NGUOIYCAU = TEN_NGUOIYCAU;
    }

    public String getDTHOAI() {
        return DTHOAI;
    }

    public void setDTHOAI(String DTHOAI) {
        this.DTHOAI = DTHOAI;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getCONG_SUAT_MOI() {
        return CONG_SUAT_MOI;
    }

    public void setCONG_SUAT_MOI(int CONG_SUAT_MOI) {
        this.CONG_SUAT_MOI = CONG_SUAT_MOI;
    }

    public int getCONG_SUAT_CU() {
        return CONG_SUAT_CU;
    }

    public void setCONG_SUAT_CU(int CONG_SUAT_CU) {
        this.CONG_SUAT_CU = CONG_SUAT_CU;
    }

    public List<LoaiGToEntity> getLstUploadFiles() {
        return LstUploadFiles;
    }

    public void setLstUploadFiles(List<LoaiGToEntity> lstUploadFiles) {
        LstUploadFiles = lstUploadFiles;
    }

    public String getMA_LOAI_YCAU_MOI() {
        return MA_LOAI_YCAU_MOI;
    }

    public void setMA_LOAI_YCAU_MOI(String MA_LOAI_YCAU_MOI) {
        this.MA_LOAI_YCAU_MOI = MA_LOAI_YCAU_MOI;
    }

    public String getNOI_DUNG_YEU_CAU() {
        return NOI_DUNG_YEU_CAU;
    }

    public void setNOI_DUNG_YEU_CAU(String NOI_DUNG_YEU_CAU) {
        this.NOI_DUNG_YEU_CAU = NOI_DUNG_YEU_CAU;
    }

    public String getMA_DVIDCHINH() {
        return MA_DVIDCHINH;
    }

    public void setMA_DVIDCHINH(String MA_DVIDCHINH) {
        this.MA_DVIDCHINH = MA_DVIDCHINH;
    }

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public String getDCHI_NGUOIYCAU() {
        return DCHI_NGUOIYCAU;
    }

    public void setDCHI_NGUOIYCAU(String DCHI_NGUOIYCAU) {
        this.DCHI_NGUOIYCAU = DCHI_NGUOIYCAU;
    }

    public String getNGUON_TNHAN() {
        return NGUON_TNHAN;
    }

    public void setNGUON_TNHAN(String NGUON_TNHAN) {
        this.NGUON_TNHAN = NGUON_TNHAN;
    }
}
