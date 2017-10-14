package com.example.cuizehui.estore.presenter;

import com.example.cuizehui.estore.entity.FlashPruductData;
import com.example.cuizehui.estore.viewpagers_views.FlashSaleMVPView;

/**
 * Created by cuizehui on 17-10-13.
 * 抢购界面业务逻辑
 */

public interface FlashSalePresenter {


    public void attachView(FlashSaleMVPView view) ;

    public void detachView() ;

    /***
     * 加载网络数据
     *
     */
    public void loadRepositories() ;

    /**
     * 跳转至flashSaleProductActivity
     */
    public void jumptoflashSaleProductActivity(FlashPruductData flashPruductData);

}
