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
import android.widget.SimpleCursorAdapter;

public class MedListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private android.app.LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private SimpleCursorAdapter mAdapter;

    private static final int LOADER_ID = 1;

    private static String[] from = { MedContract.MedTable.COL_MEDICATION,
            MedContract.MedTable.COL_PRIORITY, MedContract.MedTable.COL_MULTIPLIER,
            MedContract.MedTable.COL_UNIT, MedContract.MedTable._ID };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int[] to = { R.id.id_medication, R.id.id_priority, R.id.id_multiplier, R.id.id_unit };

        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.custom_medlistfragment, null,
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
                values.put(MedContract.MedTable.COL_MEDICATION, "UPDATED");

                MedHandler mHandler = new MedHandler();
                MedHandler.Update mUpdate = mHandler.new Update(parent.getContext());
                mUpdate.execute(new MyParamArgs(id, values));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medlist, container, false);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getActivity(), MedContract.MedTable.CONTENT_URI, from,
                    null, null, MedContract.MedTable.DEFAULT_SORT_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
