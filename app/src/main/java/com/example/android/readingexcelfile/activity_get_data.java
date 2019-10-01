package com.example.android.readingexcelfile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_get_data extends AppCompatActivity {
    Button nextbutton, previousbutton,Call,callhis ;

    EditText Name, phoneNumber, Sim;

    SQLiteDatabase SQLITEDATABASE ;

    String GetSQliteQuery, UpdateRecordQuery, DeleteQuery ;

    Cursor cursor ;

    TextView idtextview;

    Boolean CheckEditTextEmpty;

    String GetName,GetPhoneNumber,GetSim ;

    int UserID ;

    String ConvertUserID ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        callhis=(Button)findViewById(R.id.button3);
        nextbutton = (Button)findViewById(R.id.button1);
        previousbutton = (Button)findViewById(R.id.button2);
        Call = (Button) findViewById(R.id.button8);
        Name = (EditText)findViewById(R.id.editText3);
        phoneNumber = (EditText)findViewById(R.id.editText2);
        Sim = (EditText)findViewById(R.id.editText1);

        idtextview = (TextView)findViewById(R.id.textview1);

        GetSQliteQuery = "SELECT * FROM users" ;

        SQLITEDATABASE = openOrCreateDatabase("details", Context.MODE_PRIVATE, null);

        cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);

        cursor.moveToFirst();

        GetSQLiteDatabaseRecords();
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber();
            }
        });


        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!cursor.isLast()){

                    cursor.moveToNext();
                }

                GetSQLiteDatabaseRecords();

            }
        });

        previousbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!cursor.isFirst()){

                    cursor.moveToPrevious();
                }
                GetSQLiteDatabaseRecords();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            }
        }
    }
    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity_get_data.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
                startActivity(callIntent);
                Intent intent=new Intent(activity_get_data.this,callstate.class);
                startActivity(intent);

            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void GetSQLiteDatabaseRecords(){

        idtextview.setText(cursor.getString(0));

        Name.setText(cursor.getString(3).toString());

        phoneNumber.setText(cursor.getString(2).toString());

        Sim.setText(cursor.getString(1).toString());
    }

    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String sim ){

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(sim)){

            CheckEditTextEmpty = true ;

        }
        else {
            CheckEditTextEmpty = false ;
        }
    }

    public void his(View view) {
        Intent mainIntent = new Intent(activity_get_data.this,history.class);
        startActivity(mainIntent);
    }

    public void recorder(View view) {
        Intent mainIntent = new Intent(activity_get_data.this,autocallrecord.class);
        startActivity(mainIntent);
    }
}
