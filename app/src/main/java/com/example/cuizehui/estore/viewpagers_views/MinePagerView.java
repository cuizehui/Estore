package com.example.cuizehui.estore.viewpagers_views;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.LoginActivity;
import com.example.cuizehui.estore.activity.PersonalInfoActivity;
import com.example.cuizehui.estore.activity.SettingActivity;
import com.example.cuizehui.estore.entity.StringFlag;
import com.example.cuizehui.estore.entity.User;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cuizehui on 17-9-14.
 */

public class MinePagerView extends BasePagerView {

    public MinePagerView(MainActivity mainActivity) {
        super(mainActivity);
    }

    TextView textView_Log;
    CircleImageView imgHead;
    User user;
   public    ImageView setting;

    @Override
    public void initView() {
        super.initView();
        basePager_fl.removeAllViews();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        View mineview=inflater.inflate(R.layout.main_viewpager_mine,null);


        textView_Log=mineview.findViewById(R.id.txt_username);
        imgHead=mineview.findViewById(R.id.img_head);

        setting =mineview.findViewById(R.id.mine_logoutiv);

        basePager_fl.addView(mineview);
        Log.d("initview","-!-");

        user = MyApplication.getInstance(mainActivity).getUser();
            if (user == null) {

                textView_Log.setText(R.string.to_login);
            } else {
                textView_Log.setText(user.getNicheng());
               // loadMineData();

            }


    }

    @Override
    protected void initDate() {
        super.initDate();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        textView_Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent =new Intent(mainActivity,LoginActivity.class);
                 //0代表什么？
                mainActivity.startActivityForResult(intent,false,0);
            }
        });



        imgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) {
                    Intent  intent =new Intent(mainActivity, PersonalInfoActivity.class);
                    mainActivity.startActivity(intent);
                } else {
                }
            }
        });

       //退出登录
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mainActivity, SettingActivity.class);
                mainActivity.startActivity(intent);
               MyApplication.getInstance(mainActivity).setUser(null);
            //设置文字为点击登录
            //并刷新界面
             //   textView_Log.setText(R.string.to_login);

            }
        });


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(StringFlag flag){
        if(flag.equals("logout")){
            textView_Log.setText(R.string.to_login);
        }
        else {

        }
    }

}
