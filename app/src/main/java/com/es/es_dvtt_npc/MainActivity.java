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

import com.es.es_dvtt_npc.Base.BaseActivity;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Interface.BuyElectric.BuyElectricFragment;
import com.es.es_dvtt_npc.Interface.Home.HomeFragment;
import com.es.es_dvtt_npc.Interface.Login.LoginFragment;
import com.es.es_dvtt_npc.Interface.Request.RequestFragment;
import com.es.es_dvtt_npc.Interface.Search.SearchFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private DrawerLayout navDrawerLayout;
    private Toolbar toolbar;
    ImageView ivMenu,ivCall;
    LinearLayout ivBack;
    TextView tvTitleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivMenu = (ImageView) toolbar.findViewById(R.id.toolbar_btnMenu);
        ivBack = (LinearLayout) toolbar.findViewById(R.id.toolbar_btnBack);
        ivCall = (ImageView) toolbar.findViewById(R.id.toolbar_btnCall);
        tvTitleToolbar = (TextView) toolbar.findViewById(R.id.toolbar_title_center);

        if (getActivePage() == null) {
            HomeFragment homeFragment = new HomeFragment();
            switchPage(homeFragment, "home");
        }
        ivMenu.setOnClickListener(this);
        ivCall.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public int getPageHolder() {
        return R.id.act_login_main;
    }

    @Override
    public void syncActionBar() {
        Fragment fragment = getActivePage();
        if (fragment instanceof HomeFragment){
            setToolbar(getString(R.string.home),View.VISIBLE,View.GONE,View.VISIBLE);
        }else if (fragment instanceof BuyElectricFragment){
            setToolbar(getString(R.string.dichvucapmoi),View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof LoginFragment){
            setToolbar(getString(R.string.login),View.GONE,View.VISIBLE,View.GONE);
        }else if (fragment instanceof SearchFragment){
            setToolbar(getString(R.string.tracuuthongtin),View.GONE,View.VISIBLE,View.VISIBLE);
        }else if (fragment instanceof RequestFragment){
            setToolbar(getString(R.string.dichvutrongquatrinh),View.GONE,View.VISIBLE,View.VISIBLE);
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
            goBack();
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
    void setToolbar(String title,int showMenu,int showBack,int showHotline){
        tvTitleToolbar.setText(title);
        ivBack.setVisibility(showBack);
        ivMenu.setVisibility(showMenu);
        ivCall.setVisibility(showHotline);
    }
}
