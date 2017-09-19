package com.example.cuizehui.estore.databaseutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by cuizehui on 17-9-16.
 */

public class UserDb {
    public static final String DB_NAME = "UserMassage";
    public static final int VERSON = 1;
    private static UserDb userDb;
    private UserDbHelper userDbHelper;
    //打开的数据库对象
    private SQLiteDatabase db;

    //私有化构造方法    单例使这个类只有一个对象
    public UserDb(Context context) {
        userDbHelper = new UserDbHelper(context, DB_NAME, null, VERSON);
        db = userDbHelper.getWritableDatabase();
    }

    public synchronized static UserDb getInstance(Context context) {
        if (userDb == null) {
            userDb = new UserDb(context);
        }
        return userDb;
    }
//

    /**
     * 向用户表插入信息
     * @param clumname
     * @param values
     * @return
     */


    public long insertuserTable( String clumname, String values) {
        long ret = 0;
        db.beginTransaction();
        try {
            db.execSQL("insert into usermassage"+"("+clumname+") values( "+values+ ") ");
            db.setTransactionSuccessful();
        } catch (RuntimeException e) {
            Log.e("insert error.",e.toString());
        } finally {
            db.endTransaction();
        }
        return ret;
    }

    /**
     * 根据用户名获取到所有用户信息的游标集合
     * @param userphone 用户名
     * @return
     */
    public Cursor selectUserMessage(String userphone){
        Cursor cursor= db.rawQuery("select * from usermassage where userphone =?",new String[]{String.valueOf(userphone)});
        return cursor;

    }

    /**
     * 查询用户名所对应的密码
     * @param  userPhone 用户名
     */

    public String selectUserKeyword(String userPhone){
        String keyword = null;

        Cursor userMessagecursor=selectUserMessage(userPhone);
        Log.d("验证","返回行和列的个数"+userMessagecursor.getCount()+userMessagecursor.getColumnCount());
        if(userMessagecursor.moveToFirst()){
            do{
              int    column=userMessagecursor.getColumnIndex("userKeyword");
                keyword=  userMessagecursor.getString(column);
             }while(userMessagecursor.moveToNext());
        }
        if(userMessagecursor!=null){
            userMessagecursor.close();
        }
        return keyword;
    }

    /**
     *
     * @param要更新列的名 clumname 账户
     * @param要更新列的值 values  密码
     ×
     * @return
     */
    public long updataUserTable( String updateclumname, String  updatevalues,String catchclumname,String catchvalues ) {
        long ret = 0;
        db.beginTransaction();
        try {
            //
            db.execSQL("update usermassage set "+ updateclumname+" ="+"\""+updatevalues+"\""+"where "+catchclumname+"="+"\""+catchvalues+"\"");
            db.setTransactionSuccessful();
        } catch (RuntimeException e) {
            Log.e("insert error.",e.toString());
        } finally {
            db.endTransaction();
        }
        return ret;
    }

}