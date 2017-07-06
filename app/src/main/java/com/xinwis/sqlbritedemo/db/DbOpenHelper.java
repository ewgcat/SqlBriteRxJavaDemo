package com.xinwis.sqlbritedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xinwis.sqlbritedemo.db.table.Account;


/**
 * 数据库建表或升级
 * Created by liquanan on 2016/10/3.
 */
public final class DbOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;


    public DbOpenHelper(Context context) {
        super(context, "open" + "xinwis.db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Account.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + Account.TABLE_NAME);
        onCreate(db);
    }
}
