package com.example.cuizehui.estore.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.AdressMenagerActivity;
import com.example.cuizehui.estore.entity.ShopAdress;

import java.util.ArrayList;

/**
 * Created by cuizehui on 17-9-25.
 */

public class AdressMenagerRecyleviewAdapter extends RecyclerView.Adapter<AdressMenagerRecyleviewAdapter.Holder> {
    AdressMenagerActivity adressMenagerActivity;
    ArrayList<ShopAdress> shopAdresses;
    public AdressMenagerRecyleviewAdapter(AdressMenagerActivity adressMenagerActivity,ArrayList<ShopAdress> shopAdresses) {

        this.adressMenagerActivity=adressMenagerActivity;
        this.shopAdresses=shopAdresses;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(adressMenagerActivity);
       View view= layoutInflater.inflate(R.layout.shop_adress_item,null);
        return null;


    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public static class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }


}
