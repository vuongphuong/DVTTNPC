package com.es.es_dvtt_npc.Interface.Home;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.es.es_dvtt_npc.Base.ActionbarInfo;
import com.es.es_dvtt_npc.Base.BaseFragment;
import com.es.es_dvtt_npc.BaseAPI.BaseResponse;
import com.es.es_dvtt_npc.BaseAPI.Reponse.DVDChinhResponse;
import com.es.es_dvtt_npc.BaseAPI.Request.DVDChinhRequest;
import com.es.es_dvtt_npc.BaseAPI.ResponseListener;
import com.es.es_dvtt_npc.BaseAPI.errors.AuthFailureError;
import com.es.es_dvtt_npc.BaseAPI.errors.ParserError;
import com.es.es_dvtt_npc.BaseAPI.errors.ServerError;
import com.es.es_dvtt_npc.Helper.AppAlertDialog;
import com.es.es_dvtt_npc.Helper.AppLog;
import com.es.es_dvtt_npc.Helper.AutoScrollViewPager;
import com.es.es_dvtt_npc.Helper.Common;
import com.es.es_dvtt_npc.Helper.Singleton;
import com.es.es_dvtt_npc.Interface.BuyElectric.BuyElectricFragment;
import com.es.es_dvtt_npc.Interface.Customer.CustomerFragment;
import com.es.es_dvtt_npc.Interface.Login.LoginFragment;
import com.es.es_dvtt_npc.Interface.Request.RequestFragment;
import com.es.es_dvtt_npc.Interface.Search.SearchFragment;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by My_PC on 10/31/2017.
 */

public class HomeFragment extends BaseFragment implements ResponseListener,ActionbarInfo {
    private AutoScrollViewPager autoScrollViewPager;
    private ImageView ivHotLine;
    private GridView grMenu;
    String[] title;
    TypedArray image;
    MenuItem drawerItem;
    ArrayList<MenuItem> arrDrawerItems;
    Integer[] imageId = {R.drawable.image_aa, R.drawable.image_bb, R.drawable.image_xx, R.drawable.image_zz};

    //region Activity Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);

    }

//endregion

    //region Init View
    public void initialize(View view) {
        autoScrollViewPager = (AutoScrollViewPager) view.findViewById(R.id.f_home_AutoScrollViewPager);
        ivHotLine = (ImageView) view.findViewById(R.id.f_home_HotLine);
        grMenu = (GridView) view.findViewById(R.id.f_home_GridView);
        image = getResources().obtainTypedArray(R.array.icon);
        title = getResources().getStringArray(R.array.title);

        arrDrawerItems = new ArrayList<MenuItem>();
        for (int i = 0; i < title.length; i++) {
            drawerItem = new MenuItem();
            drawerItem.setItemName(title[i]);
            drawerItem.setImgResID(image.getDrawable(i));
            arrDrawerItems.add(drawerItem);
        }
        ivHotLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.CallNumber(getActivity());
            }
        });
        final MenuAdapter menuAdapter = new MenuAdapter(getContext(), arrDrawerItems);
        grMenu.setAdapter(menuAdapter);

        grMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemClick(i);
            }
        });
        autoScrollViewPager.startAutoScroll();
        autoScrollViewPager.setInterval(3000);
        autoScrollViewPager.setCycle(true);
        autoScrollViewPager.setStopScrollWhenTouch(true);

        PagerAdapter adapter = new AutoScrollPageAdapter(getContext(), imageId);
        autoScrollViewPager.setAdapter(adapter);

    }

//endregion

    //region Navigation
    @Override
    public String getActionbarTitle() {
        return getString(R.string.home);
    }

//endregion

    //region Control Action
    void itemClick(int i) {
        switch (i) {
            case 0:
                BuyElectricFragment buyElectricFragment = new BuyElectricFragment();
                mNativeManager.addFragment(buyElectricFragment, "Buy Electric");
                break;
            case 1:
                if (!Singleton.getInstance().isLogin) {
                    LoginFragment loginFragment = new LoginFragment();
                    mNativeManager.addFragment(loginFragment, "Login");
                } else {
                    CustomerFragment customerFragment = new CustomerFragment();
                    mNativeManager.addFragment(customerFragment,"Customer");
                }
                break;
            case 2:
                SearchFragment searchFragment = new SearchFragment();
                mNativeManager.addFragment(searchFragment, "Search");
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                RequestFragment requestFragment = new RequestFragment();
                mNativeManager.addFragment(requestFragment, "Request");
                break;
        }
    }

//endregion

    //region Control Delegate


//endregion

    //region API

    @Override
    public BaseResponse parse(int requestId, Call call, Response response) throws Exception {
        return new DVDChinhResponse(response);
    }

    @Override
    public void onResponse(int requestId, BaseResponse response) {
        if (requestId == Common.REQUEST_DVDCHINH) {
            DVDChinhResponse dvdChinhResponse = (DVDChinhResponse) response;
        }
    }

    @Override
    public void onError(int requestId, Exception e) {
        if (e instanceof ServerError) {
            ServerError serverError = (ServerError) e;
            AppLog.d(getString(R.string.app_name), serverError.getError().getErrorDescription());
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, getString(R.string.error), Color.WHITE, getString(R.string.common_ok), Color.RED);
        } else if (e instanceof ParserError) {
            AppLog.d(getString(R.string.app_name), "parser data error request: " + requestId);
        } else if (e instanceof AuthFailureError) {
            AppLog.d(getString(R.string.app_name), "AuthFailure error request: " + requestId);
        } else {
            AppLog.d(getString(R.string.app_name), "unKnown error request: " + requestId);
            AppAlertDialog.showAlertDialogGreen(getContext(), "Lỗi", Color.RED, "Vui lòng xem lại kết nối đến service", Color.WHITE, getString(R.string.common_ok), Color.RED);
        }
    }
//endregion

    //region Helper
//endregion
}
