package com.example.android.readingexcelfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class insertret extends AppCompatActivity {
    EditText GetName,GetPhoneNumber,GetSim ;
    Button Submit, ShowValues;
    SQLiteDatabase SQLITEDATABASE;
    String Name, PhoneNumber, Sim ;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertret);
        GetName = (EditText)findViewById(R.id.editText3);

        GetPhoneNumber = (EditText)findViewById(R.id.editText2);

        GetSim = (EditText)findViewById(R.id.editText1);

        Submit = (Button)findViewById(R.id.button1);

        ShowValues = (Button)findViewById(R.id.button2);

        Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                DBCreate();

                SubmitData2SQLiteDB();

            }
        });
        ShowValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(insertret.this,activity_get_data.class);
                startActivity(intent);
            }
        });


    }


    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("details", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name VARCHAR, phoneNumber VARCHAR, Sim VARCHAR);");
    }

    public void SubmitData2SQLiteDB(){

        Name = GetName.getText().toString();

        PhoneNumber = GetPhoneNumber.getText().toString();

        Sim = GetSim.getText().toString();

        CheckEditTextIsEmptyOrNot( Name,PhoneNumber, Sim);

        if(CheckEditTextEmpty == true)
        {

            SQLiteQuery = "INSERT INTO users (Name,phoneNumber,Sim) VALUES('"+Name+"', '"+PhoneNumber+"', '"+Sim+"');";

            SQLITEDATABASE.execSQL(SQLiteQuery);

            Toast.makeText(insertret.this,"Data Submit Successfully", Toast.LENGTH_LONG).show();

            ClearEditTextAfterDoneTask();

        }
        else {

            Toast.makeText(insertret.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
        }
    }

    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String sim ){

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(sim)){

            CheckEditTextEmpty = false ;

        }
        else {
            CheckEditTextEmpty = true ;
        }
    }

    public void ClearEditTextAfterDoneTask(){

        GetName.getText().clear();
        GetPhoneNumber.getText().clear();
        GetSim.getText().clear();

    }

}