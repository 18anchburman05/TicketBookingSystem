package com.example.anupamanurag.ticketbookingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    private com.example.anupamanurag.ticketbookingsystem.DataBaseAdapter databaseadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        e1 = (EditText) findViewById(R.id.editText6);
        e2 = (EditText) findViewById(R.id.editText8);

        Button b1 = (Button) findViewById(R.id.button2);
        databaseadapter = new com.example.anupamanurag.ticketbookingsystem.DataBaseAdapter(this);
        databaseadapter = databaseadapter.open();
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = e1.getText().toString();
                String password = e2.getText().toString();

                // fetch the Password from database for respective user name
                String storedPassword = databaseadapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if (password.equals(storedPassword)) {
                    Toast.makeText(SigninActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SigninActivity.this, BookTicket.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(SigninActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
