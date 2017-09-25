package com.example.cuizehui.estore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.ShopAdress;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAdressActivity extends BaseActivity {

    @BindView(R.id.et_shouhuoren)
    EditText editText_sh;
    @BindView(R.id.et_Mobile)
    EditText editText_mobile;
    @BindView(R.id.et_xiangxidizhi)
    EditText editText_adress;
    @BindView(R.id.btn_save)
    Button  bt_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_adress);
        ButterKnife.bind(this);
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
        ShopAdress shopAdress=new ShopAdress();

        //详细地址  写入数据库

        //    shopAdress.setDicAdress();
        //
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
