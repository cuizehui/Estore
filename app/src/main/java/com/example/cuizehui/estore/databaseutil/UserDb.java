package com.example.cuizehui.estore.databaseutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cuizehui.estore.entity.ShopCarData;

import java.util.ArrayList;

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
     *
     * @param clumname
     * @param values
     * @return
     */


    public long insertuserTable(String clumname, String values) {
        long ret = 0;
        db.beginTransaction();
        try {
            db.execSQL("insert into usermassage" + "(" + clumname + ") values( " + values + ") ");
            db.setTransactionSuccessful();
        } catch (RuntimeException e) {
            Log.e("insert error.", e.toString());
        } finally {
            db.endTransaction();
        }
        return ret;
    }

    /**
     * 向用户表
     * 根据用户名获取到所有用户信息的游标集合
     *
     * @param userphone 用户名
     * @return
     */
    public Cursor selectUserMessage(String userphone) {
        Cursor cursor = db.rawQuery("select * from usermassage where userphone =?", new String[]{String.valueOf(userphone)});
        return cursor;

    }

    /**
     * 向用户表
     * 查询用户名所对应的密码
     *
     * @param userPhone 用户名
     */

    public String selectUserKeyword(String userPhone) {
        String keyword = null;

        Cursor userMessagecursor = selectUserMessage(userPhone);
        Log.d("验证", "返回行和列的个数" + userMessagecursor.getCount() + userMessagecursor.getColumnCount());
        if (userMessagecursor.moveToFirst()) {
            do {
                int column = userMessagecursor.getColumnIndex("userKeyword");
                keyword = userMessagecursor.getString(column);
            } while (userMessagecursor.moveToNext());
        }
        if (userMessagecursor != null) {
            userMessagecursor.close();
        }
        return keyword;
    }

    /**
     * 向用户表
     *
     * @return
     * @param要更新列的名 clumname 账户
     * @param要更新列的值 values  密码
     * ×
     */
    public long updataUserTable(String updateclumname, String updatevalues, String catchclumname, String catchvalues) {
        long ret = 0;
        db.beginTransaction();
        try {
            //
            db.execSQL("update usermassage set " + updateclumname + " =" + "\"" + updatevalues + "\"" + "where " + catchclumname + "=" + "\"" + catchvalues + "\"");
            db.setTransactionSuccessful();
        } catch (RuntimeException e) {
            Log.e("insert error.", e.toString());
        } finally {
            db.endTransaction();
        }
        return ret;
    }


    /**
     * 插入购物车商品信息
     *
     * @param username
     * @param productname
     * @return
     */
    public long insertShopCarTable(String username, String productname, String storename, String number) {
        long ret = 0;
        db.beginTransaction();
        try {
            db.execSQL("insert into usershopcar" + "(userphone,productname,number,storename)values(" + username + ",'" + productname + "'" + "," + number + ",'" + storename + "') ");
            Log.d("insert into usershopcar" + "(userphone,productname,number,storename)values(" + username + ",'" + productname + "'" + ",'" + storename + "'," + number + ") ", "end");
            db.setTransactionSuccessful();
        } catch (RuntimeException e) {
            Log.e("insert error.", e.toString());
        } finally {
            db.endTransaction();
        }
        return ret;
    }


    /**
     * 按商品名和商品用户 更新购物车 商品数量
     *
     * @return
     * @param要更新列的名 clumname 账户
     * @param要更新列的值 values
     * ×
     */
    public long updataSCProductnunber(String username, String productname, String number) {
        long ret = 0;
        db.beginTransaction();
        try {
            //
            db.execSQL("update usershopcar set  number" + " =" + "\"" + number + "\"" + "where userphone=" + username + " and productname= " + "\"" + productname + "\"");
            db.setTransactionSuccessful();
        } catch (RuntimeException e) {
            Log.e("insert error.", e.toString());
        } finally {
            db.endTransaction();
        }
        return ret;
    }


    /**
     * 查询购物车表所有信息
     * 根据用户名获取到所有用户信息的游标集合
     *
     * @param userphone 用户名
     * @return
     */
    public Cursor selectShopCarMessage(String userphone) {
        Cursor cursor = db.rawQuery("select * from usershopcar where userphone =?", new String[]{String.valueOf(userphone)});
        return cursor;

    }

    /**
     * 处理购物车 信息
     *
     * @return ShopCarData 实例集
     */

    public ArrayList<ShopCarData> passShopCarMessage(Cursor shopCarDatacursor) {
        ArrayList<ShopCarData> shopCarDatas = new ArrayList<>();

        Log.d("验证", "返回行和列的个数" + shopCarDatacursor.getCount() + shopCarDatacursor.getColumnCount());
        if (shopCarDatacursor.moveToFirst()) {
            do {
                ShopCarData shopCarData = new ShopCarData();
                int columnname = shopCarDatacursor.getColumnIndex("productname");
                String producename = shopCarDatacursor.getString(columnname);

                int columnnumber = shopCarDatacursor.getColumnIndex("number");
                String number = shopCarDatacursor.getString(columnnumber);

                int columnstorename = shopCarDatacursor.getColumnIndex("storename");
                String storename = shopCarDatacursor.getString(columnstorename);


                shopCarData.setProducename(producename);
                shopCarData.setNumber(number);
                shopCarData.setShopname(storename);


                shopCarDatas.add(shopCarData);
            }
            while (shopCarDatacursor.moveToNext());
        }
        if (shopCarDatacursor != null) {
            shopCarDatacursor.close();
        }

        return shopCarDatas;
    }

    /**
     * 查询 产品从购物车表
     *
     * @param userphone 用户名
     * @return
     */
    public Cursor selectPdfromShopCar(String userphone, String productname) {
        Cursor cursor = db.rawQuery("select * from usershopcar where userphone =? and productname=?", new String[]{String.valueOf(userphone), String.valueOf(productname)});
        Log.d("查询购物车数据验证", "返回行和列的个数" + cursor.getCount() + cursor.getColumnCount());
        return cursor;

    }

    public String Productnumber(String userphone, String productname) {
        Cursor cursor = selectPdfromShopCar(userphone, productname);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            int columnnumber = cursor.getColumnIndex("number");
            Log.d("columnumber:", columnnumber + "");
            String number = cursor.getString(columnnumber);
            return number;


        } else {
            return "0";
        }


    }
}