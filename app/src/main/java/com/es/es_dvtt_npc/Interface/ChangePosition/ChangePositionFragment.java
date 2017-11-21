package com.es.es_dvtt_npc.Interface.ChangePosition;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.CapMoiSinhHoatMultiReponse;
import com.es.es_dvtt_npc.BaseAPI.Request.ThayDoiCongSuatRequest;
import com.es.es_dvtt_npc.BaseAPI.ResponseListener;
import com.es.es_dvtt_npc.BaseAPI.errors.AuthFailureError;
import com.es.es_dvtt_npc.BaseAPI.errors.ParserError;
import com.es.es_dvtt_npc.BaseAPI.errors.ServerError;
import com.es.es_dvtt_npc.Data.Object.LoaiGToEntity;
import com.es.es_dvtt_npc.Data.Object.ThayDoiEntity;
import com.es.es_dvtt_npc.Helper.AppAlertDialog;
import com.es.es_dvtt_npc.Helper.AppLog;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.DateHelper;
import com.es.es_dvtt_npc.Helper.Singleton;
import com.es.es_dvtt_npc.Interface.Login.LoginFragment;
import com.es.es_dvtt_npc.MainActivity;
import com.es.es_dvtt_npc.R;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by PhuongVV on 10/31/2017.
 */

public class ChangePositionFragment extends BaseFragment implements ResponseListener, View.OnClickListener, ActionbarInfo {
    private TextView tvName, tvIDCustomer, tvAddress, tvTitleContent;
    private EditText etName, etPhone, etAddress, etEmail, etContent;
    private Button btnSend;
    private LinearLayout btnXacDinhChuThe, btnVanBanCuaCoQuanNhaNuoc;
    private File fileXacDinhChuThe, fileVanBanCuaCoQuan;
    private static final String MA_YEU_CAU = "ma_yeu_cau";
    private String getMaYeuCau, loaiGiayTo;
    private int maCapDien;
    private ArrayList<LoaiGToEntity> loaiGToEntities;

    //region Activity Life Cycle
    public static ChangePositionFragment newInstance(int maCapDien) {
        Bundle args = new Bundle();
        args.putInt(MA_YEU_CAU, maCapDien);
        ChangePositionFragment fragment = new ChangePositionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            maCapDien = savedInstanceState.getInt(MA_YEU_CAU);
        } else if (getArguments() != null) {
            maCapDien = getArguments().getInt(MA_YEU_CAU);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MA_YEU_CAU, maCapDien);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.change_position_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!Singleton.getInstance().isLogin) {
            LoginFragment loginFragment = new LoginFragment();
            mNativeManager.addFragment(loginFragment, "Login");
        } else
            initialize(view);
        loaiGToEntities = new ArrayList<>();
    }

//endregion

    //region Init View
    public void initialize(View v) {
        tvName = (TextView) v.findViewById(R.id.f_change_position_tvNameCustomer);
        tvIDCustomer = (TextView) v.findViewById(R.id.f_change_position_tvIdCustomer);
        tvAddress = (TextView) v.findViewById(R.id.f_change_position_tvAddress);
        tvTitleContent = (TextView) v.findViewById(R.id.f_change_position_tvTitleContent);
        etName = (EditText) v.findViewById(R.id.f_change_position_etName);
        etPhone = (EditText) v.findViewById(R.id.f_change_position_etPhone);
        etAddress = (EditText) v.findViewById(R.id.f_change_position_etAddress);
        etEmail = (EditText) v.findViewById(R.id.f_change_position_etEmail);
        etContent = (EditText) v.findViewById(R.id.f_change_position_etContent);
        btnSend = (Button) v.findViewById(R.id.f_change_position_btnSend);
        btnXacDinhChuThe = (LinearLayout) v.findViewById(R.id.f_change_position_btnXacDinhChuThe);
        btnVanBanCuaCoQuanNhaNuoc = (LinearLayout) v.findViewById(R.id.f_change_position_btnVanBanCuaCoQuanNhaNuoc);

        btnSend.setOnClickListener(this);
        btnXacDinhChuThe.setOnClickListener(this);
        btnVanBanCuaCoQuanNhaNuoc.setOnClickListener(this);
        setView();
    }


//endregion

    //region Navigation
    @Override
    public String getActionbarTitle() {
        switch (maCapDien) {
            case Common.YEU_CAU_THAY_DOI_VI_TRI:
                return getString(R.string.change_position);
            case Common.YEU_CAU_THAY_DOI_CHU_THE:
                return getString(R.string.person_singing_contract);
            case Common.YEU_CAU_GIA_HAN_HOP_DONG:
                return getString(R.string.giahan);
            case Common.YEU_CAU_CHAM_DUT_HOP_DONG:
                return getString(R.string.chamdut);
            case Common.YEU_CAU_CAP_DIEN_TRO_LAI:
                return getString(R.string.capdientrolai);
            case Common.YEU_CAU_CAP_DIEN_TRO_LAI_CO_QUAN_NHA_NUOC:
                return getString(R.string.capdientrolai);
        }
        return null;
    }

//endregion

    //region Control Action
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.f_change_position_btnSend:
                if (TextUtils.isEmpty(etName.getText().toString()))
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập tên người yêu cầu", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etPhone.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập số điện thoại liên hệ", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else if (etAddress.getText().toString().isEmpty())
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng nhập địa chỉ người yêu cầu", Color.WHITE, getString(R.string.common_ok), Color.RED);
                else {
                    showLoadingDialog("Đang gửi yêu cầu...");
                    ThayDoiCongSuatRequest thayDoiCongSuatRequest = new ThayDoiCongSuatRequest();
                    Gson gson = new Gson();
                    String json = gson.toJson(getModel());
                    thayDoiCongSuatRequest.setModel(json);
                    thayDoiCongSuatRequest.setFileDangKyPhuTai(fileXacDinhChuThe);
                    thayDoiCongSuatRequest.setFileVanBanCuaCoQuan(fileVanBanCuaCoQuan);
                    mApi.postThayDoiCongSuat(Common.REQUEST_POST_THAY_DOI_CONG_SUAT, thayDoiCongSuatRequest, this);
                }
                break;
            case R.id.f_change_position_btnXacDinhChuThe:
                loaiGiayTo = Common.LOAI_GIAY_TO.XAC_DINH_CHU_THE_HĐ.toString();
                showAvatarChooserDialog();
                break;
            case R.id.f_change_position_btnVanBanCuaCoQuanNhaNuoc:
                loaiGiayTo = Common.LOAI_GIAY_TO.VAN_BAN_CHO_PHEP.toString();
                showAvatarChooserDialog();
                break;

        }

    }

    private void setView() {
        tvName.setText(Singleton.getInstance().loginEntity.getTenKhachHang());
        tvIDCustomer.setText(Singleton.getInstance().loginEntity.getCustomerId());
        tvAddress.setText(Singleton.getInstance().loginEntity.getAddress());
        etName.setText(Singleton.getInstance().loginEntity.getTenKhachHang());
        etPhone.setText(Singleton.getInstance().loginEntity.getPhone());
        etAddress.setText(Singleton.getInstance().loginEntity.getAddress());
        switch (maCapDien) {
            case Common.YEU_CAU_THAY_DOI_VI_TRI:
                tvTitleContent.setText(getString(R.string.lydo));
                btnVanBanCuaCoQuanNhaNuoc.setVisibility(View.GONE);
                btnXacDinhChuThe.setVisibility(View.GONE);
                getMaYeuCau = "DDOI";
                break;
            case Common.YEU_CAU_THAY_DOI_CHU_THE:
                tvTitleContent.setText(getString(R.string.lydo));
                btnVanBanCuaCoQuanNhaNuoc.setVisibility(View.GONE);
                btnXacDinhChuThe.setVisibility(View.VISIBLE);
                getMaYeuCau = "TLYKMOI";
                break;
            case Common.YEU_CAU_GIA_HAN_HOP_DONG:
                tvTitleContent.setText(getString(R.string.noidung));
                btnVanBanCuaCoQuanNhaNuoc.setVisibility(View.GONE);
                btnXacDinhChuThe.setVisibility(View.GONE);
                getMaYeuCau = "GHAN";
                break;
            case Common.YEU_CAU_CHAM_DUT_HOP_DONG:
                tvTitleContent.setText(getString(R.string.noidung));
                btnVanBanCuaCoQuanNhaNuoc.setVisibility(View.GONE);
                btnXacDinhChuThe.setVisibility(View.GONE);
                getMaYeuCau = "TLYHD";
                break;
            case Common.YEU_CAU_CAP_DIEN_TRO_LAI:
                tvTitleContent.setText(getString(R.string.noidung));
                btnVanBanCuaCoQuanNhaNuoc.setVisibility(View.GONE);
                btnXacDinhChuThe.setVisibility(View.GONE);
                getMaYeuCau = "CDLAIKH";
                break;
            case Common.YEU_CAU_CAP_DIEN_TRO_LAI_CO_QUAN_NHA_NUOC:
                tvTitleContent.setText(getString(R.string.noidung));
                btnVanBanCuaCoQuanNhaNuoc.setVisibility(View.VISIBLE);
                btnXacDinhChuThe.setVisibility(View.GONE);
                getMaYeuCau = "CDLAICQ";
                break;
        }
    }
//endregion

    //region Control Delegate

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

//endregion

    //region API
    @Override
    public BaseResponse parse(int requestId, Call call, Response response) throws Exception {
        return new CapMoiSinhHoatMultiReponse(response);
    }

    @Override
    public void onResponse(int requestId, BaseResponse response) {
        switch (requestId) {
            case Common.REQUEST_POST_THAY_DOI_CONG_SUAT:
                dismissLoadingDialog();
                CapMoiSinhHoatMultiReponse capMoiSinhHoatMultiReponse = (CapMoiSinhHoatMultiReponse) response;
                if (capMoiSinhHoatMultiReponse.getResult().equals(Common.TRUE)) {
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Thành công", Color.WHITE, capMoiSinhHoatMultiReponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                } else {
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, capMoiSinhHoatMultiReponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                }
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
    private ThayDoiEntity getModel() {
        ThayDoiEntity thayDoiEntity = new ThayDoiEntity();
        thayDoiEntity.setMA_KHANG(Singleton.getInstance().loginEntity.getCustomerId());
        thayDoiEntity.setTEN_NGUOIYCAU(etName.getText().toString());
        thayDoiEntity.setDTHOAI(etPhone.getText().toString());
        thayDoiEntity.setEMAIL(etEmail.getText().toString());
        thayDoiEntity.setNOI_DUNG_YEU_CAU(etContent.getText().toString());
        thayDoiEntity.setMA_LOAI_YCAU_MOI(getMaYeuCau);
        thayDoiEntity.setDCHI_NGUOIYCAU(etAddress.getText().toString());
        thayDoiEntity.setNGUON_TNHAN("Android");
        thayDoiEntity.setMA_DVIQLY(Singleton.getInstance().loginEntity.getMaDLHuyen());
        thayDoiEntity.setMA_DVIDCHINH(Singleton.getInstance().loginEntity.getMaDLHuyen());
        return thayDoiEntity;
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

    private void setFile(String path) {
        if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.XAC_DINH_CHU_THE_HĐ.toString())) {
            fileXacDinhChuThe = Common.CreatFile(path);
            setListFileUpload(fileXacDinhChuThe);
        } else if (loaiGiayTo.equals(Common.LOAI_GIAY_TO.VAN_BAN_CHO_PHEP.toString())) {
            fileVanBanCuaCoQuan = Common.CreatFile(path);
            setListFileUpload(fileVanBanCuaCoQuan);
        }
    }


//endregion
}
