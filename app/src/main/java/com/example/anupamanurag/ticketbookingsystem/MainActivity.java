package com.example.anupamanurag.ticketbookingsystem;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;


public class MainActivity extends Activity
{
    Button b1,b2;
    com.example.anupamanurag.ticketbookingsystem.DataBaseAdapter databaseadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
       databaseadapter=new com.example.anupamanurag.ticketbookingsystem.DataBaseAdapter(this);
        databaseadapter=databaseadapter.open();

        // Get The Refference Of Buttons
        b1=(Button)findViewById(R.id.SIGNUP);
        b2=(Button)findViewById(R.id.SIGNIN);

        // Set OnClick Listener on SignUp button
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent i1 = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivity(i1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                    // TODO Auto-generated method stub

                    /// Create Intent for SignUpActivity  abd Start The Activity
                    Intent i2 = new Intent(getApplicationContext(), SigninActivity.class);
                    startActivity(i2);
                }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
    }
}
