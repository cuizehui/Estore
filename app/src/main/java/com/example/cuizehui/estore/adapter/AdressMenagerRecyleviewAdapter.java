package com.example.cuizehui.estore.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.AdressMenagerActivity;
import com.example.cuizehui.estore.entity.ShopAdress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-9-25.
 */

public class AdressMenagerRecyleviewAdapter extends RecyclerView.Adapter<AdressMenagerRecyleviewAdapter.Holder> {
    AdressMenagerActivity adressMenagerActivity;
    List<ShopAdress> shopAdresses;
    onDeleteLisner onDeleteLisner;
    onFirstAdressLisner onFirstAdressLisner;

    public AdressMenagerRecyleviewAdapter(AdressMenagerActivity adressMenagerActivity, List<ShopAdress> shopAdresses) {

        this.adressMenagerActivity=adressMenagerActivity;
        this.shopAdresses=shopAdresses;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(adressMenagerActivity);
        View view= layoutInflater.inflate(R.layout.shop_adress_item,null);
        Holder holder=new Holder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        ShopAdress shopAdress=shopAdresses.get(position);
        holder.adress_name.setText(shopAdress.getSdname());
        holder.moblie_tv.setText(shopAdress.getTelephone());
        holder.dicadress_tv.setText(shopAdress.getDicAdress());


        holder.delete_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if(onDeleteLisner!=null){
                            onDeleteLisner.onDelete(position);
                        }
            }
        });

        holder.set_firstAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(onFirstAdressLisner!=null){

                   onFirstAdressLisner.setFirstAdress(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopAdresses.size();
    }



    public static class Holder extends RecyclerView.ViewHolder{
        @BindView(R.id.adress_name)
        TextView adress_name;
        @BindView(R.id.mobile_iv)
        TextView moblie_tv;
        @BindView(R.id.adress_delete)
        TextView delete_adress;
        @BindView(R.id.txt_dicadress)
        TextView dicadress_tv;
        @BindView(R.id.set_firstAdress)
        TextView set_firstAdress;


        public Holder(View itemView) {
            super(itemView);
          ButterKnife.bind(this,itemView);
        }
    }

    public void setonDeleteOnclickLisner(onDeleteLisner onDeleteLisner){
        this.onDeleteLisner=onDeleteLisner;

    }
    public interface  onDeleteLisner{
        void onDelete(int position);
    }

    //设置 默认地址回调
    public void setonFirstAdressOnclickLisner(onFirstAdressLisner onFirstAdressLisner){
        this.onFirstAdressLisner=onFirstAdressLisner;

    }
    public interface  onFirstAdressLisner{
        void setFirstAdress(int position);
    }

}
