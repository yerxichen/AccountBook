package com.softwise.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softwise.dto.Account;
import com.softwise.dto.Money;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by softwise on 2016/12/26.
 */

public class DBUtil {
    private MyOpenDBHelper myOpenDBHelper;

    public DBUtil(MyOpenDBHelper myOpenDBHelper) {
        this.myOpenDBHelper = myOpenDBHelper;
    }

    //插入数据
    public void add(Account a){
        SQLiteDatabase db=myOpenDBHelper.getWritableDatabase();
        db.execSQL("INSERT INTO account(accaction,accmoney,acclist,accsay,acctime,accpic) values(?,?,?,?,?,?)",
                new String[]{a.getAccaction(),a.getAccmoney().toString(),a.getAcclist(),a.getAccsay(),a.getAcctime(),a.getAccpic()});
    }
    //修改数据
    public void update(Account a){
        SQLiteDatabase db=myOpenDBHelper.getWritableDatabase();
        db.execSQL("UPDATE account SET accaction = ?,accmoney = ?,acclist = ?,accsay = ? WHERE accid = ?",
                new String[]{a.getAccaction(),a.getAccmoney().toString(),a.getAcclist(),a.getAccsay(),a.getAccid().toString()});
    }
    //删除数据
    public void delect(Integer aid){
        SQLiteDatabase db=myOpenDBHelper.getWritableDatabase();
        db.execSQL("DELETE FROM account WHERE accid = ?",
                new String[]{aid.toString()});
    }
    //查询某月消费
    public Double thisMonthCost(int year,int month){
        String data=year+"."+month;
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(accmoney) cost FROM account WHERE acctime like ? ",
                new String[]{data+".%"});
        Double moneyCost=0.0;
        while (cursor.moveToNext()){
            moneyCost=cursor.getDouble(cursor.getColumnIndex("cost"));

        }
        cursor.close();
        return moneyCost;
    }
    //查询某月存入
    public Double thisMonthAdd(int year,int month){
        String data=year+"."+month;
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(mcun) cun FROM money WHERE mdata like ? ",
                new String[]{data+".%"});
        Double moneyCun=0.0;
        while (cursor.moveToNext()){
            moneyCun=cursor.getDouble(cursor.getColumnIndex("cun"));
        }
        cursor.close();
        return moneyCun;
    }
    //查询本月数据列表
    public List<Account> thisMonthList(int year,int month){
        String data=year+"."+month;
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT *  FROM account WHERE acctime like ? ",
                new String[]{data+".%"});
        List<Account> list=new ArrayList<>();
        while (cursor.moveToNext()){
            int accid=cursor.getInt(cursor.getColumnIndex("accid"));
            String accaction=cursor.getString(cursor.getColumnIndex("accaction"));
            Double accmoney=cursor.getDouble(cursor.getColumnIndex("accmoney"));
            String acclist=cursor.getString(cursor.getColumnIndex("acclist"));
            String accsay=cursor.getString(cursor.getColumnIndex("accsay"));
            String acctime=cursor.getString(cursor.getColumnIndex("acctime"));
            String accpic=cursor.getString(cursor.getColumnIndex("accpic"));
            list.add(new Account(accid,accaction,accmoney,acclist,accsay,acctime,accpic));
        }
        cursor.close();
        return list;
    }
    //查询总共花费金额
    public Double costAll(){
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(accmoney) cost FROM account ",null);
        Double allCost=0.0;
        while (cursor.moveToNext()){
            allCost=cursor.getDouble(cursor.getColumnIndex("cost"));
        }
        cursor.close();
        return allCost;
    }
    //查询总共存入金额
    public Double cunAll(){
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(mcun) cun FROM money ",null);
        Double allCun=0.0;
        while (cursor.moveToNext()){
            allCun=cursor.getDouble(cursor.getColumnIndex("cun"));
        }
        cursor.close();
        return allCun;
    }
    //新增金额
    public void addMoney(Money m){
        SQLiteDatabase db=myOpenDBHelper.getWritableDatabase();
        db.execSQL("INSERT INTO money(mcun,mdata) values(?,?)",
                new Object[]{m.getMcun(),m.getMdata()});
    }
    //得到某月存入的总金额
    public String selectMonthCun(String dataMonth){
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(mcun) cun FROM money WHERE mdata=?",
                new String[]{dataMonth});
        Double moneyMonth=0.0;
        while (cursor.moveToNext()){
            moneyMonth=cursor.getDouble(cursor.getColumnIndex("cun"));
        }
        cursor.close();
        return moneyMonth.toString();
    }

}
