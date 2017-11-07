package com.es.es_dvtt_npc.Base;

import android.content.Context;
import android.content.SharedPreferences;


public abstract class BasePrefers {
    protected Context mContext;

    public BasePrefers() {
        mContext = BaseActivity.context;
    }

    protected SharedPreferences getPreferences() {
        return mContext.getSharedPreferences(getFileNamePrefers(),
                Context.MODE_PRIVATE);
    }

    protected SharedPreferences.Editor getEditor() {
        return getPreferences().edit();
    }

    protected abstract String getFileNamePrefers();
}