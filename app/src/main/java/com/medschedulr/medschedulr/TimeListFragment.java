package com.medschedulr.medschedulr;

import android.app.ListFragment;
import android.content.CursorLoader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class TimeListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private EditText medItem;
    private String[] mArr;
    ArrayAdapter<String> mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        medItem = (EditText) getActivity().findViewById(R.id.id_medication_item);


        CursorLoader mCursor = new CursorLoader(getActivity(), MedContract.MedTable.CONTENT_URI,
                new String[]{ MedContract.MedTable.COL_FREQUENCY, MedContract.MedTable.COL_TIMES,
                        MedContract.MedTable._ID }, MedContract.MedTable.COL_MEDICATION + " = ? ", new String[]
                { medItem.getText().toString() }, MedContract.MedTable._ID + " ASC");

        String vals = mCursor.loadInBackground().getString(0);
        if (vals != null) {
            mArr = vals.split(",");
        }

        mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_time, mArr);
        setListAdapter(mAdapter);

        return inflater.inflate(R.layout.fragment_timelist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
