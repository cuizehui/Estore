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
        shopDaTa1.setProductName("商品1");
        shopDaTa1.setPicadress("19.5");
        shopDaTa1.setShopName("商店A");
        shopDaTa1.setPrice("图片地址a");

        ShopDaTa shopDaTa2=new ShopDaTa();
        shopDaTa2.setProductName("商品2");
        shopDaTa2.setPicadress("19.6");
        shopDaTa2.setShopName("商店b");
        shopDaTa2.setPrice("图片地址b");

        arrayList.add(shopDaTa2);

        arrayList.add(shopDaTa1);



        return  arrayList ;
    }

    @Provides

    public HomeViewpagerLVAdapter provideLVadpter(ArrayList<ShopDaTa> arrayList){
        Log.d("list.size:",arrayList.size()+"");
        HomeViewpagerLVAdapter homeViewpagerLVAdapter=new HomeViewpagerLVAdapter(arrayList,mainActivity);

        return  homeViewpagerLVAdapter;
    }
}
