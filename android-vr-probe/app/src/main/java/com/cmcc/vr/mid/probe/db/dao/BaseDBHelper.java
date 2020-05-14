package com.cmcc.vr.mid.probe.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteTransactionListener;

/**
 * BaseDBHelper
 *
 * @Type BaseDBHelper.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:49
 */
public abstract class BaseDBHelper extends SQLiteOpenHelper implements SQLiteTransactionListener {
    private static final String TAG = "BaseDBHelper";
    private final ThreadLocal<Boolean> threadLocal = new ThreadLocal();
    protected SQLiteDatabase mDb;

    protected abstract void createTablesInTransaction();

    protected abstract int deleteInTransaction(String str, String str2, String[] strArr);

    protected abstract void dropTablesInTransaction();

    protected abstract long insertInTransaction(String tableName, String str2,
                                                ContentValues contentValues);

    protected abstract Cursor queryInTransaction(String str, String[] strArr, String str2,
                                                 String[] strArr2, String str3, String str4,
                                                 String str5);

    protected abstract Cursor rawQueryInTransaction(String str);

    protected abstract int updateInTransaction(String str, ContentValues contentValues, String str2,
                                               String[] strArr);

    public BaseDBHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    private boolean a() {
        return threadLocal.get() != null && (threadLocal.get()).booleanValue();
    }

    protected long insert(String tableName, ContentValues contentValues) {
        long j = -1;
        if (a()) {
            return insertInTransaction(tableName, null, contentValues);
        }
        mDb = getWritableDatabase();
        if (mDb != null) {
            mDb.beginTransactionWithListener(this);

            j = insertInTransaction(tableName, null, contentValues);

            mDb.setTransactionSuccessful();
            mDb.endTransaction();
        }

        return j;
    }

    protected int update(String str, ContentValues contentValues, String aConditions, String[] aParams) {
        int i = -1;
        if (a()) {
            return updateInTransaction(str, contentValues, aConditions, aParams);
        }
        mDb = getWritableDatabase();
        if (mDb != null) {
            mDb.beginTransactionWithListener(this);

            i = updateInTransaction(str, contentValues, aConditions, aParams);

            mDb.setTransactionSuccessful();
            mDb.endTransaction();
        }
        return i;
    }

    protected int delete(String str, String str2, String[] strArr) {
        int i = -1;
        if (a()) {
            return deleteInTransaction(str, str2, strArr);
        }
        mDb = getWritableDatabase();

        if (mDb != null) {
            mDb.beginTransactionWithListener(this);

            i = deleteInTransaction(str, str2, strArr);

            mDb.setTransactionSuccessful();
            mDb.endTransaction();
        }

        return i;
    }

    protected Cursor rawQuery(String str) {
        Cursor cursor = null;
        if (a()) {
            return rawQueryInTransaction(str);
        }
        mDb = getWritableDatabase();

        if (mDb != null) {
            mDb.beginTransactionWithListener(this);

            cursor = rawQueryInTransaction(str);

            mDb.setTransactionSuccessful();
            mDb.endTransaction();
        }
        return cursor;
    }

    protected Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3,
                           String str4, String str5) {
        Cursor cursor = null;
        if (a()) {
            return queryInTransaction(str, strArr, str2, strArr2, str3, str4, str5);
        }
        mDb = getWritableDatabase();

        if (mDb != null) {
            mDb.beginTransactionWithListener(this);

            cursor = queryInTransaction(str, strArr, str2, strArr2, str3, str4, str5);
            mDb.setTransactionSuccessful();
            mDb.endTransaction();
        }

        return cursor;
    }

    protected void dropTables() {
        if (!a()) {
            mDb = getWritableDatabase();
        }
        dropTablesInTransaction();
    }

    protected void createTables() {
        if (!a()) {
            mDb = getWritableDatabase();
        }
        createTablesInTransaction();
    }
}
