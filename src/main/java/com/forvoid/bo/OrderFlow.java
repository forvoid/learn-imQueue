package com.forvoid.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by forvoid on 2017/8/2.
 */
public class OrderFlow implements Serializable {

    private static final long serialVersionUID = -6530852892900795846L;
    private int ofid;
    private int oid;
    private long createTime;
    private int status;

    @Override
    public String toString()  {
        return "OrderFlow{" +
                "ofid=" + ofid +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }

    public int getOfid() {
        return ofid;
    }

    public void setOfid(int ofid) {
        this.ofid = ofid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}
