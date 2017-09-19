package com.example.cuizehui.estore.entity;

/**
 * Created by cuizehui on 17-9-19.
 */

public class ShopDaTa {
    //商店名
    String shopName;
    //商品名
    String  productName;
    //图片地址
    String  picadress;
    //商品描述
    String  productdic;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPicadress() {
        return picadress;
    }

    public void setPicadress(String picadress) {
        this.picadress = picadress;
    }

    public String getProductdic() {
        return productdic;
    }

    public void setProductdic(String productdic) {
        this.productdic = productdic;
    }
}
