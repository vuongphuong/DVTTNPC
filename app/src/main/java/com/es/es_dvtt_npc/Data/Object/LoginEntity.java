package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhuongVV on 11/17/2017.
 */

public class LoginEntity implements Parcelable{
    @SerializedName("makh")
    private String customerId;

    @SerializedName("loaikh")
    private String loaiHopDong;

    @SerializedName("dichikh")
    private String address;

    @SerializedName("mahdong")
    private String contractId;

    @SerializedName("tenkh")
    private String tenKhachHang;

    @SerializedName("madienluchuyen")
    public String maDLHuyen;

    @SerializedName("madienluctinh")
    private String maDLTinh;

    public String email;

    @SerializedName("sodt")
    private String phone;

    protected LoginEntity(Parcel in) {
        customerId = in.readString();
        loaiHopDong = in.readString();
        address = in.readString();
        contractId = in.readString();
        tenKhachHang = in.readString();
        maDLHuyen = in.readString();
        maDLTinh = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerId);
        dest.writeString(loaiHopDong);
        dest.writeString(address);
        dest.writeString(contractId);
        dest.writeString(tenKhachHang);
        dest.writeString(maDLHuyen);
        dest.writeString(maDLTinh);
        dest.writeString(email);
        dest.writeString(phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginEntity> CREATOR = new Creator<LoginEntity>() {
        @Override
        public LoginEntity createFromParcel(Parcel in) {
            return new LoginEntity(in);
        }

        @Override
        public LoginEntity[] newArray(int size) {
            return new LoginEntity[size];
        }
    };

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getLoaiHopDong() {
        return loaiHopDong;
    }

    public void setLoaiHopDong(String loaiHopDong) {
        this.loaiHopDong = loaiHopDong;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaDLHuyen() {
        return maDLHuyen;
    }

    public void setMaDLHuyen(String maDLHuyen) {
        this.maDLHuyen = maDLHuyen;
    }

    public String getMaDLTinh() {
        return maDLTinh;
    }

    public void setMaDLTinh(String maDLTinh) {
        this.maDLTinh = maDLTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
