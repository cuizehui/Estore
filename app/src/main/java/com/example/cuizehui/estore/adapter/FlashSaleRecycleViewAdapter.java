package com.example.cuizehui.estore.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.entity.FlashPruductData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-10-13.
 */

public class FlashSaleRecycleViewAdapter extends  RecyclerView.Adapter<FlashSaleRecycleViewAdapter.RepositoryViewHolder>{

    ArrayList<FlashPruductData> repositories;

 ItemClickCallback itemClickCallback;

    public void setItemClickCallback(ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public FlashSaleRecycleViewAdapter() {
        this.repositories = null;

    }

    public FlashSaleRecycleViewAdapter(ArrayList<FlashPruductData> repositories) {
        this.repositories = repositories;

    }

    public void setFlashSalerepositories(ArrayList<FlashPruductData> repositories){
        this.repositories=repositories;
    }



    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flashsale_rv_item, parent, false);

        RepositoryViewHolder viewHolder=new RepositoryViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
         final FlashPruductData flashPruductData=repositories.get(position);
        holder.tv_newPrice.setText("现价： "+flashPruductData.getFlashprice()+ " ¥");
        holder.tv_oldPrice.setText("原价： "+flashPruductData.getOldprice()+  " ¥");
        holder.tv_ProuctName.setText(flashPruductData.getProductname());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickCallback!=null){
                        itemClickCallback.onItemClick(flashPruductData);
                }
                else {

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }




    public class RepositoryViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.flash_sale_tv_productname)
        TextView tv_ProuctName;
        @BindView(R.id.flash_sale_tv_oldprice)
        TextView tv_oldPrice;
        @BindView(R.id.flash_sale_tv_nowprice)
        TextView tv_newPrice;
        @BindView(R.id.flash_sale_ll)
        LinearLayout ll;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public interface ItemClickCallback {
        void onItemClick(FlashPruductData flashPruductData);
    }
}
