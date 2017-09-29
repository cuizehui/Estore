package com.example.cuizehui.estore.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by cuizehui on 17-9-25.
 */
//收货地址对象
public class ShopAdress  extends DataSupport{
    private  String sdname;
    private  String telephone;
    private  String shengfen;
    private  String shi;
    private  String qu;
    private  String dicAdress;
    private  String isfirstAdress;
    private  String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsfirstAdress() {
        return isfirstAdress;
    }

    public void setIsfirstAdress(String isfirstAdress) {
        this.isfirstAdress = isfirstAdress;
    }

    public String getSdname() {
        return sdname;
    }

    public void setSdname(String sdname) {
        this.sdname = sdname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getShengfen() {
        return shengfen;
    }

    public void setShengfen(String shengfen) {
        this.shengfen = shengfen;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public String getDicAdress() {
        return dicAdress;
    }

    public void setDicAdress(String dicAdress) {
        this.dicAdress = dicAdress;
    }
}
