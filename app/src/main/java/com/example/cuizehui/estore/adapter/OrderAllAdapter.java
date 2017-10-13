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
import com.example.cuizehui.estore.activity.OrderActivity;
import com.example.cuizehui.estore.entity.OrderMessage;

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
     public Bitmap bitmap;

    public OrderAllAdapter(List<OrderMessage> orderMessages, OrderActivity context) {
        super();
        this.orderMessages=orderMessages;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(context).inflate(R.layout.item_allorder_content, parent, false);

        Holder holder=new Holder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.orderitem_pctv.setText(orderMessages.get(position).getPdname());
        holder.order_price.setText(orderMessages.get(position).getOrderprice()+"元");
        holder.order_state.setText(orderMessages.get(position).getOrderstatus());
        holder.order_number.setText("共"+orderMessages.get(position).getPdnumber()+"件");
        holder.order_id.setText(orderMessages.get(position).getOrderid());
        holder.order_shopname.setText(orderMessages.get(position).getShopname());
        byte[] bytes=orderMessages.get(position).getOrderpic();

        if(bytes!=null){
            // 5次就会OOM 异常？  //可以进行缓存 处理 也可以进行 收回？
            //成员变量
            bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);

            holder.order_iv.setImageBitmap(bitmap);
        }else {

        }

    }

    @Override
    public int getItemCount() {
        return orderMessages.size();
    }

    static class  Holder extends  RecyclerView.ViewHolder{

       @BindView(R.id.tv_orderitem_productname)
       public TextView orderitem_pctv;

        @BindView(R.id.tv_item_allorder_item_price)
        TextView  order_price;
        @BindView(R.id.tv_item_allorder_state)
        TextView order_state;

        @BindView(R.id.order_all_numbertv)
        TextView order_number;
        @BindView(R.id.tv_item_allorder_orderid)
        TextView order_id;
        @BindView(R.id.tv_item_allorder_shopname)
        TextView order_shopname;
        @BindView(R.id.orderAll_pic)
        ImageView order_iv;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
