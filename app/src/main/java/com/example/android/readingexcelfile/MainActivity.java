package com.example.android.readingexcelfile;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void order(View view) {
        try {
            AssetManager am = getAssets();
            InputStream is = am.open("torab.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();
            String xx = " ";
            for (int i = 0; i < row; i++) {
                for (int c = 0; c < col; c++) {
                    Cell z = s.getCell(c, i);
                    xx = xx + z.getContents();
                }
                xx = xx + "\n";
            }
            display(xx);
        } catch (Exception e) {
        }
    }
    public void display(String value){
        TextView x=(TextView)findViewById(R.id.textView);
        x.setText(value);
        Toast toast=Toast.makeText(getApplicationContext(),"retrieving from excel sheet is done goto below button",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }


    public void e2s(View view) {
        Intent mainIntent = new Intent(MainActivity.this,conversion.class);
        startActivity(mainIntent);
    }
}
