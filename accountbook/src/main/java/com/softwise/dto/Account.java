package com.softwise.dto;

/**
 * Created by softwise on 2016/12/26.
 */

public class Account {
    private Integer accid;
    private String accaction;
    private Double accmoney;
    private String acclist;
    private String accsay;
    private String acctime;
    private String accpic;


    public String getAccpic() {
        return accpic;
    }

    public void setAccpic(String accpic) {
        this.accpic = accpic;
    }

    public Account(Integer accid, String accaction, Double accmoney, String acclist, String accsay, String acctime, String accpic) {
        this.accid = accid;
        this.accaction = accaction;
        this.accmoney = accmoney;
        this.acclist = acclist;
        this.accsay = accsay;
        this.acctime = acctime;
        this.accpic = accpic;
    }

    public Account(String accaction, Double accmoney, String acclist, String accsay, String acctime, String accpic) {
        this.accaction = accaction;
        this.accmoney = accmoney;
        this.acclist = acclist;
        this.accsay = accsay;
        this.acctime = acctime;
        this.accpic = accpic;
    }

    public Account(Integer accid, String accaction, Double accmoney, String acclist, String accsay) {
        this.accid = accid;
        this.accaction = accaction;
        this.accmoney = accmoney;
        this.acclist = acclist;
        this.accsay = accsay;
    }

    public Account(Double accmoney, String acclist, String acctime, String accpic) {
        this.accmoney = accmoney;
        this.acclist = acclist;
        this.acctime = acctime;
        this.accpic = accpic;
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

    public Double getAccmoney() {
        return accmoney;
    }

    public void setAccmoney(Double accmoney) {
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
