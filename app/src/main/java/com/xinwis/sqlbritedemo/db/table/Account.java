package com.xinwis.sqlbritedemo.db.table;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.xinwis.sqlbritedemo.db.AccountModel;

import rx.functions.Func1;


@AutoValue
public abstract class Account implements AccountModel{
    public final static AccountModel.Factory<Account> FACTORY=new AccountModel.Factory<Account>(
            new Creator<Account>() {
                @Override
                public Account create(@Nullable Long mOrgId, @Nullable Long mGrade, @Nullable Long mUserId, @Nullable String mOrgName, @Nullable String mUserName, @Nullable String mName, @Nullable String mTelphone, @Nullable String mDeptId, @Nullable String mDeptName, @Nullable String mJobId, @Nullable String mJobName, @Nullable String mSex, @Nullable String headImg) {
                    return new AutoValue_Account(mOrgId,mGrade,mUserId,mOrgName,mUserName,mName,mTelphone,mDeptId,mDeptName,mJobId,mJobName,mSex,headImg);
                }
            }
    );

    public final static Func1<Cursor,Account> RXMAPPER=new Func1<Cursor, Account>() {
        @Override
        public Account call(Cursor cursor) {
            return MAPPER.map(cursor);
        }
    };
    public static final AccountModel.Mapper<Account> MAPPER=new AccountModel.Mapper<Account>(FACTORY);

}
