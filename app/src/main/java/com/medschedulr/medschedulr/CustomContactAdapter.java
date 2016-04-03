package com.medschedulr.medschedulr;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomContactAdapter extends BaseAdapter {

    Cursor cursor;
    Context mContext;
    LayoutInflater mInflater;

    public CustomContactAdapter(Context context, Cursor cursor) {
        mContext = context;
        this.cursor = cursor;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView contact_name, contact_number;
        View view = convertView;
        cursor.moveToPosition(position);

        if (view == null) {
            view.inflate(mContext, R.layout.activity_contacts, null);
        }
        contact_name = (TextView) view
                .findViewById(R.id.contact_name);
        contact_number = (TextView) view
                .findViewById(R.id.contact_number);
        contact_name.setText(cursor.getString(
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
        contact_number.setText(
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

        return view;
    }
}