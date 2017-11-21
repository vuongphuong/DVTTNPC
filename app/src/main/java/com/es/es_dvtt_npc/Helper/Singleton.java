package com.es.es_dvtt_npc.Helper;


import com.es.es_dvtt_npc.Data.Object.CustomerInfoEntity;
import com.es.es_dvtt_npc.Data.Object.LoginEntity;

/**
 * Created by My_PC on 8/30/2017.
 */

public class Singleton {
    private static final Singleton singleton = new Singleton();
    public LoginEntity loginEntity;
    public CustomerInfoEntity customerInfoEntity;
    public String idManager;
    public boolean isLogin;
    public int MaCapDien;

    public static Singleton getInstance() {
        return singleton;
    }

    private Singleton() {
        isLogin = false;
    }
}
