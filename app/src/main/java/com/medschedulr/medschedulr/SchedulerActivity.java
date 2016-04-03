package com.medschedulr.medschedulr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SchedulerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
    }

    public void onAddMed(View view) {
        EditText medication = (EditText)findViewById(R.id.id_edit_medication);
        EditText multiplier = (EditText)findViewById(R.id.id_edit_multiplier);
        EditText unit = (EditText)findViewById(R.id.id_edit_unit);
        EditText priority = (EditText)findViewById(R.id.id_edit_priority);

        medication.getText().toString();
    }
}
