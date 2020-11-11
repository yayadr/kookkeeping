package com.gs.bookkeeping.main.beans;

import android.app.PendingIntent;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : gengsai
 * date : 2020/10/16
 * description : 
 */
@Entity
public class AccountBean  {
    @Id(autoincrement = true)
    Long id;
    private String cId;
    private String name;
    private String num;
    private String date;
    private Float numF;
    private String des;



    @Generated(hash = 2092566078)
    public AccountBean(Long id, String cId, String name, String num, String date,
            Float numF, String des) {
        this.id = id;
        this.cId = cId;
        this.name = name;
        this.num = num;
        this.date = date;
        this.numF = numF;
        this.des = des;
    }

    @Generated(hash = 1267506976)
    public AccountBean() {
    }


    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getNumF() {
        return numF;
    }

    public void setNumF(Float numF) {
        this.numF = numF;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCId() {
        return this.cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }
}
