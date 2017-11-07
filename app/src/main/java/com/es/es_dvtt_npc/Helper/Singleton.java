package com.es.es_dvtt_npc.Helper;



/**
 * Created by My_PC on 8/30/2017.
 */

public class Singleton {
    private static final Singleton singleton = new Singleton();
    public String IPAddress;
    public String idManager;
    public boolean isLogin;

    public static Singleton getInstance() {
        return singleton;
    }

    private Singleton() {
        isLogin = false;
        IPAddress = CurrentPrefers.getInstance().getIP();
        idManager = CurrentPrefers.getInstance().getidCompany();
    }
}
