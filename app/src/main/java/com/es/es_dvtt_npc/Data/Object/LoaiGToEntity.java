package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/6/2017.
 */

public class LoaiGToEntity implements Parcelable {
    @SerializedName("FileName")
    String FileName;

    @SerializedName("FileExt")
    String FileExt;
    @SerializedName("MA_LOAI_GTO")
    String MA_LOAI_GTO;

    public LoaiGToEntity(){}

    protected LoaiGToEntity(Parcel in) {
        FileName = in.readString();
        FileExt = in.readString();
        MA_LOAI_GTO = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FileName);
        dest.writeString(FileExt);
        dest.writeString(MA_LOAI_GTO);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileExt() {
        return FileExt;
    }

    public void setFileExt(String fileExt) {
        FileExt = fileExt;
    }

    public String getMA_LOAI_GTO() {
        return MA_LOAI_GTO;
    }

    public void setMA_LOAI_GTO(String MA_LOAI_GTO) {
        this.MA_LOAI_GTO = MA_LOAI_GTO;
    }

    public static final Creator<LoaiGToEntity> CREATOR = new Creator<LoaiGToEntity>() {
        @Override
        public LoaiGToEntity createFromParcel(Parcel in) {
            return new LoaiGToEntity(in);
        }

        @Override
        public LoaiGToEntity[] newArray(int size) {
            return new LoaiGToEntity[size];
        }
    };
}
