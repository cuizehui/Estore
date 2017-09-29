package com.example.cuizehui.estore.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.LoginActivity;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

public  abstract class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setupActivityComponent(MyApplication.getInstance(this).getAppComponent());
    }



    protected abstract  void setupActivityComponent(ApplicationComponent appComponent);

    protected  void initView(){


    }
    protected void initData(){

    }
    protected  void initEvent(){

    }
//重载获取方法 启动时判断
    public void startActivityForResult(Intent intent,boolean isNeedLogin, int requestCode) {
        if (isNeedLogin) {

            User user = MyApplication.getInstance(this).getUser();
            if (user != null) {
                super.startActivityForResult(intent,requestCode);
            } else {
                MyApplication.getInstance(this).putIntent(intent);
                Intent loginIntent = new Intent(this, LoginActivity.class);
                super.startActivityForResult(loginIntent,requestCode);
            }

        } else {
            super.startActivityForResult(intent,requestCode);
        }
    }

    //重载了这个方法  每当 要跳转到另外一个Activity时，增加一个参数用来判断是否需要登录 如果没登录则会跳转至登录界面
    public void startActivity(Intent intent,Boolean isNeedLogin) {

        if (isNeedLogin) {

            User user = MyApplication.getInstance(this).getUser();
            if (user != null) {
               Log.d("user不为空","！");
                super.startActivity(intent);
            } else {
                Log.d("user为空","！");
                MyApplication.getInstance(this).putIntent(intent);
                Intent loginIntent = new Intent(this, LoginActivity.class);
                super.startActivity(loginIntent);

            }

        } else {
            super.startActivity(intent);
        }
    }

/*// setResult  二次进入界面时  为了得到回传的intent
    public void startActivityForResult(Intent intent, boolean isNeedLogin,int requestCode) {


        if (isNeedLogin) {

            User user = App.getInstance().getUser();
            if (user != null) {
                super.startActivityForResult(intent,requestCode);
            } else {

                App.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                super.startActivityForResult(loginIntent,requestCode);

            }

        } else {
            super.startActivityForResult(intent,requestCode);
        }

    }*/

}
