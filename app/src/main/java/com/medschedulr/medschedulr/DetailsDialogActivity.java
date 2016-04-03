package com.medschedulr.medschedulr;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailsDialogActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_dialog);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}