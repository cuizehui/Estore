package com.example.cuizehui.estore.viewpagers_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;

import butterknife.BindView;

/**
 * Created by cuizehui on 17-9-14.
 */

public class BasePagerView {

    protected TextView main_viewpager_tv;

    protected FrameLayout basePager_fl;

    public  MainActivity mainActivity;

    protected   View rootview;


    public BasePagerView(MainActivity mainActivity) {
        super();

        this.mainActivity=mainActivity;

        initDate();
        initView();
        initEvent();
    }

    protected  void initView(){
       LayoutInflater  layoutInflater=LayoutInflater.from(mainActivity);
        rootview = layoutInflater.inflate(R.layout.main_viewpager_base,null);
        basePager_fl=rootview.findViewById(R.id.base_viewpager_fl);
    }
    protected  void initDate(){

    }
    protected  void initEvent(){

    }
    public View getRoot(){
        return rootview;
    }
}
