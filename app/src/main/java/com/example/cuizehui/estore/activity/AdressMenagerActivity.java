package com.example.cuizehui.estore.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.adapter.AdressMenagerRecyleviewAdapter;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.ShopAdress;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收货地址管理Activity
 */
public class AdressMenagerActivity extends BaseActivity {
    @BindView(R.id.toolbar_right_title)
    TextView tt_right_tv;


    User user;

    @BindView(R.id.adressRecyclerview)
    RecyclerView recyclerView;

    List<ShopAdress> shopAdresses;

    AdressMenagerRecyleviewAdapter adressMenagerRecyleviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress_menager);
        ButterKnife.bind(this);
        user = MyApplication.getInstance(this).getUser();

        initData();
        initView();
        initEvent();

    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {

    }

    @Override
    protected void initData() {
        super.initData();

        //获取到地址信息
        shopAdresses= DataSupport.where("username = ?",user.getAccount().toString()).find(ShopAdress.class);

        if(shopAdresses!=null){
            Log.d("AressMa sieze :", shopAdresses.size()+"");
            adressMenagerRecyleviewAdapter=new AdressMenagerRecyleviewAdapter(this,shopAdresses);
        }
        else {
            Log.d("Aressma size:", shopAdresses.size()+"");
        }

    }

    @Override
    protected void initView() {
        super.initView();
        tt_right_tv.setText("编辑");

        recyclerView.setAdapter(adressMenagerRecyleviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        tt_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AdressMenagerActivity.this,CreateAdressActivity.class);
                startActivityForResult(intent,3);
            }
        });
        adressMenagerRecyleviewAdapter.setonDeleteOnclickLisner(new AdressMenagerRecyleviewAdapter.onDeleteLisner() {
            @Override
            public void onDelete(int position) {
                ShopAdress shopAdress=shopAdresses.get(position);
                DataSupport.deleteAll(ShopAdress.class,"sdname =? and  dicAdress=? ",shopAdress.getSdname().toString(),shopAdress.getDicAdress().toString());
               refrush();
            }
        });

        adressMenagerRecyleviewAdapter.setonFirstAdressOnclickLisner(new AdressMenagerRecyleviewAdapter.onFirstAdressLisner() {
            @Override
            public void setFirstAdress(int position) {
                 ShopAdress shopAdress=shopAdresses.get(position);
                //同步到数据库
                shopAdress.setIsfirstAdress("true");
                shopAdress.save();
                Toast.makeText(AdressMenagerActivity.this,"设置"+shopAdress.getDicAdress()+"为默认地址",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==3&&resultCode==3){
          /*  Log.d("adressmenager 回调","！！");

            shopAdresses= DataSupport.findAll(ShopAdress.class);
            Log.d("回调后 sieze :", shopAdresses.size()+"");

            //无效的原因可能时因为生命周期回调时，产生的是两个不同的对象
            adressMenagerRecyleviewAdapter.notifyDataSetChanged();*/
                refrush();
        }

    }
    public void refrush(){
        initData();
        initView();
        initEvent();

    }
}
