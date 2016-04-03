package com.medschedulr.medschedulr;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsActivity extends AppCompatActivity implements
        android.app.LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 9;

    ListView mContacts;
    CustomContactAdapter customContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.id_toolbar_contacts);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.string_title_contacts);

        mContacts = (ListView)findViewById(R.id.id_contacts);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public android.content.CursorLoader onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        data.moveToFirst();
        customContactAdapter = new CustomContactAdapter(this, data);
        mContacts.setAdapter(customContactAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mContacts.setAdapter(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_contacts_help:
                Toast.makeText(this, "CONTACTS SOMEONE HELP ME!", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
