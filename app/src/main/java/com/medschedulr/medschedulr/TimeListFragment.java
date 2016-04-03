package com.medschedulr.medschedulr;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class TimeListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText medItem;
    private String med;
    private android.app.LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private SimpleCursorAdapter mAdapter;

    private static final int LOADER_ID = 2;

    private static String[] from = { MedContract.MedTable.COL_TIMES, MedContract.MedTable.COL_FREQUENCY,
            MedContract.MedTable._ID };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        medItem = (EditText)getActivity().findViewById(R.id.id_medication_item);
        med = medItem.getText().toString();

        int[] to = { R.id.id_timelistitem };

        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.custom_time, null,
                from, to, 0);

        setListAdapter(mAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MedHandler mHandler = new MedHandler();
                MedHandler.Delete mDelete = mHandler.new Delete(parent.getContext());
                mDelete.execute(id);
                return true;
            }
        });

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ContentValues values = new ContentValues();
                values.put(MedContract.MedTable.COL_TIMES, "UPDATED");

                MedHandler mHandler = new MedHandler();
                MedHandler.Update mUpdate = mHandler.new Update(parent.getContext());
                mUpdate.execute(new MyParamArgs(id, values));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timelist, container, false);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), MedContract.MedTable.CONTENT_URI, from,
                MedContract.MedTable.COL_MEDICATION + " ? ", new String[]{med},
                MedContract.MedTable._ID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        String[] mArr = data.getString(0).split(",");
        mAdapter.changeCursorAndColumns(data, mArr, new int[]{R.id.id_timelistitem});
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
