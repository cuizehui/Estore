package com.example.cuizehui.estore.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.OrderActivity;
import com.example.cuizehui.estore.adapter.OrderAllAdapter;
import com.example.cuizehui.estore.entity.OrderMessage;
import com.example.cuizehui.estore.entity.User;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderFragment extends BaseFragment {
    @BindView(R.id.all_order_Recyclerview)
    RecyclerView recyclerView;
    List<OrderMessage> orderMessageList;
    OrderActivity orderActivity;
    private OrderAllAdapter orderAllAdapter;
    private User user;

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

        Log.d("orderFragment"," oncreate");

        View view= inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this,view);
        initView();
        initEvent();
        return view;

    }





    private void initData() {

        user= MyApplication.getInstance(orderActivity).getUser();
        orderMessageList=DataSupport.where("username = ? ",user.getAccount()).find(OrderMessage.class);

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

    @Override
    public void onStop() {
        super.onStop();
        Log.d("orderFragment "," stop");
    }

    @Override
    public void onDestroy() {

      //  orderAllAdapter.bitmap.recycle();
        super.onDestroy();
        Log.d("orderFragment "," destroy");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ondestroyview","destroyview");


    }
}
