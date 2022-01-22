package com.example.mynumber1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView2;
    Button button1;
    EditText editText1;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);
        editText1 = (EditText) findViewById(R.id.edittext1);
        button1 = (Button) findViewById(R.id.button1);
        viewAlldata();

    }

    private void viewAlldata() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                try {

                    String number[] = checked(editText1.getText().toString());
                    Cursor res;
                    for (int i=0; i<number.length; i++) {
                        res = myDB.getData(String.valueOf(number[i]));
                        if (res.getCount() != 0) {
                            while (res.moveToNext()) {
                                buffer.append("Title :" + res.getString(1) + "\n" +
                                        "Detail : " + res.getString(2) + "\n\n");
                            }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Please input number", Toast.LENGTH_LONG).show();
                } finally {
                    textView2.setText(buffer.toString());
                }
                }
        });
    }

    private String[] checked(String telephone) {
        String telArr[] = new String[7];
        for (int i=3; i<=8; i++ ) {
            telArr[i-3] = telephone.substring(i, i+2).toString();
        }
        return telArr;
    }
}