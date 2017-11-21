package com.es.es_dvtt_npc.Interface.Customer;

import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.CustomerInfoReponse;
import com.es.es_dvtt_npc.BaseAPI.Request.CustomerInfoRequest;
import com.es.es_dvtt_npc.BaseAPI.ResponseListener;
import com.es.es_dvtt_npc.Data.Object.CustomerInfoEntity;
import com.es.es_dvtt_npc.Helper.AppAlertDialog;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.CurrentPrefers;
import com.es.es_dvtt_npc.Helper.Singleton;
import com.es.es_dvtt_npc.Interface.ChangCapacity.ChangeCapacityFragment;
import com.es.es_dvtt_npc.Interface.ChangePersonSingingContract.ChangePersonSingingContractFragment;
import com.es.es_dvtt_npc.Interface.ChangePosition.ChangePositionFragment;
import com.es.es_dvtt_npc.Interface.ChangePurpose.ChangePurposeFragment;
import com.es.es_dvtt_npc.Interface.ChangeTheNorm.ChangeTheNormFragment;
import com.es.es_dvtt_npc.Interface.Login.LoginFragment;
import com.es.es_dvtt_npc.Interface.Search.SearchAdapter;
import com.es.es_dvtt_npc.R;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by My_PC on 11/1/2017.
 */

public class CustomerFragment extends BaseFragment implements ResponseListener,AdapterView.OnItemClickListener,ActionbarInfo {
    private ListView lvItem;
    ImageView ivAvatar;
    TextView tvName;
    private final int ROW_UPDATE_PASSWORD = 2;
    private final int ROW_REGISTER_PUSH_NOTIFICATON = 3;
    private final int ROW_LOGOUT = 4;

    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customer_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View v) {
        lvItem = (ListView) v.findViewById(R.id.f_customer_info_lvItem);
        ivAvatar = (ImageView) v.findViewById(R.id.f_customer_info_ivAvatar);
        tvName = (TextView) v.findViewById(R.id.f_customer_info_tvName);

        CustomerInfoRequest customerInfoRequest = new CustomerInfoRequest();
        customerInfoRequest.setUser(Singleton.getInstance().loginEntity.getCustomerId());
        mApi.customerInfo(Common.REQUEST_CUSTOMER_INFO,customerInfoRequest,this);
    }



//endregion

    //region Navigation

    @Override
    public String getActionbarTitle() {
        return getString(R.string.info_customer);
    }

//endregion

    //region Control Action
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case ROW_UPDATE_PASSWORD:
                break;
            case ROW_REGISTER_PUSH_NOTIFICATON:
                break;
            case ROW_LOGOUT:
                onLogout();
                break;
        }
    }
//endregion

    //region Control Delegate


//endregion

    //region API
    @Override
    public BaseResponse parse(int requestId, Call call, Response response) throws Exception {
        return new CustomerInfoReponse(response);
    }

    @Override
    public void onResponse(int requestId, BaseResponse response) {
        switch (requestId){
            case Common.REQUEST_CUSTOMER_INFO:
                CustomerInfoReponse customerInfoReponse = (CustomerInfoReponse) response;
                if (customerInfoReponse.customerInfoEntity() != null) {
                    AdapterUserInfo adapterUserInfo = new AdapterUserInfo(getContext(), customerInfoReponse.customerInfoEntity());
                    lvItem.setAdapter(adapterUserInfo);
                }else {
                    AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, customerInfoReponse.getMessage(), Color.WHITE, getString(R.string.common_ok), Color.RED);

                }
                break;
        }
    }

    @Override
    public void onError(int requestId, Exception e) {

    }

//endregion

    //region Helper

    public void onLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.confirm_logout_message)
                .setNegativeButton(R.string.confirm_no, null)
                .setPositiveButton(R.string.confirm_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //clear data from share preferences.
                        CurrentPrefers.getInstance().clearAll();
                        Singleton.getInstance().isLogin = false;
                        LoginFragment loginFragment = new LoginFragment();
                        mNativeManager.addFragment(loginFragment,"Login");
                    }
                });
        builder.show();
    }

//endregion
}
