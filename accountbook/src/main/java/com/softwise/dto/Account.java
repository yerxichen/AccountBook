package com.softwise.dto;

/**
 * Created by softwise on 2016/12/26.
 */

public class Account {
    private Integer accid;
    private String accaction;
    private String accmoney;
    private String acclist;
    private String accsay;
    private String acctime;

    public Account(Integer accid, String accaction, String accmoney, String acclist, String accsay,String acctime) {
        this.accid = accid;
        this.accaction = accaction;
        this.accmoney = accmoney;
        this.acclist = acclist;
        this.accsay = accsay;
        this.acctime = acctime;
    }

    public Account(String accaction, String accmoney, String acclist, String accsay,String acctime) {
        this.accaction = accaction;
        this.accmoney = accmoney;
        this.acclist = acclist;
        this.accsay = accsay;
        this.acctime = acctime;
    }

    public Account(Integer accid, String accaction, String accmoney, String acclist, String accsay) {
        this.accid = accid;
        this.accaction = accaction;
        this.accmoney = accmoney;
        this.acclist = acclist;
        this.accsay = accsay;
    }

    public Integer getAccid() {
        return accid;
    }

    public void setAccid(Integer accid) {
        this.accid = accid;
    }

    public String getAccaction() {
        return accaction;
    }

    public void setAccaction(String accaction) {
        this.accaction = accaction;
    }

    public String getAccmoney() {
        return accmoney;
    }

    public void setAccmoney(String accmoney) {
        this.accmoney = accmoney;
    }

    public String getAcclist() {
        return acclist;
    }

    public void setAcclist(String acclist) {
        this.acclist = acclist;
    }

    public String getAccsay() {
        return accsay;
    }

    public void setAccsay(String accsay) {
        this.accsay = accsay;
    }

    public String getAcctime() {
        return acctime;
    }

    public void setAcctime(String acctime) {
        this.acctime = acctime;
    }


}
