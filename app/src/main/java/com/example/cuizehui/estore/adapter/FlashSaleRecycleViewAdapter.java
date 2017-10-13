package com.example.cuizehui.estore.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.entity.FlashPruductData;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-10-13.
 */

public class FlashSaleRecycleViewAdapter extends  RecyclerView.Adapter<FlashSaleRecycleViewAdapter.RepositoryViewHolder>{

    ArrayList<FlashPruductData> repositories;

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

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }




    public class RepositoryViewHolder extends  RecyclerView.ViewHolder{


        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
