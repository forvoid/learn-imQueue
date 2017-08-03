package com.forvoid.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by forvoid on 2017/8/2.
 */
public class Order implements Serializable{

    private static final long serialVersionUID = -6444649995425266635L;

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", startTime=" + startTime +
                '}';
    }

    private int oid;
    private String name;
    private String code;
    private Date  startTime;



    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
