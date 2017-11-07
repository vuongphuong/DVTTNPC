package com.es.es_dvtt_npc.Interface.Search;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Interface.BuyElectric.BuyElectricAdapter;
import com.es.es_dvtt_npc.Interface.Home.MenuAdapter;
import com.es.es_dvtt_npc.Interface.Home.MenuItem;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by My_PC on 11/1/2017.
 */

public class SearchFragment extends BaseFragment {
    private ListView lvItem;
    String[] title;
    TypedArray image;
    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View v) {
        lvItem = (ListView) v.findViewById(R.id.f_search_lvItem);
        image = getResources().obtainTypedArray(R.array.IconTraCuuThongTin);
        title = getResources().getStringArray(R.array.TitleTraCuuThongTin);
        SearchAdapter searchAdapter = new SearchAdapter(getContext(), Common.setItem(title,image));
        lvItem.setAdapter(searchAdapter);
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
