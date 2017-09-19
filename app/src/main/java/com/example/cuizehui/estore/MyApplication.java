package com.example.cuizehui.estore;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;
//import com.example.cuizehui.estore.interfaces.DaggerApplicationComponent;
import com.example.cuizehui.estore.interfaces.DaggerApplicationComponent;
import com.example.cuizehui.estore.module.ApplicationModule;

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

}
