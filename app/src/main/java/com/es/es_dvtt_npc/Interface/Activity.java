package com.es.es_dvtt_npc.Interface;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.es.es_dvtt_npc.Base.BaseActivity;
import com.es.es_dvtt_npc.MainActivity;
import com.es.es_dvtt_npc.R;

import static com.es.es_dvtt_npc.Helper.Common.CheckForder;

/**
 * Created by My_PC on 10/31/2017.
 */

public class Activity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        CheckForder(getApplication());
        hideKeyboard();
        if (getActivePage() == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public int getPageHolder() {
        return R.id.act_login_main;
    }

    @Override
    public void syncActionBar() {

    }
}
