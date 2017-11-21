package com.es.es_dvtt_npc;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.BaseActivity;
import com.es.es_dvtt_npc.Data.Object.LoginEntity;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.CurrentPrefers;
import com.es.es_dvtt_npc.Helper.Singleton;
import com.es.es_dvtt_npc.Interface.BuyElectric.BuyElectricFragment;
import com.es.es_dvtt_npc.Interface.ChangCapacity.ChangeCapacityFragment;
import com.es.es_dvtt_npc.Interface.ChangePersonSingingContract.ChangePersonSingingContractFragment;
import com.es.es_dvtt_npc.Interface.ChangePosition.ChangePositionFragment;
import com.es.es_dvtt_npc.Interface.ChangePurpose.ChangePurposeFragment;
import com.es.es_dvtt_npc.Interface.ChangeTheNorm.ChangeTheNormFragment;
import com.es.es_dvtt_npc.Interface.Customer.CustomerFragment;
import com.es.es_dvtt_npc.Interface.ElectricityOutSide.ElectricityOutSideFragment;
import com.es.es_dvtt_npc.Interface.ElectricitySupply.ElectricitySupplyFragment;
import com.es.es_dvtt_npc.Interface.Home.HomeFragment;
import com.es.es_dvtt_npc.Interface.Login.LoginFragment;
import com.es.es_dvtt_npc.Interface.Request.RequestFragment;
import com.es.es_dvtt_npc.Interface.Search.SearchFragment;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private DrawerLayout navDrawerLayout;
    private Toolbar toolbar;
    ImageView ivMenu,ivCall;
    LinearLayout ivBack;
    private LinearLayout mnTrangChu,mnThongTinTaiKhoan,mnHuongDanSuDung,mnGioiThieu,mnDichVuCapMoi,mnDichVuSuDung,mnTraCuuThongTin
            ,mnDichVuHoTro,mnHuongDanDichVu;
    public static TextView tvTitleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivMenu = (ImageView) toolbar.findViewById(R.id.toolbar_btnMenu);
        ivBack = (LinearLayout) toolbar.findViewById(R.id.toolbar_btnBack);
        ivCall = (ImageView) toolbar.findViewById(R.id.toolbar_btnCall);
        tvTitleToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title_center);

        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mnTrangChu = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_home);
        mnThongTinTaiKhoan = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_customer);
        mnHuongDanSuDung = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_instructions);
        mnGioiThieu = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_introduce);
        mnDichVuCapMoi = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_buyElectric);
        mnDichVuSuDung = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_DichVuSuDung);
        mnTraCuuThongTin = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_TraCuuThongTin);
        mnDichVuHoTro = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_DichVuHoTro);
        mnHuongDanDichVu = (LinearLayout) navDrawerLayout.findViewById(R.id.menu_huongDanDichVu);

        if (getActivePage() == null) {
            HomeFragment homeFragment = new HomeFragment();
            switchPage(homeFragment, "home");
        }
        ivMenu.setOnClickListener(this);
        ivCall.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        mnTrangChu.setOnClickListener(this);
        mnThongTinTaiKhoan.setOnClickListener(this);
        mnHuongDanSuDung.setOnClickListener(this);
        mnGioiThieu.setOnClickListener(this);
        mnDichVuCapMoi.setOnClickListener(this);
        mnDichVuSuDung.setOnClickListener(this);
        mnTraCuuThongTin.setOnClickListener(this);
        mnDichVuHoTro.setOnClickListener(this);
        mnHuongDanDichVu.setOnClickListener(this);
        getLogin();
    }

    @Override
    public int getPageHolder() {
        return R.id.act_login_main;
    }

    @Override
    public void syncActionBar() {
        Fragment fragment = getActivePage();
        syncTitle(fragment);
        if (fragment instanceof HomeFragment){
            setToolbar(View.VISIBLE,View.GONE,View.VISIBLE);
        }else if (fragment instanceof BuyElectricFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof LoginFragment){
            setToolbar(View.GONE,View.VISIBLE,View.GONE);
        }else if (fragment instanceof SearchFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof RequestFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof ChangeCapacityFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof ChangePositionFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof ChangePurposeFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof ChangeTheNormFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof ChangePersonSingingContractFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof CustomerFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof ElectricitySupplyFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof ElectricityOutSideFragment){
            setToolbar(View.GONE,View.VISIBLE,View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_btnMenu) {
            if (navDrawerLayout.isDrawerOpen(Gravity.START)) {
                navDrawerLayout.closeDrawer(Gravity.START);
            } else {
                navDrawerLayout.openDrawer(Gravity.START);
            }
            hideKeyboard();
        }else if (v.getId() == R.id.toolbar_btnCall){
            Common.CallNumber(this);
        }else if (v.getId() == R.id.toolbar_btnBack){
            super.onBackPressed();
        }else if (v.getId() == R.id.menu_home){
            if (getActivePage() == null) {
                HomeFragment homeFragment = new HomeFragment();
                switchPage(homeFragment, "home");
            }
            navDrawerLayout.closeDrawer(Gravity.START,false);
        }else if (v.getId() == R.id.menu_customer){
            if (!Singleton.getInstance().isLogin){
                LoginFragment loginFragment = new LoginFragment();
                addFragment(loginFragment,"Login");
            }else {

            }
            navDrawerLayout.closeDrawer(Gravity.START);
        }else if (v.getId() == R.id.menu_instructions){
            navDrawerLayout.closeDrawer(Gravity.START);
        }else if (v.getId() == R.id.menu_introduce){
            navDrawerLayout.closeDrawer(Gravity.START);
        }else if (v.getId() == R.id.menu_buyElectric){
            BuyElectricFragment buyElectricFragment = new BuyElectricFragment();
            addFragment(buyElectricFragment,"CapMoi");
            navDrawerLayout.closeDrawer(Gravity.START);
        }else if (v.getId() == R.id.menu_DichVuSuDung){
            RequestFragment requestFragment = new RequestFragment();
            addFragment(requestFragment,"Request");
            navDrawerLayout.closeDrawer(Gravity.START);
        }else if (v.getId() == R.id.menu_TraCuuThongTin){
            SearchFragment searchFragment = new SearchFragment();
            addFragment(searchFragment,"Search");
            navDrawerLayout.closeDrawer(Gravity.START);
        }else if (v.getId() == R.id.menu_DichVuHoTro){
            navDrawerLayout.closeDrawer(Gravity.START);
        }else if (v.getId() == R.id.menu_huongDanDichVu){
            navDrawerLayout.closeDrawer(Gravity.START);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Common.REQUEST_CALL) {
            boolean grant = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    grant = false;
                }
            }
            if (grant) {
                Common.CallNumber(this);
            }
        }
    }
    void setToolbar(int showMenu,int showBack,int showHotline){
        ivBack.setVisibility(showBack);
        ivMenu.setVisibility(showMenu);
        ivCall.setVisibility(showHotline);
    }

    private void syncTitle(Fragment activePage) {
        if (tvTitleToolbar == null) return;

        if (activePage instanceof ActionbarInfo) {
            ActionbarInfo actionbarInfo = (ActionbarInfo) activePage;
            String title = actionbarInfo.getActionbarTitle();

            //TODO set title
            tvTitleToolbar.setText(title);
        }
    }

    void getLogin(){
        if (CurrentPrefers.getInstance().getSavePass()){
            Singleton.getInstance().isLogin = true;
            Gson gson = new Gson();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(CurrentPrefers.getInstance().getUserInfo());
                Singleton.getInstance().loginEntity = gson.fromJson(jsonObject.optString("data"),LoginEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
