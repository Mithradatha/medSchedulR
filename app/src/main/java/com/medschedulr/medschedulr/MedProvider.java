package com.medschedulr.medschedulr;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MedProvider extends ContentProvider {

    private static final int MED_LIST = 1;
    private static final int MED_ID = 2;

    private static final UriMatcher URI_MATCHER;

    private MedHelper mHelper = null;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(MedContract.AUTHORITY, "meds", MED_LIST);
        URI_MATCHER.addURI(MedContract.AUTHORITY, "meds/#", MED_ID);
    }

    @Override
    public boolean onCreate() {
        mHelper = new MedHelper(getContext(), MedHelper.DB_NAME, null, MedHelper.DB_VERSION);
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        int nRecordsDeleted = 0;

        switch(URI_MATCHER.match(uri)) {
            case MED_LIST:
                nRecordsDeleted = db.delete(MedContract.MedTable.TBL_NAME, selection, selectionArgs);
                break;
            case MED_ID:
                String where = MedContract.MedTable._ID + " = " + uri.getLastPathSegment();
                if (!TextUtils.isEmpty(selection))
                    where += " AND " + selection;
                nRecordsDeleted = db.delete(MedContract.MedTable.TBL_NAME, where, selectionArgs);
        }

        if (nRecordsDeleted > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return nRecordsDeleted;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        SQLiteQueryBuilder mBuilder = new SQLiteQueryBuilder();
        mBuilder.setTables(MedContract.MedTable.TBL_NAME);

        switch (URI_MATCHER.match(uri)) {
            case MED_LIST:
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = MedContract.MedTable.DEFAULT_SORT_ORDER;
                break;
            case MED_ID:
                mBuilder.appendWhere(MedContract.MedTable._ID + " = " + uri.getLastPathSegment());
        }

        Cursor mCursor = mBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        mCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return mCursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case MED_LIST:
                return MedContract.MedTable.CONTENT_LIST_TYPE;
            case MED_ID:
                return MedContract.MedTable.CONTENT_ID_TYPE;
            default:
                return "Oops!";
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long mID = db.insert(MedContract.MedTable.TBL_NAME, null, values);
        if (mID > 0) {
            Uri idURI = ContentUris.withAppendedId(uri, mID);
            getContext().getContentResolver().notifyChange(idURI, null);
            return idURI;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        int nRecordsUpdated = 0;

        switch (URI_MATCHER.match(uri)) {
            case MED_LIST:
                nRecordsUpdated = db.update(MedContract.MedTable.TBL_NAME, values, selection, selectionArgs);
                break;
            case MED_ID:
                String where = MedContract.MedTable._ID + " = " + uri.getLastPathSegment();
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                nRecordsUpdated = db.update(MedContract.MedTable.TBL_NAME, values, where, selectionArgs);
        }
        if (nRecordsUpdated > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return nRecordsUpdated;
    }
}
