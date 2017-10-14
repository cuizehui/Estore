package com.example.cuizehui.estore.viewpagers_views;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.entity.FlashPruductData;

import java.util.ArrayList;

/**
 * Created by cuizehui on 17-10-13.
 * 抢购界面 UI逻辑
 */

public interface FlashSaleMVPView {
    public void reupRepositories(ArrayList<FlashPruductData> repositories);

    public MainActivity getMainActivity();
}
