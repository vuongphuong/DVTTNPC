package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PhuongVV on 11/20/2017.
 */

public class ThayDoiViTriDiemDoEntity implements Parcelable{
    @SerializedName("MA_DVIDCHINH")
    private String MA_DVIDCHINH;

    @SerializedName("DCHI_NGUOIYCAU")
    private String DCHI_NGUOIYCAU;

    @SerializedName("MA_KHANG")
    private String MA_KHANG;

    @SerializedName("TEN_NGUOIYCAU")
    private String TEN_NGUOIYCAU;

    @SerializedName("DTHOAI")
    private String DTHOAI;

    @SerializedName("EMAIL")
    private String EMAIL;

    @SerializedName("MA_LOAI_YCAU_MOI")
    private String MA_LOAI_YCAU_MOI;

    @SerializedName("MA_DVIDCHINH")
    private String NOI_DUNG_YEU_CAU;

    @SerializedName("MA_DVIDCHINH")
    private String MA_DVIQLY;

    @SerializedName("MA_DVIDCHINH")
    private String NGUON_TNHAN;

    @SerializedName("LstUploadFiles")
    private List<LoaiGToEntity> LstUploadFiles;

    protected ThayDoiViTriDiemDoEntity(Parcel in) {
        MA_DVIDCHINH = in.readString();
        DCHI_NGUOIYCAU = in.readString();
        MA_KHANG = in.readString();
        TEN_NGUOIYCAU = in.readString();
        DTHOAI = in.readString();
        EMAIL = in.readString();
        MA_LOAI_YCAU_MOI = in.readString();
        NOI_DUNG_YEU_CAU = in.readString();
        MA_DVIQLY = in.readString();
        NGUON_TNHAN = in.readString();
        LstUploadFiles = in.createTypedArrayList(LoaiGToEntity.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MA_DVIDCHINH);
        dest.writeString(DCHI_NGUOIYCAU);
        dest.writeString(MA_KHANG);
        dest.writeString(TEN_NGUOIYCAU);
        dest.writeString(DTHOAI);
        dest.writeString(EMAIL);
        dest.writeString(MA_LOAI_YCAU_MOI);
        dest.writeString(NOI_DUNG_YEU_CAU);
        dest.writeString(MA_DVIQLY);
        dest.writeString(NGUON_TNHAN);
        dest.writeTypedList(LstUploadFiles);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ThayDoiViTriDiemDoEntity> CREATOR = new Creator<ThayDoiViTriDiemDoEntity>() {
        @Override
        public ThayDoiViTriDiemDoEntity createFromParcel(Parcel in) {
            return new ThayDoiViTriDiemDoEntity(in);
        }

        @Override
        public ThayDoiViTriDiemDoEntity[] newArray(int size) {
            return new ThayDoiViTriDiemDoEntity[size];
        }
    };

    public String getMA_DVIDCHINH() {
        return MA_DVIDCHINH;
    }

    public void setMA_DVIDCHINH(String MA_DVIDCHINH) {
        this.MA_DVIDCHINH = MA_DVIDCHINH;
    }

    public String getDCHI_NGUOIYCAU() {
        return DCHI_NGUOIYCAU;
    }

    public void setDCHI_NGUOIYCAU(String DCHI_NGUOIYCAU) {
        this.DCHI_NGUOIYCAU = DCHI_NGUOIYCAU;
    }

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

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public String getNGUON_TNHAN() {
        return NGUON_TNHAN;
    }

    public void setNGUON_TNHAN(String NGUON_TNHAN) {
        this.NGUON_TNHAN = NGUON_TNHAN;
    }

    public List<LoaiGToEntity> getLstUploadFiles() {
        return LstUploadFiles;
    }

    public void setLstUploadFiles(List<LoaiGToEntity> lstUploadFiles) {
        LstUploadFiles = lstUploadFiles;
    }
}
