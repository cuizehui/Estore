package com.example.cuizehui.estore.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by cuizehui on 17-9-25.
 */
//订单对象

public class OrderMessage  extends DataSupport{
   private  String  orderid;
   private  String  username;

   private  String  pdname;

    private String  pdnumber;

    private String  ordertime;
    //订单状态
    private String  orderstatus;
    private String  orderprice;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public String getPdnumber() {
        return pdnumber;
    }

    public void setPdnumber(String pdnumber) {
        this.pdnumber = pdnumber;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }
}
