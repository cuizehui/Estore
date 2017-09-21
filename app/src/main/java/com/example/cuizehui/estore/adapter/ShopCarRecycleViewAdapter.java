package com.example.cuizehui.estore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.entity.ShopCarData;
import com.example.cuizehui.estore.viewpagers_views.BasePagerView;

import java.util.ArrayList;

/**
 * Created by cuizehui on 17-9-20.
 */

public class ShopCarRecycleViewAdapter extends RecyclerView.Adapter<ShopCarRecycleViewAdapter.ViewHolder>{
    private ArrayList<ShopCarData> shopCarDatas;

    private  MainActivity context;
    public ArrayList<ShopCarData> getShopCarDatas() {
        return shopCarDatas;
    }

    public ShopCarRecycleViewAdapter(MainActivity context, ArrayList<ShopCarData> shopCarDatas) {
        super();
        this.shopCarDatas=shopCarDatas;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);

        View view= inflater.inflate(R.layout.shopcar_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(shopCarDatas.get(position).getProducename());
        holder.numberTV.setText(shopCarDatas.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return shopCarDatas.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //店名

        //商品名
        public TextView mTextView;
        //数量
        public TextView numberTV;

        public ViewHolder(View view){
            super(view);
            mTextView = view.findViewById(R.id.shopcar_name);
            numberTV=view.findViewById(R.id.item_gi_num_tv);
        }
    }

}
