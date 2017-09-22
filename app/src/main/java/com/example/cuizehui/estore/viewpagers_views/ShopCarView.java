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

    public UserDb userDb;
    public ArrayList<ShopCarData> shopCarDatas;
    public User user;

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
       user= MyApplication.getInstance(mainActivity).getUser();
        Log.d("shopCarview","initdata1");

        //判断是否在登录状态
        if(user!=null){
            //拉取数据库 购物车商品信息
            Log.d("shopCarview","initdata2");

            //第一次登录如何处理这个view!!!!

             userDb= MyApplication.getInstance(mainActivity).getUserDatedb();

            //返回 按商店排序的数据集合

            Cursor cursor= userDb.selectPdOrderByShopName(user.getAccount());

            shopCarDatas = userDb.passShopCarMessage(cursor);
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
                isSelectFirst(shopCarDatas);
                shopCarRecycleViewAdapter=new ShopCarRecycleViewAdapter(mainActivity,shopCarDatas);

            }



        }
        else {
            ArrayList<ShopCarData> shopCarnull =new ArrayList<>();
            shopCarRecycleViewAdapter=new ShopCarRecycleViewAdapter(mainActivity,shopCarnull);
        }



    }

    @Override
    protected void initEvent() {
        //全选事件 和结算事件
        super.initEvent();
        shopCarRecycleViewAdapter.setOnEditClickListenter(new ShopCarRecycleViewAdapter.OnEditClickListenter() {
            @Override
            public void onEditClick(int postion, String productname, int count) {

                Log.e("修改数量接口回调","111");
                userDb.updataSCProductnunber(user.getAccount(),productname,count+"");
            }
        });
    }


    /**
     * 给每个商品添加头属性
     * @param list
     */
    public static void isSelectFirst(ArrayList<ShopCarData> list){
        if(list.size() > 0) {
            //头个商品一定属于它所在商铺的第一个位置，isFirst标记为1.
            list.get(0).setIsFirst(1);

            for (int i = 1; i < list.size(); i++) {
                //每个商品跟它前一个商品比较，如果Shopid相同isFirst则标记为2，
                //如果Shopid不同，isFirst标记为1.
                if (list.get(i).getShopname().equals(list.get(i - 1).getShopname().toString())) {
                    list.get(i).setIsFirst(2);
                } else {
                    list.get(i).setIsFirst(1);
                }
            }
        }
    }
}
