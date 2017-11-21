package com.es.es_dvtt_npc.Data.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PhuongVV on 11/17/2017.
 */

public class CustomerInfoEntity  implements Parcelable{
    @SerializedName("ttcatdien")
    public TTCatDienEntity ttCatDien;

    @SerializedName("ttdonvibandien")
    public DonViBanDienEntity donViBanDien;

    @SerializedName("ttkhachhang")
    public TTKhachHangEntity ttKhachHang;

    @SerializedName("ttthanhtoan")
    public List<TTThanhToanEntity> thanhToanList;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;

    @SerializedName("avatar_url")
    public String avatar;

    @SerializedName("sotaikhoan")
    public String soTaiKhoan;

    @SerializedName("tennganhang")
    public String tenNganHang;

    protected CustomerInfoEntity(Parcel in) {
        ttCatDien = in.readParcelable(TTCatDienEntity.class.getClassLoader());
        donViBanDien = in.readParcelable(DonViBanDienEntity.class.getClassLoader());
        ttKhachHang = in.readParcelable(TTKhachHangEntity.class.getClassLoader());
        thanhToanList = in.createTypedArrayList(TTThanhToanEntity.CREATOR);
        email = in.readString();
        phone = in.readString();
        avatar = in.readString();
        soTaiKhoan = in.readString();
        tenNganHang = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(ttCatDien, flags);
        dest.writeParcelable(donViBanDien, flags);
        dest.writeParcelable(ttKhachHang, flags);
        dest.writeTypedList(thanhToanList);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(avatar);
        dest.writeString(soTaiKhoan);
        dest.writeString(tenNganHang);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CustomerInfoEntity> CREATOR = new Creator<CustomerInfoEntity>() {
        @Override
        public CustomerInfoEntity createFromParcel(Parcel in) {
            return new CustomerInfoEntity(in);
        }

        @Override
        public CustomerInfoEntity[] newArray(int size) {
            return new CustomerInfoEntity[size];
        }
    };

    public TTCatDienEntity getTtCatDien() {
        return ttCatDien;
    }

    public void setTtCatDien(TTCatDienEntity ttCatDien) {
        this.ttCatDien = ttCatDien;
    }

    public DonViBanDienEntity getDonViBanDien() {
        return donViBanDien;
    }

    public void setDonViBanDien(DonViBanDienEntity donViBanDien) {
        this.donViBanDien = donViBanDien;
    }

    public TTKhachHangEntity getTtKhachHang() {
        return ttKhachHang;
    }

    public void setTtKhachHang(TTKhachHangEntity ttKhachHang) {
        this.ttKhachHang = ttKhachHang;
    }

    public List<TTThanhToanEntity> getThanhToanList() {
        return thanhToanList;
    }

    public void setThanhToanList(List<TTThanhToanEntity> thanhToanList) {
        this.thanhToanList = thanhToanList;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public String getTenNganHang() {
        return tenNganHang;
    }

    public void setTenNganHang(String tenNganHang) {
        this.tenNganHang = tenNganHang;
    }
}
