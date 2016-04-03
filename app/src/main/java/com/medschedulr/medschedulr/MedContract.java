package com.medschedulr.medschedulr;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MedContract {

    private MedContract() {}

    public static final String AUTHORITY = "com.medschedulr.medschedulr.provider";
    public static final String SCHEME = "content://";

    public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY);

    public static final class MedTable implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(MedContract.CONTENT_URI, "meds");

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "vnd.medschedulr.medschedulr.meds";

        public static final String CONTENT_ID_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "vnd.medschedulr.medschedulr.meds";

        public static final String TBL_NAME = "medTable";

        public static final String COL_MEDICATION = "_medication";
        public static final String COL_MULTIPLIER = "_multiplier";
        public static final String COL_UNIT = "_unit";
        public static final String COL_DAYSOFWEEK = "_daysofweek";
        public static final String COL_FREQUENCY = "_frequency";
        public static final String COL_TIMES = "_times";
        public static final String COL_STARTDATE = "_startdate";
        public static final String COL_ENDDATE = "_enddate";
        public static final String COL_PRIORITY = "_priority";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TBL_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MEDICATION + " TEXT, " +
                COL_MULTIPLIER + " INTEGER, " +
                COL_UNIT + " TEXT, " +
                COL_DAYSOFWEEK + " TEXT, " +
                COL_FREQUENCY + " TEXT, " +
                COL_TIMES + " INTEGER, " +
                COL_STARTDATE + " TEXT, " +
                COL_ENDDATE + " TEXT, " +
                COL_PRIORITY + " TEXT"+
                " );";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " +
                TBL_NAME + ";";

        public static final String[] PROJECTION_ALL = { _ID, COL_MEDICATION,
            COL_MULTIPLIER, COL_UNIT, COL_DAYSOFWEEK, COL_FREQUENCY, COL_TIMES,
            COL_STARTDATE, COL_ENDDATE, COL_PRIORITY };

        public static final String DEFAULT_SORT_ORDER = COL_PRIORITY + " ASC";
    }
}
