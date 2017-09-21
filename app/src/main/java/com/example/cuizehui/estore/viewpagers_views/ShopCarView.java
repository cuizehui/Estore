package com.example.cuizehui.estore.viewpagers_views;

import android.database.Cursor;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.ProductMessageActivity;
import com.example.cuizehui.estore.adapter.ShopCarRecycleViewAdapter;
import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.ShopCarData;
import com.example.cuizehui.estore.entity.User;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by cuizehui on 17-9-14.
 */

public class ShopCarView extends  BasePagerView {
    
   public  RecyclerView mRecyclerView;

    ShopCarRecycleViewAdapter shopCarRecycleViewAdapter;
    public ShopCarView(MainActivity mainActivity) {
        super(mainActivity);

        Log.d("shopCarview","绘制");
        User user= MyApplication.getInstance(mainActivity).getUser();

        if(user!=null) {
            initDate();
            initView();
            initEvent();
        }else {

        }


    }

    @Override
    public void initView() {
        super.initView();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        View shopcarview=inflater.inflate(R.layout.main_viewpager_shopcar,null);
        mRecyclerView=shopcarview.findViewById(R.id.shopcar_recyclerView);

        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        //设置adapter

        mRecyclerView.setAdapter(shopCarRecycleViewAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //   mRecyclerView.addItemDecoration(new DividerItemDecoration(mainActivity, DividerItemDecoration.HORIZONTAL_LIST));
        basePager_fl.addView(shopcarview);

    }


    //初始化购物车时执行这个方法
   //回调后依然执行这个方法
    @Override
    public void initDate() {
        super.initDate();
        User user= MyApplication.getInstance(mainActivity).getUser();
        Log.d("shopCarview","initdata1");

        //判断是否在登录状态
        if(user!=null){
            //拉取数据库 购物车商品信息
            Log.d("shopCarview","initdata2");
            UserDb userDb= MyApplication.getInstance(mainActivity).getUserDatedb();
            Cursor cursor= userDb.selectShopCarMessage(user.getAccount());

            ArrayList<ShopCarData> shopCarDatas = userDb.passShopCarMessage(cursor);
            Log.d("shopCarViewdata size",shopCarDatas.size()+"");
            //如果是0 说明没有购物车信息
            if(shopCarDatas.size()==0){
                //设置空数据
                ArrayList<ShopCarData> shopCarnull =new ArrayList<>();
                shopCarRecycleViewAdapter=new ShopCarRecycleViewAdapter(mainActivity,shopCarnull);

            }
            else {
                //获取真数据
                Log.d("",shopCarDatas.get(0).getProducename());
                shopCarRecycleViewAdapter=new ShopCarRecycleViewAdapter(mainActivity,shopCarDatas);

            }



        }
        else {
            ArrayList<ShopCarData> shopCarnull =new ArrayList<>();
            shopCarRecycleViewAdapter=new ShopCarRecycleViewAdapter(mainActivity,shopCarnull);
        }




        //recycleviewAdapter 生成，在这里 动态生成 同类商品个数view

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
