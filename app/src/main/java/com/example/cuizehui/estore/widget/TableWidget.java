package com.example.cuizehui.estore.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.RemoteViews;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;

/**
 * Created by cuizehui on 17-10-11.
 * //桌面小部件
 */

public class TableWidget extends AppWidgetProvider {


   public  static  final String  ClickAction="com.com.example.cuizehui.estore.widget.Click" ;
    public TableWidget() {
        super();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //对ui进行初始化
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.tablewidget_layout);
        remoteViews.setImageViewResource(R.id.table_imageview,R.drawable.tabl);

        ComponentName componentName=new ComponentName(context,TableWidget.class);


        //设置点击事件
        Intent intentclick=new Intent(context,MainActivity.class);
       // intentclick.setAction(ClickAction);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intentclick,0);
        remoteViews.setOnClickPendingIntent(R.id.table_imageview,pendingIntent);


    /*    //如果时内部 则相当于给自己发送广播  注册好action 后自己发送
        Intent intentaciton=new Intent();
        PendingIntent pendingIntentclick=PendingIntent.getBroadcast(context,0,intentaciton,0);
        remoteViews.setOnClickPendingIntent(R.id.table_imageview,pendingIntentclick);
*/

        appWidgetManager.updateAppWidget(componentName,remoteViews);


    }

    //接受到广播后自己要做什么

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

     if (intent.getAction().equals(ClickAction)){

         AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);

         //对ui进行修改
         RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.tablewidget_layout);
         Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.tabl);
         remoteViews.setImageViewBitmap(R.id.table_imageview,rotateBitmap(context,bitmap,180));

         appWidgetManager.updateAppWidget(new ComponentName(context,TableWidget.class),remoteViews);


     }



    }

    private Bitmap rotateBitmap(Context context,Bitmap srcbBitmap ,float degeree){
        Matrix matrix=new Matrix();
        matrix.reset();
        matrix.setRotate(degeree);
        Bitmap tmpBitmap=Bitmap.createBitmap(srcbBitmap,0,0,srcbBitmap.getWidth(),srcbBitmap.getHeight(),matrix,true);
        return  tmpBitmap;

    }
}
