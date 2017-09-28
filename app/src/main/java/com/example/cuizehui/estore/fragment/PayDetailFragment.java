package com.example.cuizehui.estore.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cuizehui.estore.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PayDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PayDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayDetailFragment extends DialogFragment {

      private static final String ARG_PARAM1 = "param1";
     private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String oderprice;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView needpayprice;
    private TextView payprice;
    private RelativeLayout rePayWay;
    private LinearLayout LinPayWay;
    private RelativeLayout rePayDetail;
    private RelativeLayout reBalance;
    private RelativeLayout reBalance2;
    private TextView payway_tt;
    private TextView tvbalance;
    private TextView tvbalance2;
    private Button btnPay;

    private Boolean payResult=false;

    public PayDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PayDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayDetailFragment newInstance(String param1, String param2) {
        PayDetailFragment fragment = new PayDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            oderprice = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_pay_detail);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() * 3 / 5;
        window.setAttributes(lp);

        initView(dialog );
        initEvent(dialog);
        return dialog;
    }



    private void initView(Dialog dialog ) {
        payprice=  dialog.findViewById(R.id.pay_price);
        payprice.setText(oderprice);
       needpayprice=dialog.findViewById(R.id.needpay_price);
        needpayprice.setText(oderprice);
        rePayWay = dialog.findViewById(R.id.re_pay_way);
        LinPayWay =  dialog.findViewById(R.id.lin_pay_way);//付款方式
        rePayDetail =  dialog.findViewById(R.id.re_pay_detail);//付款详情
        reBalance = (RelativeLayout) dialog.findViewById(R.id.re_balance);//付款方式（余额）
        reBalance2 = (RelativeLayout) dialog.findViewById(R.id.re_balance2);//付款方式（余额）
        payway_tt=dialog.findViewById(R.id.pay_way_tt);
        tvbalance=dialog.findViewById(R.id.tv_balance);
        tvbalance2=dialog.findViewById(R.id.tv_balance2);
        btnPay = (Button) dialog.findViewById(R.id.btn_confirm_pay);



    }
    private void initEvent(final Dialog dialog) {

        final Animation slide_left_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left);
        final Animation slide_right_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_to_left);
        final Animation slide_left_to_right = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_right);
        final Animation slide_left_to_left_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left_in);


        rePayWay.setOnClickListener(new View.OnClickListener() {

         @Override
        public void onClick(View view) {
            rePayDetail.startAnimation(slide_left_to_left);
            rePayDetail.setVisibility(View.GONE);
            LinPayWay.startAnimation(slide_right_to_left);
            LinPayWay.setVisibility(View.VISIBLE);

        }
    });

       reBalance.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             //支付方式改变
             payway_tt.setText(tvbalance.getText());
             payResult=true;
             rePayDetail.startAnimation(slide_left_to_left_in);
             rePayDetail.setVisibility(View.VISIBLE);
             LinPayWay.startAnimation(slide_left_to_right);
             LinPayWay.setVisibility(View.GONE);
         }
     });
        reBalance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //支付方式改变
                payway_tt.setText(tvbalance2.getText());
                rePayDetail.startAnimation(slide_left_to_left_in);
                rePayDetail.setVisibility(View.VISIBLE);
                LinPayWay.startAnimation(slide_left_to_right);
                LinPayWay.setVisibility(View.GONE);
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //回传给Activity状态值

                onButtonPressed(payResult.toString());
                dialog.dismiss();
            }
        });
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String uri);
    }





}
