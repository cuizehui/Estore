package com.example.cuizehui.estore.databaseutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cuizehui on 17-9-16.
 */

public class UserDbHelper extends SQLiteOpenHelper {
    //用户表
    final  String CREATE_USER_TABLE="create TABLE usermassage(" +
            //用户名
            "  userphone    string  primary key  ," +
            //用户密码
            "  userKeyword  string  )";
    //用户购物车表
    final  String CREATE_UserShopCar_TABLE="create TABLE usershopcar(" +
            //用户名
            "  userphone    string," +
            //商品名
            "productname  string," +
            //商品个数
            "number string," +
            //商品价格
            "price string," +
            "storename string, primary key(userphone,productname))";


    public UserDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      // 用户表
             sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        //用户购物车表
             sqLiteDatabase.execSQL(CREATE_UserShopCar_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
