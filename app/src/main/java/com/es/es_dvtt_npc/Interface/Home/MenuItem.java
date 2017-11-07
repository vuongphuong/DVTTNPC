package com.es.es_dvtt_npc.Interface.Home;

import android.graphics.drawable.Drawable;

/**
 * Created by phuon on 7/4/2016.
 */
public class MenuItem {
    String ItemName;
    Drawable imgResID;

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public Drawable getImgResID() {
        return imgResID;
    }
    public void setImgResID(Drawable imgResID) {
        this.imgResID = imgResID;
    }

}
