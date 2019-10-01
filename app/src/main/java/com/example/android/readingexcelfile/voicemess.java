package com.example.android.readingexcelfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class voicemess extends AppCompatActivity {
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voicemess);
        b1=(Button)findViewById(R.id.button7);
        b2=(Button)findViewById(R.id.button9);
    }

    public void play(View view) {
        Toast.makeText(this, "play voice message started by admin", Toast.LENGTH_SHORT).show();
    }

    public void stop(View view) {
        Toast.makeText(this, "play voice message stopped by admin ", Toast.LENGTH_SHORT).show();
    }
}
