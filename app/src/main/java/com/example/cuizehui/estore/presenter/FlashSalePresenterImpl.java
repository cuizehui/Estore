package com.example.cuizehui.estore.presenter;

import com.example.cuizehui.estore.entity.FlashPruductData;
import com.example.cuizehui.estore.viewpagers_views.FlashSaleMVPView;

import java.util.ArrayList;

/**
 * Created by cuizehui on 17-10-13.
 * 抢购界面业务逻辑实现类
 */

public class FlashSalePresenterImpl implements  FlashSalePresenter {

  public FlashSaleMVPView flashSaleMVPView;
    @Override
    public void attachView(FlashSaleMVPView view) {
       this.flashSaleMVPView=view;
    }

    @Override
    public void detachView() {
        this.flashSaleMVPView= null;
    }


    /**
     * 获取抢购 数据Arraylist
     */
    @Override
    public void loadRepositories() {
        ArrayList<FlashPruductData> list=new ArrayList<>();
        FlashPruductData flashPruductData1=new FlashPruductData();
        flashPruductData1.setProductname("抢购商品1");
        flashPruductData1.setStartData("上午11点");
        FlashPruductData flashPruductData2=new FlashPruductData();
        flashPruductData2.setProductname("抢购商品2");
        FlashPruductData flashPruductData3=new FlashPruductData();
        FlashPruductData flashPruductData4=new FlashPruductData();
        FlashPruductData flashPruductData5=new FlashPruductData();

        list.add(flashPruductData1);
        list.add(flashPruductData2);
        list.add(flashPruductData3);
        list.add(flashPruductData4);
        list.add(flashPruductData5);
        flashSaleMVPView.reupRepositories(list);


    }


}
