package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by PhuongVV on 11/6/2017.
 */

public class BuyElectricEntity implements Parcelable {
    @SerializedName("TEN_CQUAN")
    String TEN_CQUAN;
    @SerializedName("TEN_DDIEN")
    String TEN_DDIEN;
    @SerializedName("TEN_NGUOIYCAU")
    String TEN_NGUOIYCAU;
    @SerializedName("DTHOAI")
    String DTHOAI;
    @SerializedName("EMAIL")
    String EMAIL;
    @SerializedName("CMT")
    String CMT;
    @SerializedName("NGAY_CAPCMT")
    String NGAY_CAPCMT;
    @SerializedName("NOI_CAPCMT")
    String NOI_CAPCMT;
    @SerializedName("MA_DVIDCHINH")
    String MA_DVIDCHINH;
    @SerializedName("DCHI_NGUOIYCAU")
    String DCHI_NGUOIYCAU;
    @SerializedName("DCHI_DDIEN")
    String DCHI_DDIEN;
    @SerializedName("CONG_SUAT")
    int CONG_SUAT;
    @SerializedName("MASO_THUE")
    String MASO_THUE;
    @SerializedName("TKHOAN_KHANG")
    String TKHOAN_KHANG;
    @SerializedName("MA_NHANG")
    String MA_NHANG;
    @SerializedName("MA_LOAI_YCAU_MOI")
    String MA_LOAI_YCAU_MOI;
    @SerializedName("SO_HO")
    int SO_HO;
    @SerializedName("LOAI_CAPMOI")
    String LOAI_CAPMOI;
    @SerializedName("THOI_GIAN")
    String THOI_GIAN;
    @SerializedName("LstUploadFiles")
    ArrayList<LoaiGToEntity> LstUploadFiles;
    @SerializedName("LstDChung")
    ArrayList<HoDungChungEntity> LstDChung;
    @SerializedName("NGUON_TNHAN")
    String NGUON_TNHAN;

    public BuyElectricEntity() {
    }

    protected BuyElectricEntity(Parcel in) {
        TEN_CQUAN = in.readString();
        TEN_DDIEN = in.readString();
        TEN_NGUOIYCAU = in.readString();
        DTHOAI = in.readString();
        EMAIL = in.readString();
        CMT = in.readString();
        NGAY_CAPCMT = in.readString();
        NOI_CAPCMT = in.readString();
        MA_DVIDCHINH = in.readString();
        DCHI_NGUOIYCAU = in.readString();
        DCHI_DDIEN = in.readString();
        CONG_SUAT = in.readInt();
        MASO_THUE = in.readString();
        TKHOAN_KHANG = in.readString();
        MA_NHANG = in.readString();
        MA_LOAI_YCAU_MOI = in.readString();
        SO_HO = in.readInt();
        LOAI_CAPMOI = in.readString();
        THOI_GIAN = in.readString();
        NGUON_TNHAN = in.readString();
        LstUploadFiles = in.createTypedArrayList(LoaiGToEntity.CREATOR);
        LstDChung = in.createTypedArrayList(HoDungChungEntity.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TEN_CQUAN);
        dest.writeString(TEN_DDIEN);
        dest.writeString(TEN_NGUOIYCAU);
        dest.writeString(DTHOAI);
        dest.writeString(EMAIL);
        dest.writeString(CMT);
        dest.writeString(NGAY_CAPCMT);
        dest.writeString(NOI_CAPCMT);
        dest.writeString(MA_DVIDCHINH);
        dest.writeString(DCHI_NGUOIYCAU);
        dest.writeString(DCHI_DDIEN);
        dest.writeInt(CONG_SUAT);
        dest.writeString(MASO_THUE);
        dest.writeString(TKHOAN_KHANG);
        dest.writeString(MA_NHANG);
        dest.writeString(MA_LOAI_YCAU_MOI);
        dest.writeInt(SO_HO);
        dest.writeString(LOAI_CAPMOI);
        dest.writeString(THOI_GIAN);
        dest.writeString(NGUON_TNHAN);
        dest.writeTypedList(LstUploadFiles);
        dest.writeTypedList(LstDChung);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BuyElectricEntity> CREATOR = new Creator<BuyElectricEntity>() {
        @Override
        public BuyElectricEntity createFromParcel(Parcel in) {
            return new BuyElectricEntity(in);
        }

        @Override
        public BuyElectricEntity[] newArray(int size) {
            return new BuyElectricEntity[size];
        }
    };

    public String getTEN_CQUAN() {
        return TEN_CQUAN;
    }

    public void setTEN_CQUAN(String TEN_CQUAN) {
        this.TEN_CQUAN = TEN_CQUAN;
    }

    public String getTEN_DDIEN() {
        return TEN_DDIEN;
    }

    public void setTEN_DDIEN(String TEN_DDIEN) {
        this.TEN_DDIEN = TEN_DDIEN;
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

    public String getCMT() {
        return CMT;
    }

    public void setCMT(String CMT) {
        this.CMT = CMT;
    }

    public String getNGAY_CAPCMT() {
        return NGAY_CAPCMT;
    }

    public void setNGAY_CAPCMT(String NGAY_CAPCMT) {
        this.NGAY_CAPCMT = NGAY_CAPCMT;
    }

    public String getNOI_CAPCMT() {
        return NOI_CAPCMT;
    }

    public void setNOI_CAPCMT(String NOI_CAPCMT) {
        this.NOI_CAPCMT = NOI_CAPCMT;
    }

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

    public String getDCHI_DDIEN() {
        return DCHI_DDIEN;
    }

    public void setDCHI_DDIEN(String DCHI_DDIEN) {
        this.DCHI_DDIEN = DCHI_DDIEN;
    }

    public int getCONG_SUAT() {
        return CONG_SUAT;
    }

    public void setCONG_SUAT(int CONG_SUAT) {
        this.CONG_SUAT = CONG_SUAT;
    }

    public String getMASO_THUE() {
        return MASO_THUE;
    }

    public void setMASO_THUE(String MASO_THUE) {
        this.MASO_THUE = MASO_THUE;
    }

    public String getTKHOAN_KHANG() {
        return TKHOAN_KHANG;
    }

    public void setTKHOAN_KHANG(String TKHOAN_KHANG) {
        this.TKHOAN_KHANG = TKHOAN_KHANG;
    }

    public String getMA_NHANG() {
        return MA_NHANG;
    }

    public void setMA_NHANG(String MA_NHANG) {
        this.MA_NHANG = MA_NHANG;
    }

    public String getMA_LOAI_YCAU_MOI() {
        return MA_LOAI_YCAU_MOI;
    }

    public void setMA_LOAI_YCAU_MOI(String MA_LOAI_YCAU) {
        this.MA_LOAI_YCAU_MOI = MA_LOAI_YCAU;
    }

    public int getSO_HO() {
        return SO_HO;
    }

    public void setSO_HO(int SO_HO) {
        this.SO_HO = SO_HO;
    }

    public String getLOAI_CAPMOI() {
        return LOAI_CAPMOI;
    }

    public void setLOAI_CAPMOI(String LOAI_CAPMOI) {
        this.LOAI_CAPMOI = LOAI_CAPMOI;
    }

    public String getTHOI_GIAN() {
        return THOI_GIAN;
    }

    public void setTHOI_GIAN(String THOI_GIAN) {
        this.THOI_GIAN = THOI_GIAN;
    }

    public String getNGUON_TNHAN() {
        return NGUON_TNHAN;
    }

    public void setNGUON_TNHAN(String NGUON_TNHAN) {
        this.NGUON_TNHAN = NGUON_TNHAN;
    }

    public ArrayList<LoaiGToEntity> getLstUploadFiles() {
        return LstUploadFiles;
    }

    public void setLstUploadFiles(ArrayList<LoaiGToEntity> lstUploadFiles) {
        LstUploadFiles = lstUploadFiles;
    }

    public ArrayList<HoDungChungEntity> getLstDChung() {
        return LstDChung;
    }

    public void setLstDChung(ArrayList<HoDungChungEntity> lstDChung) {
        LstDChung = lstDChung;
    }
}
