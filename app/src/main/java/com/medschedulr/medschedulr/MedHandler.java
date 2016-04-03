package com.medschedulr.medschedulr;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

public class MedHandler {

    public class Insert extends AsyncTask<ContentValues, Void, Uri> {

        private Context mContext;

        public Insert(Context context) {
            this.mContext = context;
        }

        @Override
        protected Uri doInBackground(ContentValues... params) {
            return mContext.getContentResolver().insert(MedContract.MedTable.CONTENT_URI, params[0]);
        }
    }

    public class Delete extends AsyncTask<Long, Void, Integer> {

        private Context mContext;

        public Delete(Context context) {
            this.mContext = context;
        }

        @Override
        protected Integer doInBackground(Long... params) {
            return mContext.getContentResolver().delete(MedContract.MedTable.CONTENT_URI,
                    MedContract.MedTable._ID + " = ? ", new String[]{Long.toString(params[0])});
        }
    }

    public class Update extends AsyncTask<MyParamArgs, Void, Integer> {

        private Context mContext;

        public Update(Context context) {
            this.mContext = context;
        }

        @Override
        protected Integer doInBackground(MyParamArgs... params) {
            return mContext.getContentResolver().update(MedContract.MedTable.CONTENT_URI,
                    params[0].getContentValues(), MedContract.MedTable._ID + " = ? ",
                    new String[]{Long.toString(params[0].getLong())});
        }
    }

}
