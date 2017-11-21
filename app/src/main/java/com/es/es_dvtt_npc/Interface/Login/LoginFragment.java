package com.es.es_dvtt_npc.Interface.Login;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.App;
import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.CustomerInfoReponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.LoginReponse;
import com.es.es_dvtt_npc.BaseAPI.Request.CustomerInfoRequest;
import com.es.es_dvtt_npc.BaseAPI.Request.LoginRequest;
import com.es.es_dvtt_npc.BaseAPI.ResponseListener;
import com.es.es_dvtt_npc.BaseAPI.errors.AuthFailureError;
import com.es.es_dvtt_npc.BaseAPI.errors.ParserError;
import com.es.es_dvtt_npc.BaseAPI.errors.ServerError;
import com.es.es_dvtt_npc.Data.Object.CustomerInfoEntity;
import com.es.es_dvtt_npc.Data.Object.LoginEntity;
import com.es.es_dvtt_npc.Helper.AppAlertDialog;
import com.es.es_dvtt_npc.Helper.AppLog;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.CurrentPrefers;
import com.es.es_dvtt_npc.Helper.Singleton;
import com.es.es_dvtt_npc.Interface.BuyElectric.BuyElectricAdapter;
import com.es.es_dvtt_npc.Interface.Customer.CustomerFragment;
import com.es.es_dvtt_npc.Interface.Home.MenuItem;
import com.es.es_dvtt_npc.MainActivity;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by My_PC on 10/31/2017.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener,ResponseListener,ActionbarInfo {
    private EditText etUser, etPassword;
    ImageView ivHotline;
    private Button btnLogin;
    private CheckBox cbSave;

    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View v) {
        etUser = (EditText) v.findViewById(R.id.f_login_etUser);
        etPassword = (EditText) v.findViewById(R.id.f_login_etPassword);
        ivHotline = (ImageView) v.findViewById(R.id.f_login_HotLine);
        btnLogin = (Button) v.findViewById(R.id.f_login_btnLogin);
        cbSave = (CheckBox) v.findViewById(R.id.f_login_cbSavePass);
        ivHotline.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        etUser.setText(CurrentPrefers.getInstance().getUserName());
        etPassword.setText(CurrentPrefers.getInstance().getPassword());
        if (CurrentPrefers.getInstance().getSavePass())
            cbSave.isChecked();
    }

//endregion

    //region Navigation
    @Override
    public String getActionbarTitle() {
        return getString(R.string.login);
    }


//endregion

    //region Control Action
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.f_login_HotLine:
                Common.CallNumber(getActivity());
                break;
            case R.id.f_login_btnLogin:
                if (etUser.getText().toString().isEmpty()|| etPassword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin!",Toast.LENGTH_LONG).show();
                }else {
                    if (cbSave.isChecked()){
                        CurrentPrefers.getInstance().saveUserName(etUser.getText().toString());
                        CurrentPrefers.getInstance().savePassword(etPassword.getText().toString());
                        CurrentPrefers.getInstance().savePass(true);
                    }else {
                        CurrentPrefers.getInstance().clearAll();
                    }

                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUser(etUser.getText().toString());
                    loginRequest.setPass(etPassword.getText().toString());
                    mApi.login(Common.REQUEST_LOGIN,loginRequest,this);
                    showLoadingDialog("Đang đăng nhập...");
                }
                break;
        }
    }
//endregion

    //region Control Delegate


//endregion

    //region API


//endregion

    //region Helper
    private ArrayList<MenuItem> setItem(String[] title, TypedArray image) {
        ArrayList<MenuItem> arrDrawerItems = new ArrayList<MenuItem>();
        for (int i = 0; i < title.length; i++) {
            MenuItem drawerItem = new MenuItem();
            drawerItem.setItemName(title[i]);
            drawerItem.setImgResID(image.getDrawable(i));
            arrDrawerItems.add(drawerItem);
        }
        return arrDrawerItems;
    }

    @Override
    public BaseResponse parse(int requestId, Call call, Response response) throws Exception {
        switch (requestId){
            case Common.REQUEST_CUSTOMER_INFO:
                return new CustomerInfoReponse(response);
            default:
                return new LoginReponse(response) ;
        }
    }

    @Override
    public void onResponse(int requestId, BaseResponse response) {
        switch (requestId){
            case Common.REQUEST_LOGIN:
                LoginReponse loginReponse = (LoginReponse) response;
                LoginEntity loginEntity = loginReponse.loginEntity();
                if (loginEntity != null) {
//                    CustomerInfoRequest customerInfoRequest = new CustomerInfoRequest();
//                    customerInfoRequest.setUser(loginEntity.customerId);
//                    mApi.customerInfo(Common.REQUEST_CUSTOMER_INFO,customerInfoRequest,this);
                    Singleton.getInstance().loginEntity = loginEntity;
                    Singleton.getInstance().isLogin = true;
                    ((MainActivity) getActivity()).onBackPressed();
                }else{
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, loginReponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                }
                break;
            case Common.REQUEST_CUSTOMER_INFO:
                CustomerInfoReponse customerInfoReponse = (CustomerInfoReponse) response;
                if (customerInfoReponse.customerInfoEntity() != null){
                    Singleton.getInstance().customerInfoEntity = customerInfoReponse.customerInfoEntity();
                    ((MainActivity) getActivity()).onBackPressed();
                } else{
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, customerInfoReponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);
                }
                break;
        }
        dismissLoadingDialog();
    }

    @Override
    public void onError(int requestId, Exception e) {
        if (e instanceof ServerError) {
            ServerError serverError = (ServerError) e;
            AppLog.e(getString(R.string.app_name), serverError.getError().getErrorDescription());
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Lỗi thao tác trên service", Color.WHITE, getString(R.string.common_ok), Color.RED);
        } else if (e instanceof ParserError) {
            AppLog.e(getString(R.string.app_name), "parser data error request: " + requestId);
        } else if (e instanceof AuthFailureError) {
            AppLog.e(getString(R.string.app_name), "AuthFailure error request: " + requestId);
        } else {
            AppLog.e(getString(R.string.app_name), "unKnown error request: " + requestId);
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng xem lại kết nối đến service", Color.WHITE, getString(R.string.common_ok), Color.RED);
        }
        dismissLoadingDialog();
    }

//endregion
}
