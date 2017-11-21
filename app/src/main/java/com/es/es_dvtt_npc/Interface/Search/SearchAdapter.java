package com.es.es_dvtt_npc.Interface.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.es.es_dvtt_npc.Base.AdapterDelegate;
import com.es.es_dvtt_npc.Interface.Home.MenuItem;
import com.es.es_dvtt_npc.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by My_PC on 10/31/2017.
 */

public class SearchAdapter extends BaseAdapter {
    Context c;
    ArrayList<MenuItem> menuItems;
    private LayoutInflater inflater;

    public SearchAdapter(Context context, ArrayList<MenuItem> menuItems) {
        this.c = context;
        this.menuItems = menuItems;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return menuItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = inflater.inflate(R.layout.item_buy_electric_childrent, null);
            holder.tvTitle = (TextView) view.findViewById(R.id.i_buy_electric_child_tvTitle);
            holder.ivIcon = (RoundedImageView) view.findViewById(R.id.i_buy_electric_child_ivIcon);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.tvTitle.setText(menuItems.get(i).getItemName());
        holder.ivIcon.setImageDrawable(menuItems.get(i).getImgResID());
        return view;
    }

    private class Holder {
        TextView tvTitle;
        RoundedImageView ivIcon;
    }
}
