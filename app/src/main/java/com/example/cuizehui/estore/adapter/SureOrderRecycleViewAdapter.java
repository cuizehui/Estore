package com.example.cuizehui.estore.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.SureOrderActivity;
import com.example.cuizehui.estore.entity.ShopCarData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-9-26.
 */

public class SureOrderRecycleViewAdapter extends  RecyclerView.Adapter<SureOrderRecycleViewAdapter.Holder>{
   SureOrderActivity activity;
    ArrayList<ShopCarData> shopDaTas;
    public SureOrderRecycleViewAdapter(SureOrderActivity activity,ArrayList<ShopCarData> shopDaTas) {
        super();
        this.activity=activity;
        this.shopDaTas=shopDaTas;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(activity);
       View view= layoutInflater.inflate(R.layout.order_shop_item,null);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ShopCarData shopCarData=shopDaTas.get(position);
        holder.tvname.setText(shopCarData.getProducename());
        holder.tVshop_price.setText(shopCarData.getPrice());
        holder.tvnum.setText(shopCarData.getNumber());
        holder.tVshopname.setText(shopCarData.getShopname());

        //处理字节图片
        byte[] bytes=shopCarData.getBytes();
        if(bytes!=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.iv_pic.setImageBitmap(bitmap);
        }
        else {

        }

    }

    @Override
    public int getItemCount() {
        return shopDaTas.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_shop_price)
        TextView tVshop_price;
        @BindView(R.id.order_shopname)
        TextView tVshopname;
        @BindView(R.id.order_product_num)
        TextView tvnum;
        @BindView(R.id.order_product_name)
        TextView tvname;
        @BindView(R.id.order_pic)
        ImageView iv_pic;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
