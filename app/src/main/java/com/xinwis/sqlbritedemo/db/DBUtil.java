package com.xinwis.sqlbritedemo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.xinwis.sqlbritedemo.model.AccountInfo;
import com.xinwis.sqlbritedemo.db.table.Account;

import rx.schedulers.Schedulers;


public class DBUtil {
    protected static final String TAG = DBUtil.class.getSimpleName();
    private static DBUtil mDbUtil;

    private BriteDatabase mDB;

    private DbOpenHelper mDbOpenHelper;

    private SqlBrite mSqlBrite;


    private DBUtil(Context context) {
        DatabaseContext databaseContext = new DatabaseContext(context);
        mDbOpenHelper = new DbOpenHelper(databaseContext);
         mSqlBrite = new SqlBrite.Builder().build();
        mDB = mSqlBrite.wrapDatabaseHelper(mDbOpenHelper, Schedulers.io());
    }

    public static DBUtil getInstance(Context context) {
        if (mDbUtil == null) {
            mDbUtil = new DBUtil(context);
        }
        return mDbUtil;
    }


    public BriteDatabase getDB() {
        if (mDB == null) {
            mDB = mSqlBrite.wrapDatabaseHelper(mDbOpenHelper, Schedulers.io());
        }
        SQLiteDatabase writableDatabase = mDB.getWritableDatabase();
        return mDB;
    }


    public void clearAccountInfo(){
        mDB = getDB();
        mDB.execute("delete from " + Account.TABLE_NAME + " where 1=1");
        mDB.close();
    }


    public void insertAccountInfo(AccountInfo accountInfo) {
        mDB = getDB();
        mDB.insert(Account.TABLE_NAME,
                Account.FACTORY.marshal()
                        .mOrgId(accountInfo.getmOrgId())
                        .mGrade(accountInfo.getmGrade())
                        .mUserId(accountInfo.getmUserId())
                        .mOrgName(accountInfo.getmOrgName())
                        .mUserName(accountInfo.getmUserName())
                        .mName(accountInfo.getmNickName())
                        .mTelphone(accountInfo.getmTelphone())
                        .mDeptId(accountInfo.getmDeptId())
                        .mDeptName(accountInfo.getmDeptName())
                        .mJobId(accountInfo.getmJobId())
                        .mJobName(accountInfo.getmJobName())
                        .mSex(accountInfo.getmSex())
                        .headImg(accountInfo.getmHeadImg())
                        .asContentValues());
        mDB.close();
    }

    public AccountInfo queryAccountInfo() {
        mDB = getDB();
        AccountInfo accountInfo = null;
        Cursor cursor = mDB.query("select * from " + Account.TABLE_NAME);
        if (cursor != null && cursor.moveToNext()) {
            Account account = Account.MAPPER.map(cursor);
            long mOrgId = account.mOrgId();
            long mGrade = account.mGrade();
            long mUserId = account.mUserId();
            String mOrgName = account.mOrgName();
            String mUserName = account.mUserName();
            String mName = account.mName();
            String mTelphone = account.mTelphone();
            String mDeptId = account.mDeptId();
            String mDeptName = account.mDeptName();
            String mJobId = account.mJobId();
            String mJobName = account.mJobName();
            String mSex = account.mSex();
            String headImg = account.headImg();
            accountInfo = new AccountInfo(mOrgId, mGrade, mUserId, mOrgName, mUserName, mName, mTelphone, mDeptId, mDeptName, mJobId, mJobName, mSex, headImg);
            cursor.close();
            mDB.close();
        }
        return accountInfo;
    }


}