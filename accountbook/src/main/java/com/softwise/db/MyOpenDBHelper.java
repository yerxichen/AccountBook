package com.softwise.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by softwise on 2016/12/26.
 */

public class MyOpenDBHelper extends SQLiteOpenHelper {
    public MyOpenDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "my.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE account(accid INTEGER PRIMARY KEY AUTOINCREMENT,accaction VARCHAR(400),accmoney VARCHAR(400),acclist VARCHAR(400),accsay VARCHAR(400))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE account ADD acctime VARCHAR(100) NULL ");
    }
}
