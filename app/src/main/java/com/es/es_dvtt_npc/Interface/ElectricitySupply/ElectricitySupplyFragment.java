package com.es.es_dvtt_npc.Interface.ElectricitySupply;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.DVDChinhResponse;
import com.es.es_dvtt_npc.BaseAPI.Request.CapMoiDienSinhHoatRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.DVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.GetChildDVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.test;
import com.es.es_dvtt_npc.BaseAPI.ResponseListener;
import com.es.es_dvtt_npc.BaseAPI.errors.AuthFailureError;
import com.es.es_dvtt_npc.BaseAPI.errors.ParserError;
import com.es.es_dvtt_npc.BaseAPI.errors.ServerError;
import com.es.es_dvtt_npc.Data.Object.BuyElectricEntity;
import com.es.es_dvtt_npc.Data.Object.CityEntity;
import com.es.es_dvtt_npc.Data.Object.LoaiGToEntity;
import com.es.es_dvtt_npc.Helper.AppAlertDialog;
import com.es.es_dvtt_npc.Helper.AppLog;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.DateHelper;
import com.es.es_dvtt_npc.Interface.Search.SearchAdapter;
import com.es.es_dvtt_npc.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.es.es_dvtt_npc.R.id.image;

/**
 * Created by My_PC on 11/1/2017.
 */

public class ElectricitySupplyFragment extends BaseFragment implements ResponseListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    private EditText etName, etPhone, etEmail, etCMT, etNgayCap, etNoiCap,
            etNoiThuongTru, etDiaChiSuDung, etSoHoDungChung, etCongSuatDangKy, etSoTaiKhoan;
    private Spinner spTinh, spHuyen, spXa, spNganHang;
    private ArrayList<CityEntity> dsTinh;
    private ArrayList<CityEntity> dsQuan;
    private ArrayList<CityEntity> dsXa;
    private Button btnSend;
    File fileImageMeter;
    ArrayAdapter<CityEntity> adapterDviTinh, adapterDviHuyen, adapterDviXa;

    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View v) {
        etName = (EditText) v.findViewById(R.id.f_electricity_supply_tvNameCustomer);
        etPhone = (EditText) v.findViewById(R.id.f_electricity_supply_tvPhone);
        etEmail = (EditText) v.findViewById(R.id.f_electricity_supply_tvEmail);
        etCMT = (EditText) v.findViewById(R.id.f_electricity_supply_tvPeopleID);
        etNgayCap = (EditText) v.findViewById(R.id.f_electricity_supply_tvDateRange);
        etNoiCap = (EditText) v.findViewById(R.id.f_electricity_supply_tvNoiCap);
        etNoiThuongTru = (EditText) v.findViewById(R.id.f_electricity_supply_tvNoiThuongTru);
        etDiaChiSuDung = (EditText) v.findViewById(R.id.f_electricity_supply_tvDiaChiSuDung);
        etCongSuatDangKy = (EditText) v.findViewById(R.id.f_electricity_supply_tvCongSuatDangKy);
        etSoHoDungChung = (EditText) v.findViewById(R.id.f_electricity_supply_tvSoHoDungChung);
        etSoTaiKhoan = (EditText) v.findViewById(R.id.f_electricity_supply_tvTaiKhoan);
        spTinh = (Spinner) v.findViewById(R.id.f_electricity_supply_spTinh);
        spHuyen = (Spinner) v.findViewById(R.id.f_electricity_supply_spHuyen);
        spXa = (Spinner) v.findViewById(R.id.f_electricity_supply_spXa);
        spNganHang = (Spinner) v.findViewById(R.id.f_electricity_supply_spNganHang);
        btnSend = (Button) v.findViewById(R.id.f_electricity_supply_btnSend);
        setText();

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
        DVDChinhRequest dvdChinhRequest = new DVDChinhRequest();
        mApi.getDVDChinh(Common.REQUEST_DVDCHINH, dvdChinhRequest, this);
        btnSend.setOnClickListener(this);
    }


//endregion

    //region Navigation


//endregion

    //region Control Action
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (view.getId() == R.id.f_electricity_supply_spTinh) {

        } else if (view.getId() == R.id.f_electricity_supply_spHuyen) {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.f_electricity_supply_btnSend) {
            if (etName.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() || etCMT.getText().toString().isEmpty() || etNgayCap.getText().toString().isEmpty()
                    || etNoiCap.getText().toString().isEmpty() || etNoiThuongTru.getText().toString().isEmpty() | etDiaChiSuDung.getText().toString().isEmpty()) {
                AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Điền đầy đủ thông tin có cần thiết", Color.WHITE, getString(R.string.common_ok), Color.RED);
            } else {
                CapMoiDienSinhHoatRequest capMoiDienSinhHoatRequest = new CapMoiDienSinhHoatRequest();
                Gson gson = new Gson();
                String json = gson.toJson(setBuyElectric());
//                File file = new File();
                capMoiDienSinhHoatRequest.setModel(json);
                mApi.postCapMoiDienSinhHoat(Common.REQUEST_POST_CAP_MOI_DIEN_SINH_HOAT, capMoiDienSinhHoatRequest, this);
//                chooseCameraPick();

            }
        }
    }


//endregion

    //region Control Delegate
    public void chooseCameraPick() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            String path = Environment.getExternalStorageDirectory().getPath() + Common.PROGRAM_PATH;
            try {
                File f = new File(path);
                if (!f.exists()) {
                    f.mkdir();
                }
                final String fileName = path
                        + "/"
                        + "avatar_" + DateHelper.stringFromDate(Calendar.getInstance().getTime(), "dd_MM_yyyy_HH_mm_ss") + ".jpg";
                fileImageMeter = new File(fileName);
                if (fileImageMeter.exists()) {
                    fileImageMeter.delete();
                }

                fileImageMeter.createNewFile();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImageMeter));
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivityForResult(takePictureIntent, Common.TAKE_PHOTO_BY_CAMERA);
        }
    }

//endregion

    //region API
    @Override
    public BaseResponse parse(int requestId, Call call, Response response) throws Exception {
        return new DVDChinhResponse(response);
    }

    @Override
    public void onResponse(int requestId, BaseResponse response) {
        if (requestId == Common.REQUEST_DVDCHINH) {
            DVDChinhResponse dvdChinhResponse = (DVDChinhResponse) response;
            dsTinh = dvdChinhResponse.getCityEntities();
            adapterDviTinh = new ArrayAdapter<CityEntity>(getContext(), R.layout.simple_item_list, dsTinh);
            adapterDviTinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTinh.setAdapter(adapterDviTinh);
            spTinh.setSelection(Adapter.NO_SELECTION, false);
        } else if (requestId == Common.REQUEST_GET_CHILD_DVDCHINH_HUYEN) {
            DVDChinhResponse dvdChinhResponse = (DVDChinhResponse) response;
            dsQuan = dvdChinhResponse.getCityEntities();
            adapterDviHuyen = new ArrayAdapter<CityEntity>(getContext(), R.layout.simple_item_list, dsQuan);
            adapterDviHuyen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spHuyen.setAdapter(adapterDviHuyen);
        } else if (requestId == Common.REQUEST_GET_CHILD_DVDCHINH_XA) {
            DVDChinhResponse dvdChinhResponse = (DVDChinhResponse) response;
            dsXa = dvdChinhResponse.getCityEntities();
            ArrayAdapter<CityEntity> adapterDvi = new ArrayAdapter<CityEntity>(getContext(), R.layout.simple_item_list, dsXa);
            adapterDvi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spXa.setAdapter(adapterDvi);
        }
    }

    @Override
    public void onError(int requestId, Exception e) {
        if (e instanceof ServerError) {
            ServerError serverError = (ServerError) e;
            AppLog.d(getString(R.string.app_name), serverError.getError().getErrorDescription());
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, getString(R.string.error), Color.WHITE, getString(R.string.common_ok), Color.RED);
        } else if (e instanceof ParserError) {
            AppLog.d(getString(R.string.app_name), "parser data error request: " + requestId);
        } else if (e instanceof AuthFailureError) {
            AppLog.d(getString(R.string.app_name), "AuthFailure error request: " + requestId);
        } else {
            AppLog.d(getString(R.string.app_name), "unKnown error request: " + requestId);
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng xem lại kết nối đến service", Color.WHITE, getString(R.string.common_ok), Color.RED);
        }
    }

//endregion

    //region Helper
    public void setText() {
        etName.setText("Es Test");
        etPhone.setText("0965956536");
        etEmail.setText("");
        etCMT.setText("355641564");
        etNgayCap.setText("12/11/2017");
        etNoiCap.setText("Ha Noi");
        etNoiThuongTru.setText("Ha Noi");
        etDiaChiSuDung.setText("25 Vũ Phạm hàm - Cầu Giấy - Hà Nội");
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
        buyElectricEntity.setMA_DVIDCHINH("0101699999");
        buyElectricEntity.setMA_LOAI_YCAU("CDIEN");
        ArrayList<LoaiGToEntity> loaiGToEntities = new ArrayList<>();
        LoaiGToEntity loaiGToEntity = new LoaiGToEntity();
        loaiGToEntity.setFileExt("jpg");
        loaiGToEntity.setFileName("test");
        loaiGToEntity.setMA_LOAI_GTO("CMT");
        loaiGToEntities.add(loaiGToEntity);
        buyElectricEntity.setLstUploadFiles(loaiGToEntities);
        return buyElectricEntity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.TAKE_PHOTO_BY_CAMERA) {
            test test = new test();
//            test.setModel(fileImageMeter);
            Gson gson = new Gson();
            String json = gson.toJson(setBuyElectric());
            test.setImageFile("{" + "\"Name\":\"phuong\"," + "\"Value\":\"test\"" +"}");
            mApi.postCapMoiDienSinhHoat2(Common.REQUEST_POST_CAP_MOI_DIEN_SINH_HOAT, test, this);
        }
    }
//endregion
}
