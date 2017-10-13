package com.example.cuizehui.estore.entity;

/**
 * Created by cuizehui on 17-10-13.
 * 抢购物品数据
 */

public class FlashPruductData {
    String productname;
    String oldprice;
    String flashprice;
    byte[] pic;
    String startData;
    String restTime;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public String getFlashprice() {
        return flashprice;
    }

    public void setFlashprice(String flashprice) {
        this.flashprice = flashprice;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }
}
