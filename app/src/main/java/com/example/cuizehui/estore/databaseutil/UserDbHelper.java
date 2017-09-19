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


    public UserDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      // 用户表
             sqLiteDatabase.execSQL(CREATE_USER_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
