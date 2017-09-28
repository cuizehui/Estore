package com.example.cuizehui.estore.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.OrderActivity;
import com.example.cuizehui.estore.entity.OrderMessage;
import com.example.cuizehui.estore.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-9-27.
 */

public class OrderAllAdapter extends RecyclerView.Adapter<OrderAllAdapter.Holder> {
   List<OrderMessage> orderMessages;
    OrderActivity context;
    private int ITEM_HEADER = 1,ITEM_CONTENT = 2,ITEM_FOOTER = 3;

    public OrderAllAdapter(List<OrderMessage> orderMessages, OrderActivity context) {
        super();
        this.orderMessages=orderMessages;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
       /* if(viewType == ITEM_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_header, parent, false);
        }else if(viewType == ITEM_CONTENT){
         }else if(viewType == ITEM_FOOTER){
            view = LayoutInflater.from(context).inflate(R.layout.item_allorder_footer, parent, false);
       }*/
        view = LayoutInflater.from(context).inflate(R.layout.item_allorder_content, parent, false);

        Holder holder=new Holder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.orderitem_pctv.setText(orderMessages.get(position).getPdname());
    }

    @Override
    public int getItemCount() {
        return orderMessages.size();
    }

    static class  Holder extends  RecyclerView.ViewHolder{

       @BindView(R.id.tv_orderitem_productname)
       public TextView orderitem_pctv;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
