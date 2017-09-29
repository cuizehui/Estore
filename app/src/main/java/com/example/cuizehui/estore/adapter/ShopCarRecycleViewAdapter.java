package com.example.cuizehui.estore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.entity.ShopCarData;
import com.example.cuizehui.estore.viewpagers_views.BasePagerView;
import com.example.cuizehui.estore.viewpagers_views.ShopCarView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-9-20.
 */

public class ShopCarRecycleViewAdapter extends RecyclerView.Adapter<ShopCarRecycleViewAdapter.ViewHolder>{
    private ArrayList<ShopCarData> shopCarDatas;


    private  MainActivity context;

    private ShopCarView shopCarView;

    //删除接口
    private OnDeleteClickListener mOnDeleteClickListener;

    //操作数量的接口
    private OnEditClickListenter mOnEditClickListener;
    private OnResfreshListener mOnResfreshListener;

    public ArrayList<ShopCarData> getShopCarDatas() {
        return shopCarDatas;
    }

    public ShopCarRecycleViewAdapter(MainActivity context, ArrayList<ShopCarData> shopCarDatas,ShopCarView shopCarView) {
        super();

        this.shopCarView=shopCarView;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
     //    Log.d("第"+position+"项数据获取是否isfirst",shopCarDatas.get(position).getIsFirst()+"");


        holder.mTvshopname.setText(shopCarDatas.get(position).getShopname());
        holder.productname.setText(shopCarDatas.get(position).getProducename());
        holder.numberTV.setText(shopCarDatas.get(position).getNumber());
        holder.poductprice.setText(shopCarDatas.get(position).getPrice());

        if (position > 0) {
            if (shopCarDatas.get(position).getShopname().equals(shopCarDatas.get(position - 1).getShopname())) {
                holder.llShopCartHeader.setVisibility(View.GONE);
            } else {
                holder.llShopCartHeader.setVisibility(View.VISIBLE);
            }
        }else {
            holder.llShopCartHeader.setVisibility(View.VISIBLE);
        }
        //链接：http://www.jianshu.com/p/6c3328f87fc9


        //根据选中的状态值进行重绘
        //选中状态

        if(shopCarDatas.get(position).isSelect()){
            holder.shopproductSelect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_selected));
        }else {
            holder.shopproductSelect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_unselected));
        }

        if(shopCarDatas.get(position).isShopSelect()){
            holder.shopselect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_selected));
        }else {
            holder.shopselect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_unselected));
        }

       // 每次绘制 刷新  都会触发这个方法
        if(mOnResfreshListener != null){
            boolean isSelect = false;
            //返回一个是否全部选中的参数
            for(int i = 0;i < shopCarDatas.size(); i++){
                if(!shopCarDatas.get(i).isSelect()){
                    isSelect = false;
                    break;
                }else{
                    isSelect = true;
                }
            }
            mOnResfreshListener.onResfresh(isSelect,position);
        }



        //设置商品点击时间，并通知重绘
        holder.shopproductSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果点击一定和当前状态不同,设置点击的状态

                shopCarDatas.get(position).setSelect(!shopCarDatas.get(position).isSelect());

                //通过循环找出不同商铺被标记的第一个商品的位置
                for(int i = 0;i < shopCarDatas.size(); i++){
                    if(shopCarDatas.get(i).getIsFirst() == 1) {

                        //遍历去找出同一家商铺的所有商品的勾选情况
                        for(int j = 0;j < shopCarDatas.size();j++){
                            //如果是同一家商铺的商品，并且其中一个商品是未选中，
                            //那么商铺的全选勾选取消
                            if(shopCarDatas.get(j).getShopname().equals(shopCarDatas.get(i).getShopname().toString())
                                    && !shopCarDatas.get(j).isSelect()){
                                shopCarDatas.get(i).setShopSelect(false);
                                break;
                            }else{
                                //如果是同一家商铺的商品，并且所有商品是选中，那么商铺的选中全选勾选
                                shopCarDatas.get(i).setShopSelect(true);
                            }
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        //商店购销点击事件
        holder.shopselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果position的isFirst为1，证明是每个商铺的第一件商品
                //而且这件商品有商铺的头部header
                if(shopCarDatas.get(position).getIsFirst() == 1) {
                    //isShopSelect取反选
                    shopCarDatas.get(position).setShopSelect(!shopCarDatas.get(position).isShopSelect());

                    for(int i = 0;i < shopCarDatas.size();i++){
                        //如果Shopid与position的Shopid相同，
                        // 则设置它的选中情况与position的isShopSelect选中情况一样
                        if(shopCarDatas.get(i).getShopname().equals(shopCarDatas.get(position).getShopname().toString()) ){
                            shopCarDatas.get(i).setSelect(shopCarDatas.get(position).isShopSelect());
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });


        holder.minusIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                int number=Integer.parseInt(shopCarDatas.get(position).getNumber());
                if(number>0){
                    String producename=shopCarDatas.get(position).getProducename();
                    number=number-1;
                    if (mOnEditClickListener != null) {
                        Log.d("mOnEditClick","1234");
                        mOnEditClickListener.onEditClick( position,producename,number);
                    }
                    else {
                        Log.d("mOnEditClick","null");

                    }
                    shopCarDatas.get(position).setNumber(number+"");
                    notifyDataSetChanged();
                }

            }

        });

        holder.addIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number=Integer.parseInt(shopCarDatas.get(position).getNumber());
                String producename=shopCarDatas.get(position).getProducename();
                number=number+1;
                if (mOnEditClickListener != null) {
                     mOnEditClickListener.onEditClick( position,producename,number);
                }
                else {
                }
                shopCarDatas.get(position).setNumber(number+"");
                notifyDataSetChanged();

            }
        });

        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //调用删除某个规格商品的接口
                if(mOnDeleteClickListener != null){
                    mOnDeleteClickListener.onDeleteClick(view,position,shopCarDatas.get(position).getProducename());
                }

                shopCarDatas.remove(position);
                //重新排序，标记所有商品不同商铺第一个的商品位置
                shopCarView.isSelectFirst(shopCarDatas);
                notifyDataSetChanged();
            }
        });

    }

    //提供删除接口
    public interface OnDeleteClickListener{
        void onDeleteClick(View view, int position, String productname);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener mOnDeleteClickListener){
        this.mOnDeleteClickListener = mOnDeleteClickListener;
    }

    //提供更改数量的接口
    public interface OnEditClickListenter{
        void onEditClick(int postion, String productname, int count);
    }
    public void setOnEditClickListenter(OnEditClickListenter onEditClickListenter){
        this.mOnEditClickListener =onEditClickListenter;
    }

    //和Acitivity 交互的回调
    public interface OnResfreshListener{
        void onResfresh(boolean isSelect,int postion);
    }

    public void setResfreshListener(OnResfreshListener mOnResfreshListener){
        this.mOnResfreshListener = mOnResfreshListener;
    }

    @Override
    public int getItemCount() {
        return shopCarDatas.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //店名
        //商品名
        @BindView(R.id.tv_item_shopcart_shopname)
        public TextView mTvshopname;
        //数量
        @BindView(R.id.et_item_shopcart_cloth_num)
        public TextView numberTV;
        //商店布局
        @BindView(R.id.ll_shopcart_header)
        LinearLayout llShopCartHeader;

        @BindView(R.id.tv_item_shopcart_productname)
        TextView  productname;

        @BindView(R.id.tv_item_shopcart_product_price)
        TextView  poductprice;

        @BindView(R.id.iv_item_shopcart_shopselect)
        ImageView shopselect;

        @BindView(R.id.tv_item_shopcart_clothselect)
        ImageView shopproductSelect;

        @BindView(R.id.iv_item_shopcart_cloth_minus)
        ImageView minusIV;
        @BindView(R.id.iv_item_shopcart_cloth_add)
        ImageView addIV;

        @BindView(R.id.iv_item_shopcart_cloth_delete)
        ImageView deleteIV;



        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }


    }


    //

    //





}
