package com.example.android.readingexcelfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class conversion extends AppCompatActivity {
Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        finish=(Button)findViewById(R.id.button);
    }

    public void finish(View view) {
        Intent mainIntent = new Intent(conversion.this,insertret.class);
        startActivity(mainIntent);
    }
}
