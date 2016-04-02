package com.medschedulr.medschedulr;

import android.provider.BaseColumns;

public class MedContract {

    private MedContract() {}

    public static final class MedTable implements BaseColumns {

        public static final String TBL_NAME = "medTable";

        public static final String COL_MEDICATION = "_medication";
        public static final String COL_QUANTITY = "_quantity";
        public static final String COL_TYPE = "_type";
        public static final String COL_FREQUENCY = "_frequency";
        public static final String COL_SPAN = "_span";
        public static final String COL_PRIORITY = "_priority";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TBL_NAME + " (" +
                _ID + " PRIMARY KEY AUTOINCREMENT, " +
                COL_MEDICATION + " NOT NULL, " +
                COL_QUANTITY + ", " +
                COL_TYPE + ", " +
                COL_FREQUENCY + ", " +
                COL_SPAN + ", " +
                COL_PRIORITY +
                " );";
    }
}
