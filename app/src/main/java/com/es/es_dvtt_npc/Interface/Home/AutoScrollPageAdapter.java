package com.es.es_dvtt_npc.Interface.Home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.es.es_dvtt_npc.R;

/**
 * Created by My_PC on 10/31/2017.
 */

public class AutoScrollPageAdapter extends PagerAdapter {

    private Context context;
    private Integer[] imagesArray;

    public AutoScrollPageAdapter(Context context,Integer[] imagesArray){
        this.context = context;
        this.imagesArray = imagesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewItem = inflater.inflate(R.layout.item_scroll_pager, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.i_scroll_pager_Image);
        imageView.setImageResource(imagesArray[position]);
        ((ViewPager)container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imagesArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }
}
