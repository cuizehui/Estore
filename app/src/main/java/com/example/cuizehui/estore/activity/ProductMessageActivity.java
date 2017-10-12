package com.example.cuizehui.estore.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.ShopCarData;
import com.example.cuizehui.estore.entity.ShopDaTa;
import com.example.cuizehui.estore.entity.StringFlag;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;
import com.example.cuizehui.estore.widget.BottomBarView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 产品信息Activity
 */
public class ProductMessageActivity extends BaseActivity {

    @BindView(R.id.shopname)
    TextView shopname_textView;
    @BindView(R.id.tv_add_shopcar)
    TextView addshopcar_textView;
    @BindView(R.id.tv_good_detail_shop)
    TextView shopMessageTV;
    @BindView(R.id.tv_good_detail_buy)
    TextView butTV;
    @BindView(R.id.shopdata_price)
    TextView price;
    @BindView(R.id.shopcar_icon)
    BottomBarView bottomBarView;
    @BindView(R.id.product_mianpic)
    ImageView product_iV;

    private ShopDaTa shopDaTa;
    private UserDb userDb;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_message);
        Log.d("productAcitivity","!oncreate");
        initData();
        initView();
        initEvent();
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {

    }

    @Override
    protected void initView() {
        super.initView();
        //获取购物清单 总数量 而不是种类数量！
        UserDb userDb=MyApplication.getInstance(ProductMessageActivity.this).getUserDatedb();
        Cursor cursor=userDb.selectShopCarMessage(user.getAccount());
        int number= cursor.getCount();

        try {
            bottomBarView.add(number);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initData() {
        super.initData();
        ButterKnife.bind(this);
        shopDaTa = (ShopDaTa) getIntent().getSerializableExtra("shopData");

        shopname_textView.setText(shopDaTa.getProductName());
        price.setText(shopDaTa.getPrice());


        byte[] bytes=shopDaTa.getBitmaps();
         if(bytes!=null){
             Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
             product_iV.setImageBitmap(bitmap);
         }
         else {

         }

         userDb=MyApplication.getInstance(ProductMessageActivity.this).getUserDatedb();
         user= MyApplication.getInstance(ProductMessageActivity.this).getUser();


    }

    @Override
    protected void initEvent() {
        super.initEvent();

        addshopcar_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number=0;

                number=number+1;
                  //database
                //插入表 商品名 和商品个数 商家名（便于分类）

                String username =user.getAccount();

                    String price=shopDaTa.getPrice();
                    String productname=shopDaTa.getProductName();
                    String shopname=shopDaTa.getShopName();
                    byte[] bytes=shopDaTa.getBitmaps();

                    // 插入前可先进行查询 如果已经存在 则使用更新方式 如果不存在则使用插入
                     String  selectnumber=userDb.Productnumber(username,productname);
                 int   i=Integer.parseInt(selectnumber);
                     if(i==0){
                        Log.d("数据库插入","");
                        userDb.insertShopCarTable(username,productname,shopname,number+"",price,bytes);
                      }
                    else  {
                        Log.d("数据库更新","");
                         number=number+i;
                         userDb.updataSCProductnunber(username,productname,number+"");
                    }


                    //执行动画
                    Toast.makeText(ProductMessageActivity.this,"添加成功",Toast.LENGTH_SHORT).show();

                try {
                    bottomBarView.add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    setResult(2);



            }
        });

       shopMessageTV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(ProductMessageActivity.this,ShopMessageAcitivity.class);
               startActivity(intent);
           }
       });
        butTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentbuy=new Intent(ProductMessageActivity.this,SureOrderActivity.class);

                //购物车信息包装传入
                ShopCarData shopCarData=new ShopCarData();
                shopCarData.setProducename(shopDaTa.getProductName());
                shopCarData.setIsFirst(1);
                shopCarData.setPrice(shopDaTa.getPrice());
                shopCarData.setShopname(shopDaTa.getShopName());
                shopCarData.setNumber("1");
                shopCarData.setBytes(shopDaTa.getBitmaps());
                ArrayList<ShopCarData> shopCarDatas=new ArrayList<ShopCarData>();
                shopCarDatas.add(shopCarData);

                intentbuy.putExtra("dataBean",shopCarDatas);
                intentbuy.putExtra("flag","buy");
                startActivity(intentbuy);

            }
        });

        bottomBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringFlag stringFlag=new StringFlag();

                stringFlag.setFlag("jumpshopcar");
                EventBus.getDefault().post(stringFlag);

                finish();

            }
        });
    }


    @Override
    public void finish() {
         setResult(2);
        super.finish();
    }


}
