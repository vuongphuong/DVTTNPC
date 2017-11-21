package com.es.es_dvtt_npc.Interface.ElectricitySupply;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.CapMoiSinhHoatMultiReponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.DVDChinhResponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.GetNganHangResponse;
import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiSinhHoatMutiRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.DVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetChildDVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetNganHangRequest;
import com.es.es_dvtt_npc.BaseAPI.ResponseListener;
import com.es.es_dvtt_npc.BaseAPI.errors.AuthFailureError;
import com.es.es_dvtt_npc.BaseAPI.errors.ParserError;
import com.es.es_dvtt_npc.BaseAPI.errors.ServerError;
import com.es.es_dvtt_npc.Data.Object.BuyElectricEntity;
import com.es.es_dvtt_npc.Data.Object.CityEntity;
import com.es.es_dvtt_npc.Data.Object.HoDungChungEntity;
import com.es.es_dvtt_npc.Data.Object.LoaiGToEntity;
import com.es.es_dvtt_npc.Data.Object.NganHangEntity;
import com.es.es_dvtt_npc.Helper.AppAlertDialog;
import com.es.es_dvtt_npc.Helper.AppLog;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.DateHelper;
import com.es.es_dvtt_npc.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Response;

import static com.es.es_dvtt_npc.Helper.Common.LOAI_GIAY_TO.GIAY_TO_TUY_THAN;

/**
 * Created by PhuongVV on 11/1/2017.
 */

public class ElectricitySupplyFragment extends BaseFragment implements ResponseListener, View.OnClickListener,ActionbarInfo {
    private static final String MA_CAP_DIEN = "ma_cap_dien";
    private int maCapDien;
    private String loaiGiayTo;
    private EditText etName, etPhone, etEmail, etCMT, etNgayCap, etNoiCap,
            etNoiThuongTru, etDiaChiSuDung, etSoHoDungChung, etCongSuatDangKy, etSoTaiKhoan;
    private Spinner spTinh, spHuyen, spXa, spNganHang;
    private ArrayList<CityEntity> dsTinh;
    private ArrayList<CityEntity> dsQuan;
    private ArrayList<CityEntity> dsXa;
    private ArrayList<NganHangEntity> nganHangEntities;
    private ArrayList<HoDungChungEntity> hoDungChungEntities;
    private Button btnSend, btnAdd, btnInfo;
    private LinearLayout llGiayToTuyThan, llXacDinhChuThe, llUyQuyen, llSoHoKhau, llGiayCamKet, llThongTinDauNoi, llTitleSoHo;
    File fileGiayToTuyThan, fileXacDinhChuThe, fileSoHoKhau, fileUyQuyen, fileGiayCamKet, fileThongTinDauNoi;
    private String LOAI_CAPMOI, MA_CAP_DIEN_MOI, MA_DV_HANH_CHINH, MA_NHANG;
    ArrayAdapter<CityEntity> adapterDviTinh, adapterDviHuyen, adapterDviXa;
    private Dialog dlAddHo, dlInfoHo;
    ArrayList<LoaiGToEntity> loaiGToEntities;


    public static ElectricitySupplyFragment newInstance(int maCapDien) {
        Bundle args = new Bundle();
        args.putInt(MA_CAP_DIEN, maCapDien);
        ElectricitySupplyFragment fragment = new ElectricitySupplyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            maCapDien = savedInstanceState.getInt(MA_CAP_DIEN);
        } else if (getArguments() != null) {
            maCapDien = getArguments().getInt(MA_CAP_DIEN);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MA_CAP_DIEN, maCapDien);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.electricity_supply_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dsTinh = new ArrayList<>();
        dsQuan = new ArrayList<>();
        dsXa = new ArrayList<>();
        hoDungChungEntities = new ArrayList<>();
        loaiGToEntities = new ArrayList<>();
        nganHangEntities = new ArrayList<>();
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View v) {
        etName = v.findViewById(R.id.f_electricity_supply_tvNameCustomer);
        etPhone = v.findViewById(R.id.f_electricity_supply_tvPhone);
        etEmail = v.findViewById(R.id.f_electricity_supply_tvEmail);
        etCMT = v.findViewById(R.id.f_electricity_supply_tvPeopleID);
        etNgayCap = v.findViewById(R.id.f_electricity_supply_tvDateRange);
        etNoiCap = v.findViewById(R.id.f_electricity_supply_tvNoiCap);
        etNoiThuongTru = v.findViewById(R.id.f_electricity_supply_tvNoiThuongTru);
        etDiaChiSuDung = v.findViewById(R.id.f_electricity_supply_tvDiaChiSuDung);
        etCongSuatDangKy = v.findViewById(R.id.f_electricity_supply_tvCongSuatDangKy);
        etSoHoDungChung = v.findViewById(R.id.f_electricity_supply_tvSoHoDungChung);
        etSoTaiKhoan = v.findViewById(R.id.f_electricity_supply_tvTaiKhoan);
        spTinh = v.findViewById(R.id.f_electricity_supply_spTinh);
        spHuyen = v.findViewById(R.id.f_electricity_supply_spHuyen);
        spXa = v.findViewById(R.id.f_electricity_supply_spXa);
        spNganHang = v.findViewById(R.id.f_electricity_supply_spNganHang);
        btnSend = v.findViewById(R.id.f_electricity_supply_btnSend);
        btnAdd = v.findViewById(R.id.f_electricity_supply_btnAdd);
        btnInfo = v.findViewById(R.id.f_electricity_supply_btnInfo);
        llGiayToTuyThan = v.findViewById(R.id.f_electricity_supply_btnGiayToTuyThan);
        llXacDinhChuThe = v.findViewById(R.id.f_electricity_supply_btnXacDinhChuThe);
        llUyQuyen = v.findViewById(R.id.f_electricity_supply_btnGiayUyQuyen);
        llSoHoKhau = v.findViewById(R.id.f_electricity_supply_btnSoHoKhau);
        llGiayCamKet = v.findViewById(R.id.f_electricity_supply_btnGiayCamKet);
        llThongTinDauNoi = v.findViewById(R.id.f_electricity_supply_btnThongTinDauNoi);
        llTitleSoHo = v.findViewById(R.id.f_electricity_supply_llTitleSoHo);
        setView();
//        setText();
        DVDChinhRequest dvdChinhRequest = new DVDChinhRequest();
        mApi.getDVDChinh(Common.REQUEST_DVDCHINH, dvdChinhRequest, this);
        GetNganHangRequest nganHangRequest = new GetNganHangRequest();
        mApi.getNganHang(Common.REQUEST_GET_NGAN_HANG, nganHangRequest, this);

        btnSend.setOnClickListener(this);
        llGiayToTuyThan.setOnClickListener(this);
        llXacDinhChuThe.setOnClickListener(this);
        llUyQuyen.setOnClickListener(this);
        llSoHoKhau.setOnClickListener(this);
        llGiayCamKet.setOnClickListener(this);
        llThongTinDauNoi.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        etNgayCap.setOnClickListener(this);

        spTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GetChildDVDChinhRequest dvdChinhRequest = new GetChildDVDChinhRequest();
                dvdChinhRequest.setMaDviCtren(dsTinh.get(i).getMA_DVIDCHINH());
                mApi.getChildDVDChinh(Common.REQUEST_GET_CHILD_DVDCHINH_HUYEN, dvdChinhRequest, ElectricitySupplyFragment.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GetChildDVDChinhRequest dvdChinhRequest = new GetChildDVDChinhRequest();
                dvdChinhRequest.setMaDviCtren(dsQuan.get(i).getMA_DVIDCHINH());
                mApi.getChildDVDChinh(Common.REQUEST_GET_CHILD_DVDCHINH_XA, dvdChinhRequest, ElectricitySupplyFragment.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spXa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MA_DV_HANH_CHINH = dsXa.get(i).getMA_DVIDCHINH();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spNganHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MA_NHANG = nganHangEntities.get(i).getMA_NHANG();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    //endregion

    //region Navigation
    @Override
    public String getActionbarTitle() {
        switch (maCapDien) {
            case Common.CAP_MOI_SINH_HOAT:
                return getString(R.string.capmoisinhhoat);
            case Common.CAP_MOI_DUNG_CHUNG:
               return getString(R.string.dungchungcongto);
            case Common.TACH_CONG_TO:
                return getString(R.string.tachcongto);
            case Common.CAP_MOI_SINH_HOAT_TRUNG_AP:
                return getString(R.string.capmoisinhhoattrungap);
        }
        return null;
    }

//endregion

    //region Control Action

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.f_electricity_supply_btnSend:
                if (TextUtils.isEmpty(etName.getText().toString()))
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập tên người yêu cầu", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etPhone.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập số điện thoại liên hệ", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etCMT.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập số chứng minh thư hoặc thẻ căn cước", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etNgayCap.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập ngày cấp chứng minh thư hoặc thẻ căn cước", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etNoiCap.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập nơi cấp chứng minh thư hoặc thẻ căn cước", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etNoiThuongTru.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập địa chỉ giao dịch", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etDiaChiSuDung.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập địa chỉ sử dụng điện", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (MA_DV_HANH_CHINH == null)
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng chọn đơn vị hành chính", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (maCapDien == Common.CAP_MOI_DUNG_CHUNG) {
                    if (Integer.valueOf(etSoHoDungChung.getText().toString()) != hoDungChungEntities.size()) {
                        AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng kiểm tra thông tin số hộ dùng chung để khớp với số hộ đã đăng ký!",
                                Color.WHITE, getString(R.string.common_ok), Color.RED);
                    } else {
                        setRequest();
                    }
                } else {
                    setRequest();
                }
                break;
            case R.id.f_electricity_supply_btnGiayToTuyThan:
                loaiGiayTo = GIAY_TO_TUY_THAN.toString();
                showAvatarChooserDialog();
                break;
            case R.id.f_electricity_supply_btnXacDinhChuThe:
                loaiGiayTo = Common.LOAI_GIAY_TO.XAC_DINH_CHU_THE_HĐ.toString();
                showAvatarChooserDialog();
                break;
            case R.id.f_electricity_supply_btnSoHoKhau:
                loaiGiayTo = Common.LOAI_GIAY_TO.KHAC.toString();
                showAvatarChooserDialog();
                break;
            case R.id.f_electricity_supply_btnGiayUyQuyen:
                loaiGiayTo = Common.LOAI_GIAY_TO.GIAY_UY_QUYEN.toString();
                showAvatarChooserDialog();
                break;
            case R.id.f_electricity_supply_btnGiayCamKet:
                loaiGiayTo = Common.LOAI_GIAY_TO.GIAY_CAM_KET_THANH_TOAN.toString();
                showAvatarChooserDialog();
                break;
            case R.id.f_electricity_supply_btnThongTinDauNoi:
                loaiGiayTo = Common.LOAI_GIAY_TO.DANG_KY_DAU_NOI.toString();
                showAvatarChooserDialog();
                break;
            case R.id.f_electricity_supply_tvDateRange:
                DateHelper.dateDialogPicker(getContext(), etNgayCap);
                break;
            case R.id.f_electricity_supply_btnAdd:
                showAddHoDungChung();
                break;
            case R.id.f_electricity_supply_btnInfo:
                AppAlertDialog.showInfoHoDungChung(getContext(), dlInfoHo, hoDungChungEntities);
                break;
        }
    }


//endregion

    //region Control Delegate

//endregion

    //region API
    @Override
    public BaseResponse parse(int requestId, Call call, Response response) throws Exception {
        switch (requestId) {
            case Common.REQUEST_DVDCHINH:
                return new DVDChinhResponse(response);
            case Common.REQUEST_GET_CHILD_DVDCHINH_HUYEN:
                return new DVDChinhResponse(response);
            case Common.REQUEST_GET_CHILD_DVDCHINH_XA:
                return new DVDChinhResponse(response);
            case Common.REQUEST_GET_NGAN_HANG:
                return new GetNganHangResponse(response);
            default:
                return new CapMoiSinhHoatMultiReponse(response);
        }
    }

    @Override
    public void onResponse(int requestId, BaseResponse response) {
        switch (requestId) {
            case Common.REQUEST_DVDCHINH:
                DVDChinhResponse dvdChinhResponse = (DVDChinhResponse) response;
                dsTinh = dvdChinhResponse.getCityEntities();
                if (dsTinh != null) {
                    adapterDviTinh = new ArrayAdapter<>(getContext(), R.layout.simple_item_list, dsTinh);
                    spTinh.setAdapter(adapterDviTinh);
                } else
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, dvdChinhResponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                break;
            case Common.REQUEST_GET_CHILD_DVDCHINH_HUYEN:
                DVDChinhResponse dvdChinhResponseHuyen = (DVDChinhResponse) response;
                dsQuan = dvdChinhResponseHuyen.getCityEntities();
                if (dsQuan != null) {
                    adapterDviHuyen = new ArrayAdapter<>(getContext(), R.layout.simple_item_list, dsQuan);
                    adapterDviHuyen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spHuyen.setAdapter(adapterDviHuyen);
                } else
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, dvdChinhResponseHuyen.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                break;
            case Common.REQUEST_GET_CHILD_DVDCHINH_XA:
                DVDChinhResponse dvdChinhResponseXa = (DVDChinhResponse) response;
                dsXa = dvdChinhResponseXa.getCityEntities();
                if (dsQuan != null) {
                    ArrayAdapter<CityEntity> adapterDvi = new ArrayAdapter<>(getContext(), R.layout.simple_item_list, dsXa);
                    adapterDvi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spXa.setAdapter(adapterDvi);
                } else
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, dvdChinhResponseXa.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                break;
            case Common.REQUEST_POST_CAP_MOI_DIEN_SINH_HOAT:
                fileGiayCamKet = null;
                fileGiayToTuyThan = null;
                fileSoHoKhau = null;
                fileThongTinDauNoi = null;
                fileUyQuyen = null;
                fileXacDinhChuThe = null;
                dismissLoadingDialog();
                CapMoiSinhHoatMultiReponse capMoiSinhHoatMultiReponse = (CapMoiSinhHoatMultiReponse) response;
                if (capMoiSinhHoatMultiReponse.getResult().equals(Common.TRUE)) {
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Thành công", Color.WHITE, capMoiSinhHoatMultiReponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                } else {
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, capMoiSinhHoatMultiReponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                }
                break;
            case Common.REQUEST_GET_NGAN_HANG:
                GetNganHangResponse getNganHangResponse = (GetNganHangResponse) response;
                nganHangEntities = getNganHangResponse.getNganHangEntities();
                if (nganHangEntities != null) {
                    ArrayAdapter<NganHangEntity> adapterNganHang = new ArrayAdapter<>(getContext(), R.layout.simple_item_list, nganHangEntities);
                    adapterNganHang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spNganHang.setAdapter(adapterNganHang);
                } else
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, getNganHangResponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);

                break;
        }
    }

    @Override
    public void onError(int requestId, Exception e) {
        dismissLoadingDialog();
        if (e instanceof ServerError) {
            ServerError serverError = (ServerError) e;
            AppLog.e(getString(R.string.app_name), serverError.getError().getErrorDescription());
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Lỗi thao tác trên service", Color.WHITE, getString(R.string.common_ok), Color.RED);
        } else if (e instanceof ParserError) {
            AppLog.e(getString(R.string.app_name), "parser data error request: " + requestId);
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Không thể đọc dữ liệu", Color.WHITE, getString(R.string.common_ok), Color.RED);
        } else if (e instanceof AuthFailureError) {
            AppLog.e(getString(R.string.app_name), "AuthFailure error request: " + requestId);
        } else {
            AppLog.e(getString(R.string.app_name), "unKnown error request: " + requestId);
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, e.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
        }
    }

//endregion

    //region Helper
    public void setText() {
        etName.setText("Es Test");
        etPhone.setText("0965956536");
        etEmail.setText("phuong@gmail.com");
        etCMT.setText("355641564");
        etNgayCap.setText("12/11/2017");
        etNoiCap.setText("Ha Noi");
        etNoiThuongTru.setText("Ha Noi");
        etDiaChiSuDung.setText("25 Vũ Phạm hàm - Cầu Giấy - Hà Nội");
        etSoHoDungChung.setText("1");
        etCongSuatDangKy.setText("220");
    }

    private void setView() {
        etNgayCap.setFocusable(false);
        switch (maCapDien) {
            case Common.CAP_MOI_SINH_HOAT:
                LOAI_CAPMOI = "Chưa có điện";
                MA_CAP_DIEN_MOI = "CDIEN01";
                btnAdd.setVisibility(View.GONE);
                btnInfo.setVisibility(View.GONE);
                llTitleSoHo.setVisibility(View.GONE);
                etSoHoDungChung.setVisibility(View.GONE);
                llGiayToTuyThan.setVisibility(View.VISIBLE);
                llThongTinDauNoi.setVisibility(View.GONE);
                llGiayCamKet.setVisibility(View.GONE);
                llXacDinhChuThe.setVisibility(View.VISIBLE);
                llSoHoKhau.setVisibility(View.GONE);
                llUyQuyen.setVisibility(View.GONE);
                break;
            case Common.CAP_MOI_DUNG_CHUNG:
                LOAI_CAPMOI = "dùng chung công tơ";
                MA_CAP_DIEN_MOI = "CDIEN02";
                btnAdd.setVisibility(View.VISIBLE);
                btnInfo.setVisibility(View.VISIBLE);
                llTitleSoHo.setVisibility(View.VISIBLE);
                etSoHoDungChung.setVisibility(View.VISIBLE);
                llGiayToTuyThan.setVisibility(View.VISIBLE);
                llThongTinDauNoi.setVisibility(View.GONE);
                llGiayCamKet.setVisibility(View.GONE);
                llXacDinhChuThe.setVisibility(View.VISIBLE);
                llSoHoKhau.setVisibility(View.VISIBLE);
                llUyQuyen.setVisibility(View.VISIBLE);
                break;
            case Common.TACH_CONG_TO:
                LOAI_CAPMOI = "tách công tơ";
                MA_CAP_DIEN_MOI = "CDIEN03";
                btnAdd.setVisibility(View.GONE);
                btnInfo.setVisibility(View.GONE);
                llTitleSoHo.setVisibility(View.GONE);
                etSoHoDungChung.setVisibility(View.GONE);
                llGiayToTuyThan.setVisibility(View.VISIBLE);
                llThongTinDauNoi.setVisibility(View.GONE);
                llGiayCamKet.setVisibility(View.VISIBLE);
                llXacDinhChuThe.setVisibility(View.VISIBLE);
                llSoHoKhau.setVisibility(View.GONE);
                llUyQuyen.setVisibility(View.GONE);
                break;

            case Common.CAP_MOI_SINH_HOAT_TRUNG_AP:
                LOAI_CAPMOI = "chưa có điện";
                MA_CAP_DIEN_MOI = "CDIEN07";
                btnAdd.setVisibility(View.GONE);
                btnInfo.setVisibility(View.GONE);
                llTitleSoHo.setVisibility(View.GONE);
                etSoHoDungChung.setVisibility(View.GONE);
                llGiayToTuyThan.setVisibility(View.VISIBLE);
                llThongTinDauNoi.setVisibility(View.VISIBLE);
                llGiayCamKet.setVisibility(View.VISIBLE);
                llXacDinhChuThe.setVisibility(View.VISIBLE);
                llSoHoKhau.setVisibility(View.GONE);
                llUyQuyen.setVisibility(View.GONE);
                break;
        }
    }

    private BuyElectricEntity setBuyElectric() {
        BuyElectricEntity buyElectricEntity = new BuyElectricEntity();
        buyElectricEntity.setTEN_NGUOIYCAU(etName.getText().toString());
        buyElectricEntity.setDTHOAI(etPhone.getText().toString());
        buyElectricEntity.setEMAIL(etEmail.getText().toString());
        buyElectricEntity.setCMT(etCMT.getText().toString());
        buyElectricEntity.setNGAY_CAPCMT(etNgayCap.getText().toString());
        buyElectricEntity.setNOI_CAPCMT(etNoiCap.getText().toString());
        buyElectricEntity.setDCHI_NGUOIYCAU(etNoiThuongTru.getText().toString());
        buyElectricEntity.setDCHI_DDIEN(etDiaChiSuDung.getText().toString());
        buyElectricEntity.setMA_DVIDCHINH(MA_DV_HANH_CHINH);
        buyElectricEntity.setLOAI_CAPMOI(LOAI_CAPMOI);
        if (!TextUtils.isEmpty(etCongSuatDangKy.getText().toString()))
            buyElectricEntity.setCONG_SUAT(Integer.valueOf(etCongSuatDangKy.getText().toString()));
        if (maCapDien == Common.CAP_MOI_DUNG_CHUNG)
            if (!TextUtils.isEmpty(etSoHoDungChung.getText().toString()))
                buyElectricEntity.setSO_HO(Integer.valueOf(etSoHoDungChung.getText().toString()));
        buyElectricEntity.setTHOI_GIAN("dai_han");
        buyElectricEntity.setNGUON_TNHAN("Android");
        buyElectricEntity.setTKHOAN_KHANG(etSoTaiKhoan.getText().toString());
        buyElectricEntity.setMA_LOAI_YCAU_MOI(MA_CAP_DIEN_MOI);
        buyElectricEntity.setMA_NHANG(MA_NHANG);
        buyElectricEntity.setLstUploadFiles(loaiGToEntities);
        buyElectricEntity.setLstDChung(hoDungChungEntities);
        return buyElectricEntity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.TAKE_PHOTO_BY_CAMERA) {
        } else if (requestCode == Common.SELECT_PHOTO_FROM_LIBRARY) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                String filePath = Common.getPath(getContext(), selectedImageUri);
                if (filePath == null) {
                    filePath = selectedImageUri.toString();
                }
                filePath = filePath.replace("file://", "");
                setFile(filePath);
            }
        }
    }


    void showAddHoDungChung() {
        if (dlAddHo == null) {
            dlAddHo = new Dialog(getContext(), R.style.full_screen_dialog);
        }

        //setting custom layout to dialog
        dlAddHo.setContentView(R.layout.dl_add_ho_dung_chung);
        dlAddHo.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);

        final EditText edName = dlAddHo.findViewById(R.id.dl_add_ho_dung_chung_etName);
        final EditText edPhone = dlAddHo.findViewById(R.id.dl_add_ho_dung_chung_etPhone);
        final EditText edAddress = dlAddHo.findViewById(R.id.dl_add_ho_dung_chung_etAddress);
        Button btnAdd = dlAddHo.findViewById(R.id.dl_add_ho_dung_chung_btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edName.getText().toString()) || TextUtils.isEmpty(edAddress.getText().toString()) || TextUtils.isEmpty(edPhone.getText().toString())) {
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập đầy đủ thông tin", Color.WHITE, getString(R.string.common_ok), Color.RED);
                } else {
                    HoDungChungEntity hoDungChungEntity = new HoDungChungEntity();
                    hoDungChungEntity.setTEN_CHUHO(edName.getText().toString());
                    hoDungChungEntity.setSDT_LIENHE(edPhone.getText().toString());
                    hoDungChungEntity.setDIA_CHI(edAddress.getText().toString());
                    hoDungChungEntities.add(hoDungChungEntity);
                    dlAddHo.dismiss();
                }
            }
        });
        dlAddHo.show();
    }

    private void setRequest() {
        CapMoiSinhHoatMutiRequest capMoiSinhHoatMutiRequest = new CapMoiSinhHoatMutiRequest();
        capMoiSinhHoatMutiRequest.setFileGiayToTuyThan(fileGiayToTuyThan);
        capMoiSinhHoatMutiRequest.setFileXacDinhChuThe(fileXacDinhChuThe);
        capMoiSinhHoatMutiRequest.setFileSoHoKhau(fileSoHoKhau);
        capMoiSinhHoatMutiRequest.setFileUyQuyen(fileUyQuyen);
        capMoiSinhHoatMutiRequest.setFileGiayCamKet(fileGiayCamKet);
        capMoiSinhHoatMutiRequest.setFileThongTinDauNoi(fileThongTinDauNoi);
        Gson gson = new Gson();
        capMoiSinhHoatMutiRequest.setModel(gson.toJson(setBuyElectric()));
        mApi.postCapMoiDienSinhHoat2(Common.REQUEST_POST_CAP_MOI_DIEN_SINH_HOAT, capMoiSinhHoatMutiRequest, this);
        showLoadingDialog("Đang gửi dữ liệu...");
    }

    private void showAvatarChooserDialog() {
        // Show a dialog to chose
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(getContext());
        builderDialog.setMessage("Chọn ảnh từ");
        builderDialog.setPositiveButton("Thư viện ảnh", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), Common.SELECT_PHOTO_FROM_LIBRARY);
            }
        });

        builderDialog.setNegativeButton("Chụp ảnh", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    String path = Environment.getExternalStorageDirectory().getPath() + Common.PROGRAM_PATH;
                    File f = new File(path);
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    final String fileName = path
                            + "/"
                            + loaiGiayTo + DateHelper.stringFromDate(Calendar.getInstance().getTime(), "dd_MM_yyyy_HH_mm_ss") + ".jpg";
                    setFile(fileName);
                    startActivityForResult(takePictureIntent, Common.TAKE_PHOTO_BY_CAMERA);
                }
            }
        });

        AlertDialog dialog = builderDialog.show();
        TextView messageText = dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        messageText.setPadding(10, 20, 10, 5);
    }

    private void setListFileUpload(File file) {
        LoaiGToEntity loaiGToEntity = new LoaiGToEntity();
        loaiGToEntity.setFileName(file.getName());
        loaiGToEntity.setMA_LOAI_GTO(loaiGiayTo);
        loaiGToEntity.setFileExt("jpg");
        loaiGToEntities.add(loaiGToEntity);
    }
    private void setFile(String path){
        if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.GIAY_TO_TUY_THAN.toString())) {
            fileGiayToTuyThan = Common.CreatFile(path);
            setListFileUpload(fileGiayToTuyThan);
        } else if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.XAC_DINH_CHU_THE_HĐ.toString())) {
            fileXacDinhChuThe = Common.CreatFile(path);
            setListFileUpload(fileXacDinhChuThe);
        } else if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.KHAC.toString())) {
            fileSoHoKhau = Common.CreatFile(path);
            setListFileUpload(fileSoHoKhau);
        } else if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.GIAY_UY_QUYEN.toString())) {
            fileUyQuyen = Common.CreatFile(path);
            setListFileUpload(fileUyQuyen);
        } else if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.GIAY_CAM_KET_THANH_TOAN.toString())) {
            fileGiayCamKet = Common.CreatFile(path);
            setListFileUpload(fileGiayCamKet);
        } else if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.DANG_KY_DAU_NOI.toString())) {
            fileThongTinDauNoi = Common.CreatFile(path);
            setListFileUpload(fileThongTinDauNoi);
        }
    }

//endregion
}
