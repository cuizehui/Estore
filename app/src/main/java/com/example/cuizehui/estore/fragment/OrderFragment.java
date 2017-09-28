package com.example.cuizehui.estore.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.OrderActivity;
import com.example.cuizehui.estore.adapter.OrderAllAdapter;
import com.example.cuizehui.estore.entity.OrderMessage;
import com.example.cuizehui.estore.entity.ShopAdress;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderFragment extends BaseFragment {
    @BindView(R.id.all_order_Recyclerview)
    RecyclerView recyclerView;

    OrderActivity orderActivity;
    private OrderAllAdapter orderAllAdapter;

    public OrderFragment() {
        // Required empty public constructor
    }
    public OrderFragment(OrderActivity orderActivity) {
        this();
        this.orderActivity=orderActivity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this,view);
        initView();
        initEvent();
        return view;

    }





    private void initData() {
        List<OrderMessage> orderMessageList=DataSupport.findAll(OrderMessage.class);

        //头数据预处理增加标记（原型模式）

        Log.d("orderMeessagelist,size:",""+orderMessageList.size());
        //筛选list   并分发下去

      orderAllAdapter=new OrderAllAdapter(orderMessageList,orderActivity);
    }
    private void initView() {


        recyclerView.setAdapter(orderAllAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(orderActivity));
    }

    private void initEvent() {

    }



}
