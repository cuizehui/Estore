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
        shopDaTa1.setProductName("商店A商品1");
        shopDaTa1.setPicadress("19.1");
        shopDaTa1.setShopName("商店A");
        shopDaTa1.setPrice("图片地址1");

        ShopDaTa shopDaTa2=new ShopDaTa();
        shopDaTa2.setProductName("商店B商品2");
        shopDaTa2.setPicadress("19.2");
        shopDaTa2.setShopName("商店B");
        shopDaTa2.setPrice("图片地址2");



        ShopDaTa shopDaTa3=new ShopDaTa();
        shopDaTa3.setProductName("商店A商品3");
        shopDaTa3.setPicadress("19.3");
        shopDaTa3.setShopName("商店A");
        shopDaTa3.setPrice("图片地址3");



        ShopDaTa shopDaTa4=new ShopDaTa();
        shopDaTa4.setProductName("商店C商品4");
        shopDaTa4.setPicadress("19.4");
        shopDaTa4.setShopName("商店C");
        shopDaTa4.setPrice("图片地址4");


        ShopDaTa shopDaTa5=new ShopDaTa();
        shopDaTa5.setProductName("商店B商品5");
        shopDaTa5.setPicadress("19.5");
        shopDaTa5.setShopName("商店B");
        shopDaTa5.setPrice("图片地址5");

        arrayList.add(shopDaTa2);
        arrayList.add(shopDaTa1);
        arrayList.add(shopDaTa3);
        arrayList.add(shopDaTa4);
        arrayList.add(shopDaTa5);


        return  arrayList ;
    }

    @Provides

    public HomeViewpagerLVAdapter provideLVadpter(ArrayList<ShopDaTa> arrayList){
        Log.d("list.size:",arrayList.size()+"");
        HomeViewpagerLVAdapter homeViewpagerLVAdapter=new HomeViewpagerLVAdapter(arrayList,mainActivity);

        return  homeViewpagerLVAdapter;
    }
}
