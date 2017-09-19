package com.example.cuizehui.estore.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuizehui.estore.viewpagers_views.BasePagerView;

import java.util.ArrayList;

/**
 * Created by cuizehui on 17-9-14.
 */

public class MainViewPagerAdapter extends PagerAdapter {


    public ArrayList<BasePagerView> getPagerViewArrayList() {
        return pagerViewArrayList;
    }

    ArrayList<BasePagerView>  pagerViewArrayList;
    public MainViewPagerAdapter(ArrayList<BasePagerView> pagerViewsList) {
        this.pagerViewArrayList=pagerViewsList;

    }
    @Override
    public int getCount() {
        return pagerViewArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=pagerViewArrayList.get(position).getRoot();
        container.addView(view);
        return  view;
    }
    @Override
    public int getItemPosition(Object object)   {

        Log.d("设置","position");
            return POSITION_NONE;

    }


}
