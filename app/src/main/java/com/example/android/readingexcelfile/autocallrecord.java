package com.example.android.readingexcelfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.example.android.readingexcelfile.autocallrecord;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class autocallrecord extends AppCompatActivity {
    ToggleButton startandoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocallrecord);
        startandoff = (ToggleButton) findViewById(R.id.Togglebutton2);
        startandoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((ToggleButton) v).isChecked();
                if (checked) {

                    Intent intent1 = new Intent(autocallrecord.this, RecordingService.class);
                    startService(intent1);
                    Toast.makeText(getApplicationContext(), "Call Recording Started", Toast.LENGTH_SHORT).show();


                } else {

                    Intent intent2 = new Intent(autocallrecord.this, RecordingService.class);
                    stopService(intent2);
                    Toast.makeText(getApplicationContext(), "Call Recording Stopped", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}


