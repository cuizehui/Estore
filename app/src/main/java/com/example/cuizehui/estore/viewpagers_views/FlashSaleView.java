package com.example.cuizehui.estore.viewpagers_views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.adapter.FlashSaleRecycleViewAdapter;
import com.example.cuizehui.estore.entity.FlashPruductData;
import com.example.cuizehui.estore.presenter.FlashSalePresenterImpl;

import java.util.ArrayList;

/**
 * Created by cuizehui on 17-9-14.
 * 抢购界面
 */

public class FlashSaleView extends  BasePagerView implements FlashSaleMVPView{
    FlashSalePresenterImpl flashSalePresenter;

    private RecyclerView recyclerView;
    private FlashSaleRecycleViewAdapter flashSaleRecycleViewAdapter;

    public FlashSaleView(MainActivity mainActivity) {
        super(mainActivity);

    }


    /***
     * mvp模式下 这个类中 不存在这个方法
     */
    @Override
    protected void initDate() {
        super.initDate();
    }

    @Override
    protected void initView() {
        super.initView();

        //获取引用 并将自己绑定给 presenter
        flashSalePresenter=new FlashSalePresenterImpl();
        flashSalePresenter.attachView(this);

        basePager_fl.removeAllViews();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);

        View shopview=inflater.inflate(R.layout.main_viewpager_shop,null);
        recyclerView=shopview.findViewById(R.id.flash_sale_rv);
        setupRecyclerView(recyclerView);
        //获取数据 并刷新ui(方法还是在这个类中)
        flashSalePresenter.loadRepositories();



        basePager_fl.addView(shopview);






    }



    @Override
    protected void initEvent() {
        super.initEvent();
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
         flashSaleRecycleViewAdapter=new FlashSaleRecycleViewAdapter();

        flashSaleRecycleViewAdapter.setItemClickCallback(new FlashSaleRecycleViewAdapter.ItemClickCallback() {
            @Override
            public void onItemClick(FlashPruductData flashPruductData) {
                flashSalePresenter.jumptoflashSaleProductActivity(flashPruductData);
            }
        });

        recyclerView.setAdapter(flashSaleRecycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
    }


    @Override
    public void reupRepositories(ArrayList<FlashPruductData> repositories) {
        //获取同一个？？！！！
        Log.d("刷新UI","start");
        FlashSaleRecycleViewAdapter adapter= (FlashSaleRecycleViewAdapter) recyclerView.getAdapter();
        adapter.setFlashSalerepositories(repositories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public MainActivity getMainActivity() {
        return mainActivity;
    }
}
