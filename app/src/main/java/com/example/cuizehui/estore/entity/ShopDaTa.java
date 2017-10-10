package com.example.cuizehui.estore.entity;

import java.io.Serializable;

/**
 * Created by cuizehui on 17-9-19.
 * 临时实体类 做周转
 *
 */

public class ShopDaTa  implements Serializable{
    //详细图片地址
    String  picadress;
    //商品描述
    String  productdic;
    //商店名
    String shopName;
    //店铺名
    String  productName;
    //缩略图地址
    String  picsmalladress;
    //商品价格
    String price;
    //快递价格
    String mailprice;

    //图片字节数组
    byte[] bitmaps ;

    public byte[] getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(byte[] bitmaps) {
        this.bitmaps = bitmaps;
    }

    public byte[] getBitmapbig() {
        return bitmapbig;
    }

    public void setBitmapbig(byte[] bitmapbig) {
        this.bitmapbig = bitmapbig;
    }

    byte[] bitmapbig ;


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

    public String getPicsmalladress() {
        return picsmalladress;
    }

    public void setPicsmalladress(String picsmalladress) {
        this.picsmalladress = picsmalladress;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMailprice() {
        return mailprice;
    }

    public void setMailprice(String mailprice) {
        this.mailprice = mailprice;
    }
}

