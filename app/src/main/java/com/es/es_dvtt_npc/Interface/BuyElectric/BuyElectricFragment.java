package com.es.es_dvtt_npc.Interface.BuyElectric;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Interface.ElectricitySupply.ElectricitySupplyFragment;
import com.es.es_dvtt_npc.Interface.Home.MenuItem;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PhuongVV on 10/31/2017.
 */

public class BuyElectricFragment extends BaseFragment {
    ExpandableListView lvBuyElectric;
    String[] title;
    TypedArray image;
    List<String> titleGroup;
    HashMap<String,ArrayList<MenuItem>> hashMap;
    BuyElectricAdapter buyElectricAdapter;
    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buy_electric_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hashMap = new HashMap<>();
        titleGroup = new ArrayList<>();
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View v) {
        lvBuyElectric = (ExpandableListView) v.findViewById(R.id.f_buy_electric_ExList);
        image = getResources().obtainTypedArray(R.array.Iconmuadienhaap);
        title = getResources().getStringArray(R.array.Titlemuadienhaap);
        hashMap.put(getString(R.string.capdienhaap), Common.setItem(title,image));
        image = getResources().obtainTypedArray(R.array.Iconmuadientrungap);
        title = getResources().getStringArray(R.array.Titlemuadientrungap);
        hashMap.put(getString(R.string.capdientrungap),Common.setItem(title,image));
        titleGroup.add(getString(R.string.capdienhaap));
        titleGroup.add(getString(R.string.capdientrungap));
        buyElectricAdapter = new BuyElectricAdapter(getContext(),titleGroup,hashMap);
        lvBuyElectric.setAdapter(buyElectricAdapter);
        lvBuyElectric.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                ElectricitySupplyFragment electricitySupplyFragment = new ElectricitySupplyFragment();
                mNativeManager.addFragment(electricitySupplyFragment,"Supply");
                return false;
            }
        });
    }

//endregion

    //region Navigation


//endregion

    //region Control Action

//endregion

    //region Control Delegate


//endregion

    //region API


//endregion

    //region Helper

//endregion
}
