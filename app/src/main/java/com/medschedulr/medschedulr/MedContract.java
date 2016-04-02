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
        public static final String COL_QUANTITY = "_quantity";
        public static final String COL_TYPE = "_type";
        public static final String COL_FREQUENCY = "_frequency";
        public static final String COL_SPAN = "_span";
        public static final String COL_PRIORITY = "_priority";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TBL_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                COL_MEDICATION + " TEXT NOT NULL, \n" +
                COL_QUANTITY + " REAL, \n" +
                COL_TYPE + " TEXT, \n" +
                COL_FREQUENCY + " TEXT, \n" +
                COL_SPAN + " TEXT, \n" +
                COL_PRIORITY + " TEXT\n"+
                " );";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " +
                TBL_NAME + ";";

        public static final String[] PROJECTION_ALL = { _ID, COL_MEDICATION, COL_QUANTITY,
            COL_TYPE, COL_FREQUENCY, COL_SPAN, COL_PRIORITY };

        public static final String DEFAULT_SORT_ORDER = COL_PRIORITY + " ASC";
    }
}
