package com.example.cuizehui.estore.viewpagers_views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.ProductMessageActivity;
import com.example.cuizehui.estore.adapter.HomeViewpagerLVAdapter;
import com.example.cuizehui.estore.entity.ShopDaTa;
import com.example.cuizehui.estore.interfaces.DaggerHomePagerViewComponent;
import com.example.cuizehui.estore.interfaces.HomePagerViewComponent;
import com.example.cuizehui.estore.module.HomepagerViewModule;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-9-14.
 */

public class HomepagerView extends BasePagerView {

    //商品GV
    public GridView home_GridView;
    public TextView textView;

    @Inject   HomeViewpagerLVAdapter homeViewpagerLVAdapter;

    public HomepagerView(MainActivity mainActivity) {
        super(mainActivity);
    }


    @Override
    protected void initView() {
         super.initView();

        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        //绑定任意view
        View homeview=inflater.inflate(R.layout.main_viewpager_home,null);

        home_GridView=  homeview.findViewById(R.id.home_pager_gv);

        home_GridView.setAdapter(homeViewpagerLVAdapter);


        basePager_fl.addView(homeview);


    }

    @Override
    protected void initDate() {
        super.initDate();

        HomePagerViewComponent homePagerViewComponent= DaggerHomePagerViewComponent
                .builder()
                .homepagerViewModule(new HomepagerViewModule(mainActivity))
                .build();
        homePagerViewComponent.inject(this);



    }

    @Override
    protected void initEvent() {
        super.initEvent();
       home_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // 跳转  至相应界面
                  ShopDaTa shopDaTa =homeViewpagerLVAdapter.getShopDATAsarrayList().get(i);
               Intent intent=new Intent(mainActivity, ProductMessageActivity.class);

               Bundle mBundle = new Bundle();
               mBundle.putSerializable("shopData",shopDaTa);
               intent.putExtras(mBundle);
               //请求码是2
               mainActivity.startActivityForResult(intent,true,2);
           }
       });

    }
}
