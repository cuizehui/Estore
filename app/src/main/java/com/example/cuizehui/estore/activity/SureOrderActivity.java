package com.example.cuizehui.estore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.adapter.SureOrderRecycleViewAdapter;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.OrderMessage;
import com.example.cuizehui.estore.entity.RefrushMain;
import com.example.cuizehui.estore.entity.ShopAdress;
import com.example.cuizehui.estore.entity.ShopCarData;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.fragment.PayDetailFragment;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认订单 activity
 */
public class SureOrderActivity extends BaseActivity implements PayDetailFragment.OnFragmentInteractionListener {

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

    @BindView(R.id.edite)
    Button btedite;


    List<ShopAdress>  shopAdresses;
    private ShopAdress shopAdress;
    private SureOrderRecycleViewAdapter sureOrderRecycleViewAdapter;
    private ArrayList<ShopCarData> shopCarDatas;
    private User user;
    private UserDb userDb;

    private String flagwhere;
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
        user=MyApplication.getInstance(this).getUser();
        userDb= MyApplication.getInstance(SureOrderActivity.this).getUserDatedb();

        //获取到地址信息
        shopAdresses= DataSupport.where("isfirstAdress = ? and  username= ?","true",user.getAccount()).find(ShopAdress.class);

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
            flagwhere=intentGet.getStringExtra("flag");
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
        btedite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SureOrderActivity.this,AdressMenagerActivity.class);
                startActivityForResult(intent,6);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("REQUEST_RESULTE",""+requestCode+"::"+resultCode);

        if(requestCode==4&&resultCode==3){
            initData();
            initView();
            initEvent();
        }

        if(requestCode==4&&resultCode==5){
             finish();
        }
        if (requestCode==6&&resultCode==6){
            initData();
            initView();
            initEvent();
        }

    }
    @OnClick (R.id.pay_submit)
    public  void payupPayfragment(){

        PayDetailFragment payDetailFragment=PayDetailFragment.newInstance(payallprice.getText().toString(),"");
        payDetailFragment.show(getSupportFragmentManager(),"payDetailFragment");

    }


    //fragment  支付完成后回调
    @Override
    public void onFragmentInteraction(String uri) {
            Log.d("支付完成后回调","！！"+uri);
        RefrushMain refrushMain=new RefrushMain();
        makeOrder(uri);
        if(flagwhere.equals("buy")){
           EventBus.getDefault().post(refrushMain);
        }
        else
            {

            for(int i=0;i<shopCarDatas.size();i++){
                userDb.deletePdinShopcar(user.getAccount(),shopCarDatas.get(i).getProducename());
            }
               EventBus.getDefault().post(refrushMain);
        }
         finish();
    }

    //生成订单 此方法 属于耗时操作
    private void makeOrder(String uri){
        for(int i=0;i<shopCarDatas.size();i++){
            ShopCarData shopCarData=  shopCarDatas.get(i);
            OrderMessage orderMessage=new OrderMessage();
            orderMessage.setUsername(user.getAccount());
            orderMessage.setOrderprice(shopCarData.getPrice());
            orderMessage.setShopname(shopCarData.getShopname());
            orderMessage.setPdname(shopCarData.getProducename());
            orderMessage.setPdnumber(shopCarData.getNumber());

            orderMessage.setOrderpic(shopCarData.getBytes());

            Date    curDate    =   new Date(System.currentTimeMillis());
            Log.d("curdate",curDate+"");
            orderMessage.setOrderid(curDate.toString());
            if(uri.equals("true")){
                orderMessage.setOrderstatus("payed");
            }
            else {
                orderMessage.setOrderstatus("waitpay");
            }
            orderMessage.save();

        }
    }
}
