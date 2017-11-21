package com.es.es_dvtt_npc.Interface.BuyElectric;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Interface.ElectricityOutSide.ElectricityOutSideFragment;
import com.es.es_dvtt_npc.Interface.ElectricitySupply.ElectricitySupplyFragment;
import com.es.es_dvtt_npc.Interface.Home.MenuItem;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PhuongVV on 10/31/2017.
 */

public class BuyElectricFragment extends BaseFragment implements ActionbarInfo {
    ExpandableListView lvBuyElectric;
    String[] title;
    TypedArray image;
    List<String> titleGroup;
    HashMap<String, ArrayList<MenuItem>> hashMap;
    BuyElectricAdapter buyElectricAdapter;

    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
        hashMap.put(getString(R.string.capdienhaap), Common.setItem(title, image));
        image = getResources().obtainTypedArray(R.array.Iconmuadientrungap);
        title = getResources().getStringArray(R.array.Titlemuadientrungap);
        hashMap.put(getString(R.string.capdientrungap), Common.setItem(title, image));
        titleGroup.add(getString(R.string.capdienhaap));
        titleGroup.add(getString(R.string.capdientrungap));
        buyElectricAdapter = new BuyElectricAdapter(getContext(), titleGroup, hashMap);
        lvBuyElectric.setAdapter(buyElectricAdapter);
        lvBuyElectric.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (i == 0) {
                    switch (i1) {
                        case 0:
                            mNativeManager.addFragment(ElectricitySupplyFragment.newInstance(Common.CAP_MOI_SINH_HOAT), "Supply");
                            break;
                        case 1:
                            mNativeManager.addFragment(ElectricitySupplyFragment.newInstance(Common.CAP_MOI_DUNG_CHUNG), "Supply");
                            break;
                        case 2:
                            mNativeManager.addFragment(ElectricitySupplyFragment.newInstance(Common.TACH_CONG_TO), "Supply");
                            break;
                        case 3:
                            mNativeManager.addFragment(ElectricityOutSideFragment.newInstance(Common.NGOAI_SINH_HOAT_NHO_HON_40),"OutSide");
                            break;
                        case 4:
                            mNativeManager.addFragment(ElectricityOutSideFragment.newInstance(Common.NGOAI_SINH_HOAT_LON_HON_40),"OutSide");
                            break;
                        case 5:
                            mNativeManager.addFragment(ElectricityOutSideFragment.newInstance(Common.NGOAI_SINH_HOAT_NGAN_HAN),"OutSide");
                            break;
                    }
                }else if (i==1){
                    switch (i1){
                        case 0:
                            mNativeManager.addFragment(ElectricitySupplyFragment.newInstance(Common.CAP_MOI_SINH_HOAT_TRUNG_AP), "Supply");
                            break;
                        case 1:
                            mNativeManager.addFragment(ElectricityOutSideFragment.newInstance(Common.NGOAI_SINH_HOAT_TRUNG_AP_NHO_2000), "OutSide");
                            break;
                        case 2:
                            mNativeManager.addFragment(ElectricityOutSideFragment.newInstance(Common.NGOAI_SINH_HOAT_TRUNG_AP_LON_2000), "OutSide");
                            break;
                        case 3:
                            mNativeManager.addFragment(ElectricityOutSideFragment.newInstance(Common.MUA_BUON_DIEN_NONG_THON), "OutSide");
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public String getActionbarTitle() {
        return getString(R.string.dichvucapmoi);
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
