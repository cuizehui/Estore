package com.example.cuizehui.estore.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cuizehui.estore.R;

/**
 * Created by cuizehui on 17-9-28.
 */

public class BottomBarView extends RelativeLayout {



        private Context context;
        private TextView bar_num;
        private int count = 0;


        public BottomBarView(Context context) {
            this(context, null);
        }


        public BottomBarView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }


        public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.context = context;
            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.bottom_bar_view, this, true);
            bar_num = (TextView) rl.findViewById(R.id.bar_num);
            bar_num.setVisibility(GONE);
        }


        public void add() {
            bar_num.setVisibility(VISIBLE);
            count++;
            if (count < 100) {
                bar_num.setText(count + "");
            } else {
                bar_num.setText("99+");
            }
        }


        public void add(int n) throws Exception {
            if(n<0){
                throw new Exception(BottomBarView.class.getSimpleName()+" add(int n).The param must be a positive num");
            }
            bar_num.setVisibility(VISIBLE);
            count += n;
            if (count < 100) {
                bar_num.setText(count + "");
            } else {
                bar_num.setText("99+");
            }
        }


        public void delete() {
            if (count == 0) {
                bar_num.setVisibility(GONE);
            } else {
                count--;
                if (count == 0) {
                    bar_num.setVisibility(GONE);
                } else if (count > 0 && count < 100) {
                    bar_num.setVisibility(VISIBLE);
                    bar_num.setText(count + "");
                } else {
                    bar_num.setVisibility(VISIBLE);
                    bar_num.setText("99+");
                }
            }
        }


        public void deleteAll() {
            count = 0;
            bar_num.setVisibility(GONE);
        }

}
