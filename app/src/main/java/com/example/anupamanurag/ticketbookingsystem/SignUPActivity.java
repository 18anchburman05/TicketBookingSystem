package com.example.anupamanurag.ticketbookingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends AppCompatActivity {
        EditText e1,e2,e3;
        Button b1;
        com.example.anupamanurag.ticketbookingsystem.DataBaseAdapter databaseadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        databaseadapter=new com.example.anupamanurag.ticketbookingsystem.DataBaseAdapter(this);
        databaseadapter=databaseadapter.open();

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        b1=(Button)findViewById(R.id.button10);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String a = e1.getText().toString();
                String b = e2.getText().toString();
                String c = e3.getText().toString();

                if (a.equals("") || b.equals("") || c.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if (!b.equals(c)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // Save the Data in Database
                    databaseadapter.insertEntry(a, b);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUPActivity.this, SigninActivity.class);
                    startActivity(i);
                }


            }
        });
    }
    }


