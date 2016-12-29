package com.softwise.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softwise.dto.Account;

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
                new String[]{a.getAccaction(),a.getAccmoney(),a.getAcclist(),a.getAccsay(),a.getAcctime()});
    }
    //修改数据
    public void update(Account a){
        SQLiteDatabase db=myOpenDBHelper.getWritableDatabase();
        db.execSQL("UPDATE account SET accaction = ?,accmoney = ?,acclist = ?,accsay = ? WHERE accid = ?",
                new String[]{a.getAccaction(),a.getAccmoney(),a.getAcclist(),a.getAccsay(),a.getAccid().toString()});
    }
    //删除数据
    public void delect(Integer aid){
        SQLiteDatabase db=myOpenDBHelper.getWritableDatabase();
        db.execSQL("DELETE FROM account WHERE accid = ?",
                new String[]{aid.toString()});
    }
    //查询数据
    public List<Account> excute(Integer aid){
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM account WHERE accid = ?",
                new String[]{aid.toString()});
        List<Account> list=new ArrayList<>();
        while (cursor.moveToNext()){
            int accid=cursor.getInt(cursor.getColumnIndex("accid"));
            String accaction=cursor.getString(cursor.getColumnIndex("accaction"));
            String accmoney=cursor.getString(cursor.getColumnIndex("accmoney"));
            String acclist=cursor.getString(cursor.getColumnIndex("acclist"));
            String accsay=cursor.getString(cursor.getColumnIndex("accsay"));
            String acctime=cursor.getString(cursor.getColumnIndex("acctime"));
            list.add(new Account(accid,accaction,accmoney,acclist,accsay,acctime));
        }
        cursor.close();
        return list;
    }
    //查询所有数据
    public List<Account> excuteAll(){
        SQLiteDatabase db=myOpenDBHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM account ",null);
        List<Account> list=new ArrayList<>();
        while (cursor.moveToNext()){
            int accid=cursor.getInt(cursor.getColumnIndex("accid"));
            String accaction=cursor.getString(cursor.getColumnIndex("accaction"));
            String accmoney=cursor.getString(cursor.getColumnIndex("accmoney"));
            String acclist=cursor.getString(cursor.getColumnIndex("acclist"));
            String accsay=cursor.getString(cursor.getColumnIndex("accsay"));
            String acctime=cursor.getString(cursor.getColumnIndex("acctime"));
            list.add(new Account(accid,accaction,accmoney,acclist,accsay,acctime));
        }
        cursor.close();
        return list;
    }
}
