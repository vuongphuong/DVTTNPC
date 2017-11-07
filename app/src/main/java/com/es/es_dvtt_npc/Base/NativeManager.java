package com.es.es_dvtt_npc.Base;

import android.support.v4.app.Fragment;

/**
 * Created by phuon on 5/25/2016.
 */
public interface NativeManager {

    int getPageHolder();

    void syncActionBar();

    void addFragment(Fragment fragment, String name);

    void switchPage(Fragment fragment, String name);

    Fragment getActivePage();

    void goBack();
}
