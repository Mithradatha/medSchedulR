package com.medschedulr.medschedulr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "med.db";
    public static final int DB_VERSION = 1;

    public MedHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MedContract.MedTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MedContract.MedTable.DELETE_TABLE);
        onCreate(db);
    }
}
