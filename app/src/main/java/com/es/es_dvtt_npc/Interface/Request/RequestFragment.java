package com.es.es_dvtt_npc.Interface.Request;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.Singleton;
import com.es.es_dvtt_npc.Interface.ChangCapacity.ChangeCapacityFragment;
import com.es.es_dvtt_npc.Interface.ChangePersonSingingContract.ChangePersonSingingContractFragment;
import com.es.es_dvtt_npc.Interface.ChangePosition.ChangePositionFragment;
import com.es.es_dvtt_npc.Interface.ChangePurpose.ChangePurposeFragment;
import com.es.es_dvtt_npc.Interface.ChangeTheNorm.ChangeTheNormFragment;
import com.es.es_dvtt_npc.Interface.Login.LoginFragment;
import com.es.es_dvtt_npc.Interface.Search.SearchAdapter;
import com.es.es_dvtt_npc.R;

/**
 * Created by My_PC on 11/1/2017.
 */

public class RequestFragment extends BaseFragment implements ActionbarInfo {
    private ListView lvItem;
    String[] title;
    TypedArray image;

    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.request_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View v) {
        lvItem = (ListView) v.findViewById(R.id.f_request_lvItem);
        image = getResources().obtainTypedArray(R.array.IconYeuCauDichVu);
        title = getResources().getStringArray(R.array.TitleYeuCauDichVu);
        SearchAdapter searchAdapter = new SearchAdapter(getContext(), Common.setItem(title, image));
        lvItem.setAdapter(searchAdapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mNativeManager.addFragment(ChangeCapacityFragment.newInstance(Common.YEU_CAU_THAY_DOI_CONG_SUAT), "CongSuat");
                        break;
                    case 1:
                        mNativeManager.addFragment(ChangeCapacityFragment.newInstance(Common.YEU_CAU_THAY_DOI_CONG_SUAT_LON_40), "CongSuat");
                        break;
                    case 2:
                        mNativeManager.addFragment(ChangePositionFragment.newInstance(Common.YEU_CAU_THAY_DOI_VI_TRI), "ViTri");
                        break;
                    case 3:
                        ChangePurposeFragment changePurposeFragment = new ChangePurposeFragment();
                        mNativeManager.addFragment(changePurposeFragment, "MucDich");
                        break;
                    case 4:
                        ChangeTheNormFragment changeTheNormFragment = new ChangeTheNormFragment();
                        mNativeManager.addFragment(changeTheNormFragment, "MucDich");
                        break;
                    case 5:
                        mNativeManager.addFragment(ChangePositionFragment.newInstance(Common.YEU_CAU_THAY_DOI_CHU_THE), "ChuThe");
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        mNativeManager.addFragment(ChangePositionFragment.newInstance(Common.YEU_CAU_CAP_DIEN_TRO_LAI), "CapDienTroLai");
                        break;
                    case 9:
                        mNativeManager.addFragment(ChangePositionFragment.newInstance(Common.YEU_CAU_CAP_DIEN_TRO_LAI_CO_QUAN_NHA_NUOC), "CapDienTroLaiCoQuan");
                        break;
                    case 12:
                        mNativeManager.addFragment(ChangePositionFragment.newInstance(Common.YEU_CAU_GIA_HAN_HOP_DONG), "GiaHan");
                        break;
                    case 13:
                        mNativeManager.addFragment(ChangePositionFragment.newInstance(Common.YEU_CAU_CHAM_DUT_HOP_DONG), "ChamDut");
                        break;
                }
            }
        });
    }


//endregion

    //region Navigation
    @Override
    public String getActionbarTitle() {
        return getString(R.string.dichvutrongquatrinh);
    }

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
