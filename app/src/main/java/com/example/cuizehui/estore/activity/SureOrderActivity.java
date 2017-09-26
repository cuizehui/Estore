package com.example.cuizehui.estore.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.adapter.SureOrderRecycleViewAdapter;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.ShopAdress;
import com.example.cuizehui.estore.entity.ShopCarData;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 确认订单 activity
 */
public class SureOrderActivity extends BaseActivity {

    @BindView(R.id.order_adress)
    TextView orderadressTV;
    @BindView(R.id.orderpay_name)
            TextView ordername;
    @BindView(R.id.orderpay_phone)
            TextView phone;
    @BindView(R.id.orderpay_recyclerView)

    RecyclerView orderpay_recyclerView;

    @BindView(R.id.pay_allprice)
            TextView payallprice;
    @BindView(R.id.pay_number)
            TextView pay_number;


    List<ShopAdress>  shopAdresses;
    private ShopAdress shopAdress;
    private SureOrderRecycleViewAdapter sureOrderRecycleViewAdapter;
    private ArrayList<ShopCarData> shopCarDatas;

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_order);
        ButterKnife.bind(this);



        initData();
        initView();
        initEvent();

    }

    @Override
    protected void initData() {
        super.initData();
        //获取到地址信息
        shopAdresses= DataSupport.where("isfirstAdress = ?","true").find(ShopAdress.class);

            /*if(shopAdresses.size()==0){
            //第一次登录没有 设置过
                Log.d("第一次设置","");
             Intent intent =new Intent(SureOrderActivity.this,CreateAdressActivity.class);
                startActivity(intent);
            }
            else {

            }*/
           if(shopAdresses.size()==0){
                Log.d("第一次设置if",""+shopAdresses.size());
            }
            else {
               shopAdress= shopAdresses.get(0);
             }




        //商品信息

        Intent intentGet = getIntent();
        if(intentGet!=null){
           shopCarDatas = (ArrayList<ShopCarData>) intentGet.getSerializableExtra("dataBean");
            sureOrderRecycleViewAdapter=new SureOrderRecycleViewAdapter(this,shopCarDatas);
        }
        else {
            Log.d("intent传入集合为空","！1");
        }



    }

    @Override
    protected void initView() {
        super.initView();

        if(shopAdresses.size()==0){
            Intent intent =new Intent(SureOrderActivity.this,CreateAdressActivity.class);
            startActivityForResult(intent,4);
        }
        else {
            orderadressTV.setText(shopAdress.getDicAdress());
            ordername.setText(shopAdress.getSdname());
            phone.setText(shopAdress.getTelephone());
        }

        int Allnumber=0;
        int Allprice=0;

        for(int i=0;i<shopCarDatas.size();i++){
            ShopCarData shopCarData=  shopCarDatas.get(i);
           int oneprice= Integer.parseInt(shopCarData.getPrice());
           int number=Integer.parseInt(shopCarData.getNumber());
            Allnumber += Integer.parseInt( shopCarData.getNumber());

            Allprice += oneprice*number;
        }
        pay_number.setText(" 共 "+Allnumber+" 件商品 ");
        payallprice.setText("合计 "+Allprice+" 元");

        orderpay_recyclerView.setAdapter(sureOrderRecycleViewAdapter);
        orderpay_recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==4&&resultCode==3){
            Log.d("执行订单回调","！！");
            initData();
            initView();
            initEvent();
        }
    }
}
