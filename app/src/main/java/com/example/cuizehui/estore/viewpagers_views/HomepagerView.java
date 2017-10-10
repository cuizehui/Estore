package com.example.cuizehui.estore.viewpagers_views;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.ProductMessageActivity;
import com.example.cuizehui.estore.adapter.HomeViewpagerLVAdapter;
import com.example.cuizehui.estore.entity.ShopDaTa;
import com.example.cuizehui.estore.interfaces.DaggerHomePagerViewComponent;
import com.example.cuizehui.estore.interfaces.HomePagerViewComponent;
import com.example.cuizehui.estore.module.HomepagerViewModule;
import com.example.cuizehui.estoredataservice.IDataAidlInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by cuizehui on 17-9-14.
 */

public class HomepagerView extends BasePagerView {
    private IDataAidlInterface mservice;


    //商品GV
    public GridView home_GridView;
    public TextView textView;
    private HomeViewpagerLVAdapter homeViewpagerLVAdapter1=null;
    @Inject   HomeViewpagerLVAdapter homeViewpagerLVAdapter;

    public HomepagerView(MainActivity mainActivity) {
        super(mainActivity);
    }




    @Override
    protected void initDate() {
        super.initDate();

        HomePagerViewComponent homePagerViewComponent= DaggerHomePagerViewComponent
                .builder()
                .homepagerViewModule(new HomepagerViewModule(mainActivity))
                .build();
        homePagerViewComponent.inject(this);


        //此方法最好在子线程中进行！
        provideServiceData();



    }

    @Override
    protected void initView() {
         super.initView();

        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        //绑定任意view
        View homeview=inflater.inflate(R.layout.main_viewpager_home,null);

        home_GridView=  homeview.findViewById(R.id.home_pager_gv);


      // 判断有无网络数据
        if(homeViewpagerLVAdapter1!=null){
            home_GridView.setAdapter(homeViewpagerLVAdapter1);

        }
        else {
            home_GridView.setAdapter(homeViewpagerLVAdapter);
        }

        basePager_fl.addView(homeview);

    }


    @Override
    protected void initEvent() {
        super.initEvent();
       home_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // 跳转  至相应界面
               ShopDaTa shopDaTa;

               //如果有服务器数据则
               if(homeViewpagerLVAdapter1!=null){
                    shopDaTa =homeViewpagerLVAdapter1.getShopDATAsarrayList().get(i);
               }
               else {
                    shopDaTa =homeViewpagerLVAdapter.getShopDATAsarrayList().get(i);
               }



               Intent intent=new Intent(mainActivity, ProductMessageActivity.class);

               Bundle mBundle = new Bundle();
               mBundle.putSerializable("shopData",shopDaTa);
               intent.putExtras(mBundle);
               //请求码是2
               mainActivity.startActivityForResult(intent,true,2);
           }
       });

    }

    public void  provideServiceData(){
        //调用远程服务
        Intent intent =new Intent();
        intent.setAction("com.cuizehui.dataservice");
        intent.setPackage("com.example.cuizehui.estoredataservice");
        mainActivity.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mservice = IDataAidlInterface.Stub.asInterface(iBinder);

                //操作远程服务


                List<com.example.cuizehui.estoredataservice.ShopDaTa> shopDaTas;
                try {
                    shopDaTas=mservice.getShopDaTaList();

                    ArrayList<ShopDaTa> arrayList=new ArrayList<>();

                    for (int i=0;i<shopDaTas.size();i++){

                        ShopDaTa shopDaTa=new ShopDaTa();
                        shopDaTa.setPicadress(shopDaTas.get(i).getPicadress());
                        shopDaTa.setPrice(shopDaTas.get(i).getPrice());
                        shopDaTa.setProductName(shopDaTas.get(i).getProductName());
                        shopDaTa.setShopName(shopDaTas.get(i).getShopName());
                        shopDaTa.setProductdic(shopDaTas.get(i).getProductdic());
                        shopDaTa.setMailprice(shopDaTas.get(i).getMailprice());
                         byte[] bytes;
                        if(shopDaTas.get(i).getBitmaps()!=null){
                             bytes=shopDaTas.get(i).getBitmaps();
                            Log.d("bitmap_log",bytes.length+"!null");
                            shopDaTa.setBitmaps(bytes);
                        }
                        else {
                            Log.d("bitmap","null");
                        }
                        Log.d("context",shopDaTas.get(i).getProductName()+"");
                        arrayList.add(shopDaTa);

                    }
                    homeViewpagerLVAdapter1=new HomeViewpagerLVAdapter(arrayList,mainActivity);

                    Toast.makeText(mainActivity,shopDaTas.size()+""+":"+arrayList.size(),Toast.LENGTH_SHORT).show();


                    //refrush
                    initView();
                    initEvent();

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                Log.d("绑定远程服务","成功");

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("绑定失败","失败");
            }
        }, mainActivity.BIND_AUTO_CREATE);

    }

    //回调解绑


}
