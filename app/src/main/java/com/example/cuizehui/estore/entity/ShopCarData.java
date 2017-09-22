package com.example.cuizehui.estore.entity;

/**
 * Created by cuizehui on 17-9-21.
 */

public class ShopCarData {
    private String shopname;
    private String producename;
    private String number;
    //增加是不是isfirst 标记
    private int isFirst = 2;

    private boolean isSelect = true;

    private boolean isShopSelect = true;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isShopSelect() {
        return isShopSelect;
    }

    public void setShopSelect(boolean shopSelect) {
        isShopSelect = shopSelect;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getProducename() {
        return producename;
    }

    public void setProducename(String producename) {
        this.producename = producename;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
