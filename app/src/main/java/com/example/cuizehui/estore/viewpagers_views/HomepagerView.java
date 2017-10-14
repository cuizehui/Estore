package com.example.cuizehui.estore.viewpagers_views;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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
import com.example.cuizehui.estore.activity.ShopMessageAcitivity;
import com.example.cuizehui.estore.adapter.HomeViewpagerLVAdapter;
import com.example.cuizehui.estore.entity.ShopDaTa;
import com.example.cuizehui.estore.interfaces.DaggerHomePagerViewComponent;
import com.example.cuizehui.estore.interfaces.HomePagerViewComponent;
import com.example.cuizehui.estore.module.HomepagerViewModule;
import com.example.cuizehui.estore.uitls.ThreadPoolFactory;
import com.example.cuizehui.estoredataservice.IDataAidlInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by cuizehui on 17-9-14.
 */

public class HomepagerView extends BasePagerView {

    private IDataAidlInterface mservice;
    public   ServiceConnection connection;
    //商品GV
    public GridView home_GridView;
    public TextView textView;

    @Inject   ArrayList<ShopDaTa> thereshopDaTas;


    private HomeViewpagerLVAdapter homeViewpagerLVAdapter;
    private TextView qianggou_tv;
    private TextView mingpindian_tv;


    public HomepagerView(MainActivity mainActivity) {
        super(mainActivity);
    }

    //不重绘？

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 1:
                   homeViewpagerLVAdapter.notifyDataSetChanged();
                   home_GridView.setAdapter(homeViewpagerLVAdapter);

                   Log.d("!!" , ""+homeViewpagerLVAdapter.getShopDATAsarrayList().get(0).getProductName());
                   Log.d("通知重绘","true");
                   break;
           }

        }

    };




    @Override
    protected void initDate() {
        super.initDate();

        HomePagerViewComponent homePagerViewComponent= DaggerHomePagerViewComponent
                .builder()
                .homepagerViewModule(new HomepagerViewModule(mainActivity))
                .build();
        homePagerViewComponent.inject(this);

        homeViewpagerLVAdapter=new HomeViewpagerLVAdapter(thereshopDaTas,mainActivity);


    }

    @Override
    protected void initView() {
         super.initView();

        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        //绑定任意view
        View homeview=inflater.inflate(R.layout.main_viewpager_home,null);
        home_GridView=  homeview.findViewById(R.id.home_pager_gv);

        qianggou_tv=homeview.findViewById(R.id.tv_qianggou);
        mingpindian_tv=homeview.findViewById(R.id.tv_mingpindian);

         home_GridView.setAdapter(homeViewpagerLVAdapter);
        basePager_fl.addView(homeview);

        Log.d("theredatassize ::",""+thereshopDaTas.size());
        //获取服务器数据
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                provideServiceData();
            }
        };

        ThreadPoolFactory.getNormalPool().execute(runnable);


    }


    @Override
    protected void initEvent() {
        super.initEvent();
       home_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // 跳转  至相应界面
               ShopDaTa shopDaTa=homeViewpagerLVAdapter.getShopDATAsarrayList().get(i);
               Intent intent=new Intent(mainActivity, ProductMessageActivity.class);
               Bundle mBundle = new Bundle();
               mBundle.putSerializable("shopData",shopDaTa);
               intent.putExtras(mBundle);
               //请求码是2
               mainActivity.startActivityForResult(intent,true,2);
           }
       });


        qianggou_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchViewpager(1);
            }
        });
        mingpindian_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainActivity, ShopMessageAcitivity.class);
                mainActivity.startActivity(intent);
            }
        });
    }


    /**|
     * 会出现绑定失败  多个线程操作一个 服务的状态
     *
     * 由于在线程中执行  执行结束后可能没赶上SetAdapter
     */
    public void  provideServiceData(){
        //调用远程服务
        Intent intent =new Intent();
        intent.setAction("com.cuizehui.dataservice");
        intent.setPackage("com.example.cuizehui.estoredataservice");
        connection=  new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mservice = IDataAidlInterface.Stub.asInterface(iBinder);
                //操作远程服务
                Log.d("开始执行绑定远程服务","start");
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
                    homeViewpagerLVAdapter.setShopDATAsarrayList(arrayList);
                    Toast.makeText(mainActivity,shopDaTas.size()+""+":"+arrayList.size(),Toast.LENGTH_SHORT).show();


                    Message ms= handler.obtainMessage();
                    ms.what = 1;
                    ms.sendToTarget();

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                Log.d("绑定远程服务","成功");

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("绑定失败","失败");

                homeViewpagerLVAdapter.setShopDATAsarrayList(thereshopDaTas);
                    home_GridView.setAdapter(homeViewpagerLVAdapter);
            }
        };

       Boolean isOK= mainActivity.bindService(intent,connection , mainActivity.BIND_AUTO_CREATE);
        Log.d("isbind?",":"+isOK);
        if(isOK){

        }
        else
        {
          Log.d("thresshopDatas"," :: " + thereshopDaTas.size());

                homeViewpagerLVAdapter.setShopDATAsarrayList(thereshopDaTas);

                homeViewpagerLVAdapter.notifyDataSetChanged();


        }

    }




}
