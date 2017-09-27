package com.example.cuizehui.estore.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.OrderActivity;
import com.example.cuizehui.estore.entity.OrderMessage;
import com.example.cuizehui.estore.fragment.OrderFragment;

import java.util.ArrayList;

/**
 * Created by cuizehui on 17-9-27.
 */

public class OrderAllAdapter extends RecyclerView.Adapter<OrderAllAdapter.Holder> {
    ArrayList<OrderMessage> orderMessages;
    OrderActivity context;
    private int ITEM_HEADER = 1,ITEM_CONTENT = 2,ITEM_FOOTER = 3;

    public OrderAllAdapter(ArrayList<OrderMessage> orderMessages, OrderActivity context) {
        super();
        this.orderMessages=orderMessages;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == ITEM_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_header, parent, false);
        }else if(viewType == ITEM_CONTENT){
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_content, parent, false);
        }else if(viewType == ITEM_FOOTER){
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_footer, parent, false);
       }
       Holder holder=new Holder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderMessages.size();
    }

    static class  Holder extends  RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
