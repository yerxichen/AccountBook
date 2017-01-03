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
        db.execSQL("INSERT INTO account(accaction,accmoney,acclist,accsay,acctime) values(?,?,?,?,?)",
                new String[]{a.getAccaction(),a.getAccmoney().toString(),a.getAcclist(),a.getAccsay(),a.getAcctime()});
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
    //查询当月费用数据
    public String thisMonthCost(String year,String month){
        String data=year+"."+month;
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(accmoney) cost FROM account WHERE acctime like ? ",
                new String[]{data+".%"});
        Double moneyCost=0.0;
        while (cursor.moveToNext()){
            moneyCost=cursor.getDouble(cursor.getColumnIndex("cost"));

        }
        cursor.close();
        return moneyCost.toString();
    }
    //查询所有数据
    public List<Account> excuteAll(){
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM account ",null);
        List<Account> list=new ArrayList<>();
        while (cursor.moveToNext()){
            int accid=cursor.getInt(cursor.getColumnIndex("accid"));
            String accaction=cursor.getString(cursor.getColumnIndex("accaction"));
            Double accmoney=cursor.getDouble(cursor.getColumnIndex("accmoney"));
            String acclist=cursor.getString(cursor.getColumnIndex("acclist"));
            String accsay=cursor.getString(cursor.getColumnIndex("accsay"));
            String acctime=cursor.getString(cursor.getColumnIndex("acctime"));
            list.add(new Account(accid,accaction,accmoney,acclist,accsay,acctime));
        }
        cursor.close();
        return list;
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
