package com.example.cuizehui.estore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.widget.ClearEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity {
    @BindView(R.id.edittxt_phone)
    ClearEditText mEtxtPhone;

    @BindView(R.id.edittxt_code)
    ClearEditText edittxtPwd;
     @BindView(R.id.edittxt_pwd)
    ClearEditText edittxtRePwd;



    //绑定点击事件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        ButterKnife.bind(this);

        initData();
        initView();
        initEvent();

    }


    public  void initView(){

    }
    public void initEvent(){

    }
    public void initData(){

    }

    @OnClick (R.id.ac_reg_btn) void Reg() {
        String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
        String password = edittxtPwd.getText().toString().trim();
        String rePassword= edittxtRePwd.getText().toString().trim();
        checkNumber(phone,password,rePassword);

    }

    private void checkNumber(String phone,String code,String rePassword){
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
             return;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            Toast.makeText(this, "您输入的手机号码格式不正确",Toast.LENGTH_SHORT).show();

            return;
        }

        if (TextUtils.isEmpty(code)) {

            Toast.makeText(this, "请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (code.length() > 12 || code.length() < 6) {
            Toast.makeText(this, "请输入6-12位登录密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if(! code.toString().equals(rePassword.toString())){
            Toast.makeText(this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
            return;
        }
        putData( phone, code);
    }

    private  void putData(String phone,String key){

     //把 这个改成工具类
        UserDb userDb =UserDb.getInstance(this);

        userDb.insertuserTable("userphone",phone);
        //更新密码
        userDb.updataUserTable("userKeyword",key,"userphone",phone);

        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
             finish();
    }
}
