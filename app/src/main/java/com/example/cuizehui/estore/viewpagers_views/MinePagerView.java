package com.example.cuizehui.estore.viewpagers_views;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.activity.LoginActivity;
import com.example.cuizehui.estore.entity.User;

import butterknife.BindView;
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

   public    ImageView logout;

    @Override
    public void initView() {
        super.initView();
        basePager_fl.removeAllViews();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        View mineview=inflater.inflate(R.layout.main_viewpager_mine,null);


        textView_Log=mineview.findViewById(R.id.txt_username);
        imgHead=mineview.findViewById(R.id.img_head);

        logout=mineview.findViewById(R.id.mine_logoutiv);

        basePager_fl.addView(mineview);
        Log.d("initview","-!-");

        User user = MyApplication.getInstance(mainActivity).getUser();
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

            }
        });

       //退出登录
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Log.d("?????","!!!!!");
              MyApplication.getInstance(mainActivity).setUser(null);
                textView_Log.setText(R.string.to_login);
                mainActivity.refrushShopCarView();
            }
        });

    }
}
