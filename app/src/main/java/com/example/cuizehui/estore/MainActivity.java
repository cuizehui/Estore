package com.example.cuizehui.estore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cuizehui.estore.adapter.MainViewPagerAdapter;
import com.example.cuizehui.estore.base.BaseActivity;

import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;
import com.example.cuizehui.estore.interfaces.DaggerMainActivityComponent;
import com.example.cuizehui.estore.interfaces.MainActivityComponent;
import com.example.cuizehui.estore.module.MainActivityModule;
import com.example.cuizehui.estore.uitls.Contants;
import com.example.cuizehui.estore.viewpagers_views.MinePagerView;
import com.example.cuizehui.estore.viewpagers_views.ShopCarView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    //注入对象
    @Inject
    public MainViewPagerAdapter mainViewPagerAdapter;

    @BindView(R.id.main_activity_vp)
    public ViewPager mainViewPager;

   public RadioGroup mainRadioGroup;

    @BindView(R.id.main_activity_home_rbt)
    RadioButton home_rbt;
    @BindView(R.id.main_activity_shop_rbt)
    RadioButton shop_rbt;
    @BindView(R.id.main_activity_order_rbt)
    RadioButton order_rbt;
    @BindView(R.id.main_activity_mine_rbt)
    RadioButton mine_rbt;

    //当前viewpager号码
    private int vPcurrentNb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind( this ) ;

        initView();
        initData();
        initEvent();

    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {
        MainActivityComponent  mainActivityComponent= DaggerMainActivityComponent
                .builder()
                .applicationComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initView() {
        super.initView();
   //        mainViewPager= (ViewPager) findViewById(R.id.main_activity_vp);
           mainRadioGroup= (RadioGroup) findViewById(R.id.main_activity_radio_group);
           mainViewPager.setAdapter(mainViewPagerAdapter);


    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.main_activity_home_rbt:
                        switchViewpager(0);
                        break;
                    case R.id.main_activity_shop_rbt:
                        switchViewpager(1);
                        break;
                    case R.id.main_activity_order_rbt:
                        switchViewpager(2);
                        break;

                    case R.id.main_activity_mine_rbt:
                        switchViewpager(3);
                        break;
                }
            }
        });
    }
    //依次设置每一个选择界面！！！
    public void switchViewpager(int i) {
        //当前viewpager显示的界面
        vPcurrentNb=i;
        mainViewPager.setCurrentItem(i);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("执行回调","1");
        final User user = MyApplication.getInstance(this).getUser();

        if (requestCode == Contants.REQUEST_CODE && resultCode == this.RESULT_OK) {

            Log.d("登录后 执行回调","ture");

            new Thread() {
                public void run() {
                    //这儿是耗时操作，完成之后更新UI；
                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            //通知子view g

                            if (user != null) {

                                //更新我的
                                MinePagerView minePagerView= (MinePagerView) mainViewPagerAdapter.getPagerViewArrayList().get(3);
                                minePagerView.initView();

                                minePagerView.initEvent();
                                mainViewPagerAdapter.notifyDataSetChanged();

                                //更新购物车
                               refrushShopCarView();

                            }
                            else {

                            }


                        }

                    });
                }
            }.start();

        }
        if (requestCode == 2 && resultCode == 2) {

            Log.d("购物车执行回调","ture");

            new Thread() {
                public void run() {
                    //这儿是耗时操作，完成之后更新UI；
                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            //通知子view g
                            if(user!=null)
                            {
                                 refrushShopCarView();
                            }
                            else {

                            }

                        }

                    });
                }
            }.start();

        }

         if (user != null) {
            //跳转至目标Activity
            if (MyApplication.getInstance(this).getIntent() == null) {

            }
            else {
                MyApplication.getInstance(this).jumpToTargetActivity(this);
            }
        }
        else {

        }


    }
    //刷新购物车
    public void refrushShopCarView(){
        ShopCarView shopCarView= (ShopCarView) mainViewPagerAdapter.getPagerViewArrayList().get(2);
        shopCarView.initDate();
        shopCarView.initView();

        mainViewPagerAdapter.notifyDataSetChanged();
    }


}
