package com.example.cuizehui.estore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.entity.ShopDaTa;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-9-19.
 */

public class HomeViewpagerLVAdapter extends BaseAdapter {
    public ArrayList<ShopDaTa> shopDATAsarrayList;

    Context context;

    public ArrayList<ShopDaTa> getShopDATAsarrayList() {
        return shopDATAsarrayList;
    }

    public HomeViewpagerLVAdapter(ArrayList<ShopDaTa> arrayList, MainActivity context) {
        super();
        this.context=context;
        shopDATAsarrayList=arrayList;
        Log.d("shopdataarraylistsize:",shopDATAsarrayList.size()+"");

    }

    @Override
    public int getCount() {
         return shopDATAsarrayList.size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ShopDaTa shopDaTa= shopDATAsarrayList.get(i);


        LayoutInflater layoutInflater=LayoutInflater.from(context);
        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        }
        else {

            view =layoutInflater.inflate(R.layout.shop_data_layout,null);
            holder = new ViewHolder(view);

         //   holder.textView =view.findViewById(R.id.productName_tV);

            view.setTag(holder);
        }

        holder.textView.setText(shopDaTa.getProductName().toString());

        return view;

    }
    //在holder中绑定
    static class ViewHolder {
         @BindView(R.id.productName_tV)
         TextView textView;
        public ViewHolder(View view) {
           ButterKnife.bind(this, view);
        }
    }


}
