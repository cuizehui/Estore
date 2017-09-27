package com.example.cuizehui.estore.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.TabEntity;
import com.example.cuizehui.estore.fragment.BaseFragment;
import com.example.cuizehui.estore.fragment.OrderFragment;
import com.example.cuizehui.estore.fragment.OrderNoBBFragment;
import com.example.cuizehui.estore.fragment.OrderNoPayFragment;
import com.example.cuizehui.estore.fragment.OrderPayedFragment;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{

    /*@BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    */
    @BindView(R.id.common_layout)
    CommonTabLayout mCommonTabLayout;
    @BindView(R.id.order_viewpager)
    ViewPager mViewPager;


    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"全部", "待付款", "待收货", "待退货"};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();


    }



    protected void initData() {
        initTab();

    }


    protected void initView() {
   }


    protected void initEvent() {
    }
    /*  @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

*/

    private void initTab() {


            mFragments.add(new OrderFragment());
            mFragments.add(new OrderPayedFragment());
            mFragments.add(new OrderNoPayFragment());
            mFragments.add(new OrderNoBBFragment());


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        //第二步关联 viewpager
        tl_2();

    }





    private void tl_2() {
        mCommonTabLayout.setTabData(mTabEntities);
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCommonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    //与fragment互动的方法
    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    //fragmentAdp
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }




}
