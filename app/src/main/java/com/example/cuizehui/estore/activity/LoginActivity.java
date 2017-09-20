package com.example.cuizehui.estore.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.User;

public class LoginActivity extends Activity  {


    TextView mEtxtPhone;
    TextView mEtxtPwd;
    Button loginButton;
    TextView mEtxtReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

    }

    protected void initView() {
        mEtxtPhone = findViewById(R.id.etxt_phone);
        mEtxtPwd = findViewById(R.id.etxt_pwd);
        loginButton = findViewById(R.id.btn_login);
        mEtxtReg=findViewById(R.id.txt_toReg);
    }

    protected void initEvent() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //trim 删除首位空白
                String phone = mEtxtPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(LoginActivity.this,"请输入手机号码",Toast.LENGTH_SHORT).show();
                    return;
                }

                String pwd = mEtxtPwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                checkKeyWord(phone,pwd);

            }
        });


        mEtxtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  startRegIntent=new Intent(LoginActivity.this,RegActivity.class);
                  startActivity(startRegIntent);
            }
        });
    }

    /**
     * 核对账户密码
     * @param phone
     * @param pwd
     */
    private  void checkKeyWord(String phone, String pwd){
        UserDb userDb= MyApplication.getInstance(this).getUserDatedb();
        String tureword=  userDb.selectUserKeyword(phone);
         if (pwd.equals(tureword)){
             Log.d("验证成功","登录");
             //将 user 对象所有信息复制给 全局user
             User user=new User();
             MyApplication myApplication=MyApplication.getInstance(this);

             Cursor cursor= userDb.selectUserMessage(phone);
             if(cursor.moveToFirst()){
                 do{
                     user.setAccount(cursor.getString(cursor.getColumnIndex("userphone")));
                     user.setPassword(cursor.getString(cursor.getColumnIndex("userKeyword")));
                     user.setNicheng("张三");
                 }while(cursor.moveToNext());
             }
             if(cursor!=null){
                 cursor.close();
             }

             myApplication.setUser(user);
             setResult(RESULT_OK);
             finish();
         }
         else {
             Toast.makeText(this,"密码错误",Toast.LENGTH_LONG).show();
         }

    }




}

