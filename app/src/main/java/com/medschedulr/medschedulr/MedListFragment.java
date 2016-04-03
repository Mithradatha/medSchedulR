package com.medschedulr.medschedulr;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MedListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private android.app.LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private SimpleCursorAdapter mAdapter;

    private static final int LOADER_ID = 1;

    private static final String[] from = { MedContract.MedTable.COL_MEDICATION,
            MedContract.MedTable.COL_PRIORITY, MedContract.MedTable.COL_MULTIPLIER,
            MedContract.MedTable.COL_UNIT };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText(getString(R.string.medlistfragment_empty));

        int[] to = { R.id.id_medication, R.id.id_priority, R.id.id_multiplier, R.id.id_unit };

        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.custom_medlistfragment, null,
                from, to, 0);

        setListAdapter(mAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medlist, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (getActivity() != null)
            return new CursorLoader(getActivity(), MedContract.MedTable.CONTENT_URI, from,
                    null, null, MedContract.MedTable.DEFAULT_SORT_ORDER);
        else return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (getActivity() != null)
            ((SimpleCursorAdapter)getListAdapter()).swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SimpleCursorAdapter)getListAdapter()).swapCursor(null);
    }
}
