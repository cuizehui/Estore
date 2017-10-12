package com.example.cuizehui.estore;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;
import com.example.cuizehui.estore.interfaces.DaggerApplicationComponent;
import com.example.cuizehui.estore.module.ApplicationModule;
import com.example.cuizehui.estore.uitls.CrashHandler;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.litepal.LitePal;

//import com.example.cuizehui.estore.interfaces.DaggerApplicationComponent;

/**
 * Created by cuizehui on 17-9-13.
 */

public class MyApplication extends Application
{
    private User user;


    //全局单例 注入器 并给其他注入器提供依赖
    private ApplicationComponent appComponent;

        @Override
        public void onCreate() {
            super.onCreate();

            initAppComponent();
            Fresco.initialize(this);
            LitePal.initialize(this);

            //在这里为应用设置异常处理程序，然后我们的程序才能捕获未处理的异常
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(this);

        }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        Log.d("setuser","!!");

    }


    public  void  initAppComponent(){

        appComponent= DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
    public static MyApplication getInstance(Context context){
        return (MyApplication)context.getApplicationContext();
    }


    public ApplicationComponent getAppComponent(){
            return  appComponent;
    }

    public  UserDb getUserDatedb(){
        UserDb userDb=UserDb.getInstance(this);
              return userDb;
    }

    private Intent intent;

    public void putIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return this.intent;
    }
    //跳转至目标activity
    public void jumpToTargetActivity(Context context) {
        //原因就是这里没有 执行startActivityForResult(); 这里缺少返回码
        context.startActivity(intent);
        this.intent = null;
    }
    public void jumpToTargetActivity(int requestCode,MainActivity mainActivity){

        mainActivity.startActivityForResult(intent,requestCode);
         this.intent = null;
    }

}
