package com.example.cuizehui.estoredataservice;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by cuizehui on 17-9-19.
 */

public class ShopDaTa extends DataSupport implements Parcelable {
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

    byte[] bitmapbig ;

    protected ShopDaTa(Parcel in) {
        picadress = in.readString();
        productdic = in.readString();
        shopName = in.readString();
        productName = in.readString();
        picsmalladress = in.readString();
        price = in.readString();
        mailprice = in.readString();
        bitmaps = in.createByteArray();
        bitmapbig = in.createByteArray();
    }

    public static final Creator<ShopDaTa> CREATOR = new Creator<ShopDaTa>() {
        @Override
        public ShopDaTa createFromParcel(Parcel in) {
            return new ShopDaTa(in);
        }

        @Override
        public ShopDaTa[] newArray(int size) {
            return new ShopDaTa[size];
        }
    };

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

    public ShopDaTa() {
    }









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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(picadress);
        parcel.writeString(productdic);
        parcel.writeString(shopName);
        parcel.writeString(productName);
        parcel.writeString(picsmalladress);
        parcel.writeString(price);
        parcel.writeString(mailprice);
        parcel.writeByteArray(bitmaps);
        parcel.writeByteArray(bitmapbig);
    }
}

