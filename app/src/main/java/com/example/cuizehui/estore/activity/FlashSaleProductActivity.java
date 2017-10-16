package com.example.cuizehui.estore.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlashSaleProductActivity extends BaseActivity {
     @BindView(R.id.flashsale_tv_time_line_)
     TextView time_line;

    private Calendar cal;

    private int hourint;
    int   secondint;
    int   minuteint;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    secondint=secondint-1;
                    if(secondint==0){
                         minuteint=minuteint-1;
                         secondint=60;
                    }


                    if(minuteint==0&&secondint==0){
                        hourint=hourint-1;
                        minuteint=60;
                    }
                    String s = null;
                    String m = null;
                    if(secondint<10){
                         s= "0"+secondint;
                    }
                    else{
                        s=""+secondint;
                    }
                    if(minuteint<10){
                         m= "0"+minuteint;
                    }else {
                        m=""+minuteint;
                    }

                    time_line.setText("距离开抢时间 :"+hourint+":"+m+":"+s);

                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_sale_product);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    @Override
    protected void initData() {
        super.initData();
        //获取到差值

        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

   /*     year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH))+1;
        day = String.valueOf(cal.get(Calendar.DATE));*/

        int am_pm=cal.get(Calendar.AM_PM);
         String hour;
        if (am_pm==Calendar.AM){
            hour = String.valueOf(cal.get(Calendar.HOUR));
        }
        else {
             hour = String.valueOf(cal.get(Calendar.HOUR)+12);
        }

            String minute = String.valueOf(cal.get(Calendar.MINUTE));
            String   second = String.valueOf(cal.get(Calendar.SECOND));
            secondint=    Integer.parseInt(second);
            minuteint=Integer.parseInt(minute);
        hourint=Integer.parseInt(hour);





    }

    @Override
    protected void initView() {
        super.initView();
        //计时器显示


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //每一秒 发送一次自己
                Message message=handler.obtainMessage();
                message.what=1;
                message.sendToTarget();
                handler.postDelayed(this,1000);


            }
        });
        thread.start();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
          // 能否 购买  判断 跳转订单

    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {


    }
}
