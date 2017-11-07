package com.es.es_dvtt_npc.Base;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.es.es_dvtt_npc.BaseAPI.Api;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.PermissionGrant;


/**
 * Created by phuon on 5/25/2016.
 */
public class BaseFragment extends Fragment {
    protected NativeManager mNativeManager;
    protected ProgressDialog mProgressDialog;
    protected Api mApi;

    InputMethodManager imm;

    //region Activity Life Cycle
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof NativeManager) {
            mNativeManager = (NativeManager) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = App.get().getApi();
        if (!PermissionGrant.checkSelfPermission(getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})) {
            PermissionGrant.verify(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Common.READ_EXTERNAL_STORAGE);
        }
        if (!PermissionGrant.checkSelfPermission(getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
            PermissionGrant.verify(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Common.REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mNativeManager.syncActionBar();
        hideKeyboard();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideKeyboard();
    }


//endregion
    //region Helper
    public InputMethodManager getIMM() {
        if (imm == null) {
            imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        return imm;
    }

    public void hideKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            getIMM().hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected void showLoadingDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(message);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected void dismissLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
//endregion

}