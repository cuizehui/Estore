package com.example.cuizehui.estore.viewpagers_views;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.SureOrderActivity;
import com.example.cuizehui.estore.adapter.ShopCarRecycleViewAdapter;
import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.ShopCarData;
import com.example.cuizehui.estore.entity.User;

import java.util.ArrayList;


/**
 * Created by cuizehui on 17-9-14.
 */

public class ShopCarView extends  BasePagerView {
    
    public  RecyclerView mRecyclerView;
    ShopCarRecycleViewAdapter shopCarRecycleViewAdapter;

    public UserDb userDb;
    public ArrayList<ShopCarData> shopCarDatas;
    public User user;

    public ShopCarView shopCarView;

    public  TextView  tvShopCartSelect;
    private boolean mSelect=true;
     private TextView tvShopCartTotalNum;
    private TextView tvcommitOrder;
    private TextView tv_shopcart_totalprice;
    private ImageView imptyi;


    public ShopCarView getShopCarView() {
        return shopCarView;
    }

    public ShopCarView(MainActivity mainActivity) {
        super(mainActivity);

        Log.d("shopCarview","绘制");
        User user= MyApplication.getInstance(mainActivity).getUser();

        if(user!=null) {
            initDate();
            initView();
            initEvent();
        }else {

        }
        shopCarView=this;

    }

    @Override
    public void initView() {
        super.initView();



        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        View shopcarview=inflater.inflate(R.layout.main_viewpager_shopcar,null);
        mRecyclerView=shopcarview.findViewById(R.id.shopcar_recyclerView);
        tvShopCartSelect=shopcarview.findViewById(R.id.tv_shopcart_addselect);
        tvShopCartTotalNum=shopcarview.findViewById(R.id.tv_shopcart_totalnum);
        tv_shopcart_totalprice=shopcarview.findViewById( R.id.tv_shopcart_totalprice);
        imptyi=shopcarview.findViewById(R.id.umpty_iv);
        if(shopCarDatas.size()==0){
            imptyi.setVisibility(View.VISIBLE);
        }
        else {
            imptyi.setVisibility(View.GONE);
        }
        tvcommitOrder=shopcarview.findViewById(R.id.tv_shopcart_submit);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        //设置adapter
        mRecyclerView.setAdapter(shopCarRecycleViewAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //   mRecyclerView.addItemDecoration(new DividerItemDecoration(mainActivity, DividerItemDecoration.HORIZONTAL_LIST));
        basePager_fl.addView(shopcarview);
    }


    //初始化购物车时执行这个方法
   //回调后依然执行这个方法
    @Override
    public void initDate() {
        super.initDate();
       user= MyApplication.getInstance(mainActivity).getUser();
        //Log.d("shopCarview","initdata1");

        //判断是否在登录状态
        if(user!=null){
            //拉取数据库 购物车商品信息
          //  Log.d("shopCarview","initdata2");
            userDb= MyApplication.getInstance(mainActivity).getUserDatedb();
            //返回 按商店排序的数据集合
            Cursor cursor= userDb.selectPdOrderByShopName(user.getAccount());
            shopCarDatas = userDb.passShopCarMessage(cursor);
            Log.d("shopCarViewdata size",shopCarDatas.size()+"");

            //如果是0 说明没有购物车信息
            if(shopCarDatas.size()==0){
                //设置空数据
               shopCarDatas=new ArrayList<>();
            }
            else {
                //获取真数据并排序
                Log.d("",shopCarDatas.get(0).getProducename());
                isSelectFirst(shopCarDatas);
            }
            shopCarRecycleViewAdapter=new ShopCarRecycleViewAdapter(mainActivity,shopCarDatas,shopCarView);
        }
        else
            {
             shopCarDatas=new ArrayList<>();
             shopCarRecycleViewAdapter=new ShopCarRecycleViewAdapter(mainActivity,shopCarDatas,shopCarView);
        }


    }

    @Override
    public void initEvent() {
        //全选
        tvShopCartSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){

                    mSelect = !mSelect;
                    if(mSelect){
                        Drawable left = mainActivity.getResources().getDrawable(R.drawable.shopcart_selected);
                        tvShopCartSelect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                        for(int i = 0;i < shopCarDatas.size();i++){
                            shopCarDatas.get(i).setSelect(true);
                            shopCarDatas.get(i).setShopSelect(true);
                        }
                    }else{
                        Drawable left = mainActivity.getResources().getDrawable(R.drawable.shopcart_unselected);
                        tvShopCartSelect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                        for(int i = 0;i < shopCarDatas.size();i++){
                            shopCarDatas.get(i).setSelect(false);
                            shopCarDatas.get(i).setShopSelect(false);
                        }
                    }
                    shopCarRecycleViewAdapter.notifyDataSetChanged();


                }
            }



        });
        //更改数量
        shopCarRecycleViewAdapter.setOnEditClickListenter(new ShopCarRecycleViewAdapter.OnEditClickListenter() {
            @Override
            public void onEditClick(int postion, String productname, int count) {

                userDb.updataSCProductnunber(user.getAccount(),productname,count+"");
            }
        });
        //删除
        shopCarRecycleViewAdapter.setOnDeleteClickListener(new ShopCarRecycleViewAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position, String productname) {

                userDb.deletePdinShopcar(user.getAccount(),productname);
                //删除数据后重新计算最下面两栏的数据

                Log.d("删除回调","执行");
                //这里因为比上一个慢半拍所以等于1 其实已经是没有数据了
                if(shopCarDatas.size()==1){
                    tv_shopcart_totalprice.setText("总价：" + 0);
                    tvShopCartTotalNum.setText("共" +0 + "件商品");
                }
                else {
                }

            }
        });
        //
        shopCarRecycleViewAdapter.setResfreshListener(new ShopCarRecycleViewAdapter.OnResfreshListener() {
            @Override
            public void onResfresh(boolean isSelect,int positon) {
                Log.d("onResfresh","!!resfresh");
                //处理点击后图标变化的
                mSelect = isSelect;
                if (mSelect) {
                    Drawable left = mainActivity.getResources().getDrawable(R.drawable.shopcart_selected);
                    tvShopCartSelect.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);

                } else {
                    Drawable left = mainActivity.getResources().getDrawable(R.drawable.shopcart_unselected);
                    tvShopCartSelect.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                }


                if (positon==shopCarDatas.size()-1){
                    //遍历集合 给后面的赋值 而赋值操作只有最后一项绘制完成后才需要。
                    int mTotalNum = 0;
                    int mTotalPrice=0;
                    for(int i = 0; i < shopCarDatas.size(); i++)
                        if(shopCarDatas.get(i).isSelect()) {
                            float  oneprice = Integer.parseInt(shopCarDatas.get(i).getPrice());
                            float  number= Integer.parseInt(shopCarDatas.get(i).getNumber());
                            mTotalPrice += oneprice * number;
                            mTotalNum += 1;
                        }

                    tv_shopcart_totalprice.setText("总价：" + mTotalPrice);
                    tvShopCartTotalNum.setText("共" + mTotalNum + "件商品");
                    Log.d("第"+positon+"项","（最后一项）改变Text");
                }
                else {
                    Log.d("第"+positon+"项","正在判断select");
                }



            }
        });
        tvcommitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null){

                    Intent startsureOrderActivity=new Intent(mainActivity, SureOrderActivity.class);
                    ArrayList<ShopCarData> selectShopCardata=new ArrayList<ShopCarData>();
                    for(int i = 0; i < shopCarDatas.size(); i++)
                        if(shopCarDatas.get(i).isSelect()) {
                            selectShopCardata.add(shopCarDatas.get(i));
                        }

                    startsureOrderActivity.putExtra("dataBean",selectShopCardata);
                    startsureOrderActivity.putExtra("flag","shopcar");
                    mainActivity.startActivity(startsureOrderActivity);
                }
            }
        });

    }


    /**
     * 给每个商品添加头属性
     * @param list
     */
    public static void isSelectFirst(ArrayList<ShopCarData> list){
        if(list.size() > 0) {
            //头个商品一定属于它所在商铺的第一个位置，isFirst标记为1.
            list.get(0).setIsFirst(1);

            for (int i = 1; i < list.size(); i++) {
                //每个商品跟它前一个商品比较，如果Shopid相同isFirst则标记为2，
                //如果Shopid不同，isFirst标记为1.
                if (list.get(i).getShopname().equals(list.get(i - 1).getShopname().toString())) {
                    list.get(i).setIsFirst(2);
                } else {
                    list.get(i).setIsFirst(1);
                }
            }
        }
    }
}
