package com.es.es_dvtt_npc.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.es.es_dvtt_npc.R;


/**
 * Created by My_PC on 8/30/2017.
 * @Author PhuongVV
 */

public abstract class BaseActivity extends AppCompatActivity implements NativeManager {

    protected FragmentManager mFragmentManager;
    InputMethodManager imm;
    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        context = this;
    }
    /**
     * Replace fragment ToBackStack
     * @param fragment fragment replace
     * @param name name fragment replace
     */
    @Override
    public void addFragment(Fragment fragment, String name) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_enter,
                R.anim.fragment_exit, R.anim.fragment_pop_enter,
                R.anim.fragment_pop_exit);
        ft.replace(getPageHolder(), fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    /**
     * Replace fragment no ToBackStack
     * @param fragment fragment replace
     * @param name name fragment replace
     */
    @Override
    public void switchPage(Fragment fragment, String name) {
        try {
            if (fragment == null) {
                return;
            }
            for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); i++) {
                int backStackId = mFragmentManager.getBackStackEntryAt(i).getId();
                mFragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.setCustomAnimations(R.anim.fragment_enter,
                    R.anim.fragment_exit, R.anim.fragment_pop_enter,
                    R.anim.fragment_pop_exit);
            ft.replace(getPageHolder(), fragment);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Fragment getActivePage() {
        return mFragmentManager.findFragmentById(getPageHolder());
    }

    @Override
    public void goBack() {
        if (mFragmentManager.popBackStackImmediate()) {
            mFragmentManager.popBackStack();
        } else {
            onBackPressed();
        }
    }
    public InputMethodManager getIMM() {
        if (imm == null) {
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        return imm;
    }
    public void hideKeyboard() {
        if (getCurrentFocus() != null) {
            getIMM().hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
