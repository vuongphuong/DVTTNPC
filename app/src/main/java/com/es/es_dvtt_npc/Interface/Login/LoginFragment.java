package com.es.es_dvtt_npc.Interface.Login;

import android.content.res.TypedArray;
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

import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.CurrentPrefers;
import com.es.es_dvtt_npc.Interface.BuyElectric.BuyElectricAdapter;
import com.es.es_dvtt_npc.Interface.Home.MenuItem;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by My_PC on 10/31/2017.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
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
    }

//endregion

    //region Navigation


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
                    }else {
                        CurrentPrefers.getInstance().clearAll();
                    }
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

//endregion
}
