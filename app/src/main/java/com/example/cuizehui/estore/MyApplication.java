package com.example.cuizehui.estore;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;
//import com.example.cuizehui.estore.interfaces.DaggerApplicationComponent;
import com.example.cuizehui.estore.interfaces.DaggerApplicationComponent;
import com.example.cuizehui.estore.module.ApplicationModule;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.litepal.LitePal;

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
        }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

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

        context.startActivity(intent);
        this.intent = null;
    }

}
