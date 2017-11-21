package com.es.es_dvtt_npc.Interface.ElectricitySupply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.es.es_dvtt_npc.Data.Object.HoDungChungEntity;
import com.es.es_dvtt_npc.Interface.Home.MenuItem;
import com.es.es_dvtt_npc.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by My_PC on 10/31/2017.
 */

public class InfoDungChungAdapter extends BaseAdapter {
    Context c;
    ArrayList<HoDungChungEntity> hoDungChungEntities;
    private LayoutInflater inflater;

    public InfoDungChungAdapter(Context context, ArrayList<HoDungChungEntity> hoDungChungEntities) {
        this.c = context;
        this.hoDungChungEntities = hoDungChungEntities;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return hoDungChungEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return hoDungChungEntities.get(i);
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
            view = inflater.inflate(R.layout.item_info_ho_dung_chung, null);
            holder.tvName = (TextView) view.findViewById(R.id.i_info_ho_dung_chung_tvName);
            holder.tvPhone = (TextView) view.findViewById(R.id.i_info_ho_dung_chung_tvPhone);
            holder.tvAddress = (TextView) view.findViewById(R.id.i_info_ho_dung_chung_tvAddress);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.tvName.setText(hoDungChungEntities.get(i).getTEN_CHUHO());
        holder.tvPhone.setText(hoDungChungEntities.get(i).getSDT_LIENHE());
        holder.tvAddress.setText(hoDungChungEntities.get(i).getDIA_CHI());
        return view;
    }

    private class Holder {
        TextView tvName,tvPhone,tvAddress;
    }
}
