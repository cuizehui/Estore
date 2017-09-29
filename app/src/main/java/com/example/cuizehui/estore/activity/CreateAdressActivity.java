package com.example.cuizehui.estore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.ShopAdress;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateAdressActivity extends BaseActivity {

    @BindView(R.id.et_shouhuoren)
    EditText editText_sh;
    @BindView(R.id.et_Mobile)
    EditText editText_mobile;
    @BindView(R.id.et_xiangxidizhi)
    EditText editText_adress;

    User user;

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

        user= MyApplication.getInstance(this).getUser();

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

    public  Boolean checkEdit(String sr,String phone,String adress){
        if (TextUtils.isEmpty(sr)) {
            Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            Toast.makeText(this, "您输入的手机号码格式不正确",Toast.LENGTH_SHORT).show();

            return false;
        }
        if (TextUtils.isEmpty(adress)) {
            Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
            return false;
        }

        return  true;
    }

    @OnClick(R.id.btn_save)
    public  void saveadress(){
        String   mobile =editText_mobile.getText().toString();
        String sr=editText_sh.getText().toString();
        String adress=editText_adress.getText().toString();
        Boolean isillgle=checkEdit(sr,mobile,adress);
        //插入一条数据
        if(isillgle){
            ShopAdress shopAdress=new ShopAdress();
            shopAdress.setSdname(sr);
            shopAdress.setTelephone(mobile);
            shopAdress.setDicAdress(adress);
            shopAdress.setUsername(user.getAccount());
            List<ShopAdress> shopAdresses= DataSupport.where("isfirstAdress = ? and username= ? ","true",user.getAccount()).find(ShopAdress.class);

            /*if(shopAdresses.size()==0){
            //第一次登录没有 设置过
                Log.d("第一次设置","");
             Intent intent =new Intent(SureOrderActivity.this,CreateAdressActivity.class);
                startActivity(intent);
            }
            else {

            }*/
            if(shopAdresses.size()==0){
                shopAdress.setIsfirstAdress("true");
            }
            else {
                shopAdress.setIsfirstAdress("false");
            }

            shopAdress.save();
            Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
            setResult(3);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        Log.d("result","5");
        setResult(5);

        super.onBackPressed();
          finish();
    }
}
