package com.medschedulr.medschedulr;

import android.content.ContentValues;

public class MyParamArgs {

    private Long mID;
    private ContentValues mValues;

    public MyParamArgs(Long mID, ContentValues mValues) {
        this.mID = mID;
        this.mValues = mValues;
    }

    public Long getLong() { return this.mID; }
    public ContentValues getContentValues() { return this.mValues; }
}
