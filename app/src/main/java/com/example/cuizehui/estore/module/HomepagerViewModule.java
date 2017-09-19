package com.example.cuizehui.estore.module;

import android.content.Context;
import android.util.Log;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.adapter.HomeViewpagerLVAdapter;
import com.example.cuizehui.estore.entity.ShopDaTa;
import com.example.cuizehui.estore.viewpagers_views.HomepagerView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cuizehui on 17-9-19.
 */
@Module

public class HomepagerViewModule {
    public MainActivity mainActivity;
    public HomepagerViewModule(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }



    @Provides
    public ArrayList<ShopDaTa> provideShopDaTa(){
        ArrayList<ShopDaTa> arrayList=new ArrayList<>();
        //请求拉取数据放到 arraylist中
        //模拟数据
        ShopDaTa shopDaTa1=new ShopDaTa();
        shopDaTa1.setProductName("dfsfd");

        ShopDaTa shopDaTa2=new ShopDaTa();
        shopDaTa1.setProductName("df43fd");
        arrayList.add(shopDaTa1);
        arrayList.add(shopDaTa2);



        return  arrayList ;
    }

    @Provides

    public HomeViewpagerLVAdapter provideLVadpter(ArrayList<ShopDaTa> arrayList){
        Log.d("list.size:",arrayList.size()+"");
        HomeViewpagerLVAdapter homeViewpagerLVAdapter=new HomeViewpagerLVAdapter(arrayList,mainActivity);

        return  homeViewpagerLVAdapter;
    }
}
