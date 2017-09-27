package com.example.cuizehui.estore.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuizehui.estore.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderFragment extends BaseFragment {
    @BindView(R.id.all_order_Recyclerview)
    RecyclerView recyclerView;


    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this,view);
        return view;

    }



}
