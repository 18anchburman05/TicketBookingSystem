package com.example.anupamanurag.ticketbookingsystem;

//import android.annotation.SuppressLint;
import android.app.DatePickerDialog;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookTicket extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button b1, b2;
    Spinner s1, s2;
    String source, destination;
    int cost = 0;
    TextView t1, t2;
    EditText e1;
    DatePickerDialog.OnDateSetListener date;


    // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book_ticket);



        b1 = (Button) findViewById(R.id.button5);
        b2 = (Button) findViewById(R.id.button6);
        t1 = (TextView) findViewById(R.id.textView5);
        t2 = (TextView) findViewById(R.id.textView6);
        e1 = (EditText) findViewById(R.id.editText6);

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(BookTicket.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {
                month = month + 1;
                String date = dayofmonth + "/" + month + "/" + year;
                t2.setText(date);
                }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(BookTicket.this,TicketDetails.class);
                i1.putExtra("from", source);
                i1.putExtra("to", destination);
                i1.putExtra("fare", t1.getText().toString());
                i1.putExtra("date", t2.getText().toString());
                i1.putExtra("Name", e1.getText().toString());
                startActivity(i1);
                }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(BookTicket.this);
                alert.setMessage("Are you Sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialogg = alert.create();
                dialogg.show();
            }
        });


        s1= (Spinner) findViewById(R.id.Spinner);
        s2 = (Spinner) findViewById(R.id.spinner2);

        List<String> locations = new ArrayList<String>();
        locations.add("Ranchi Junction");
        locations.add("Muri Junction");
        locations.add("Bokaro Steel City");
        locations.add("Dhanbad");
        locations.add("Asansol");
        locations.add("Durgapur");

        source = locations.get(0);
        destination = locations.get(0);

        ArrayAdapter<String> adapterSpinnerSource = new ArrayAdapter<String>(BookTicket.this, android.R.layout.simple_spinner_dropdown_item, locations);
        ArrayAdapter<String> adapterSpinnerDestination = new ArrayAdapter<String>(BookTicket.this, android.R.layout.simple_spinner_dropdown_item, locations);
        s1.setAdapter(adapterSpinnerSource);
        s2.setAdapter(adapterSpinnerDestination);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source = parent.getItemAtPosition(position).toString();
                calculatecost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destination = parent.getItemAtPosition(position).toString();

                calculatecost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void calculatecost() {
        if ((source.equals("Ranchi Junction") && destination.equals("Muri Junction")) || (source.equals("Muri Junction") && destination.equals("Ranchi Junction"))) {
            cost = 10;
        } else if ((source.equals("Ranchi Junction") && destination.equals("Ranchi Junction")) || (source.equals("Ranchi Junction") && destination.equals("Ranchi Junction"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Ranchi Junction") && destination.equals("Bokaro Steel City")) || (source.equals("Bokaro Steel City") && destination.equals("Ranchi Junction"))) {
            cost = 20;
        } else if ((source.equals("Ranchi Junction") && destination.equals("Barauni")) || (source.equals("Dhanbad") && destination.equals("Ranchi Junction"))) {
            cost = 30;
        } else if ((source.equals("Ranchi Junction") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Ranchi Junction"))) {
            cost = 40;
        } else if ((source.equals("Ranchi Junction") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Ranchi Junction"))) {
            cost = 50;
        } else if ((source.equals("Muri Junction") && destination.equals("Ranchi Junction")) || (source.equals("Ranchi Junction") && destination.equals("Muri Junction"))) {
            cost = 10;
        } else if ((source.equals("Muri Junction") && destination.equals("Bokaro Steel City")) || (source.equals("Bokaro Steel City") && destination.equals("Muri Junction"))) {
            cost = 10;
        } else if ((source.equals("Muri Junction") && destination.equals("Muri Junction")) || (source.equals("Muri Junction") && destination.equals("Muri Junction"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Muri Junction") && destination.equals("Dhanbad")) || (source.equals("Dhanbad") && destination.equals("Muri Junction"))) {
            cost = 20;
        } else if ((source.equals("Muri Junction") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Muri Junction"))) {
            cost = 30;
        } else if ((source.equals("Muri Junction") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Muri Junction"))) {
            cost = 40;
        } else if ((source.equals("Bokaro Steel City") && destination.equals("Ranchi Junction")) || (source.equals("Ranchi Junction") && destination.equals("Bokaro Steel City"))) {
            cost = 20;
        } else if ((source.equals("Bokaro Steel City") && destination.equals("Muri Junction")) || (source.equals("Muri Junction") && destination.equals("Bokaro Steel City"))) {
            cost = 10;
        } else if ((source.equals("Bokaro Steel City") && destination.equals("Bokaro Steel City")) || (source.equals("Bokaro Steel City") && destination.equals("Bokaro Steel City"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Bokaro Steel City") && destination.equals("Dhanbad")) || (source.equals("Dhanbad") && destination.equals("Bokaro Steel City"))) {
            cost = 10;
        } else if ((source.equals("Bokaro Steel City") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Bokaro Steel City"))) {
            cost = 20;
        } else if ((source.equals("Bokaro Steel City") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Bokaro Steel City"))) {
            cost = 30;
        } else if ((source.equals("Dhanbad") && destination.equals("Ranchi Junction")) || (source.equals("Ranchi Junction") && destination.equals("Dhanbad"))) {
            cost = 30;
        } else if ((source.equals("Dhanbad") && destination.equals("Muri Junction")) || (source.equals("Muri Junction") && destination.equals("Dhanbad"))) {
            cost = 20;
        } else if ((source.equals("Dhanbad") && destination.equals("Dhanbad")) || (source.equals("Dhanbad") && destination.equals("Dhanbad"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Dhanbad") && destination.equals("Bokaro Steel City")) || (source.equals("Bokaro Steel City") && destination.equals("Dhanbad"))) {
            cost = 10;
        } else if ((source.equals("Dhanbad") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Dhanbad"))) {
            cost = 20;
        } else if ((source.equals("Dhanbad") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Dhanbad"))) {
            cost = 30;
        } else if ((source.equals("Asansol") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Asansol"))) {
            cost = 10;
        } else if ((source.equals("Asansol") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Asansol"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Asansol") && destination.equals("Dhanbad")) || (source.equals("Dhanbad") && destination.equals("Asansol"))) {
            cost = 10;
        } else if ((source.equals("Asansol") && destination.equals("Bokaro Steel City")) || (source.equals("Bokaro Steel City") && destination.equals("Asansol"))) {
            cost = 20;
        } else if ((source.equals("Asansol") && destination.equals("Muri Junction")) || (source.equals("Muri Junction") && destination.equals("Asansol"))) {
            cost = 30;
        } else if ((source.equals("Asansol") && destination.equals("Ranchi Junction")) || (source.equals("Ranchi Junction") && destination.equals("Asansol"))) {
            cost = 40;
        } else if ((source.equals("Durgapur") && destination.equals("Ranchi Junction")) || (source.equals("Ranchi Junction") && destination.equals("Durgapur"))) {
            cost = 50;
        } else if ((source.equals("Durgapur") && destination.equals("Asansol")) || (source.equals("Asansol") && destination.equals("Durgapur"))) {
            cost = 10;
        } else if ((source.equals("Durgapur") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Durgapur"))) {
            Toast.makeText(getApplicationContext(), "Source and destination should not be same!!", Toast.LENGTH_SHORT).show();
        } else if ((source.equals("Durgapur") && destination.equals("Dhanbad")) || (source.equals("Dhanbad") && destination.equals("Durgapur"))) {
            cost = 20;
        } else if ((source.equals("Durgapur") && destination.equals("Bokaro Steel City")) || (source.equals("Dhanbad ") && destination.equals("Durgapur"))) {
            cost = 30;
        } else if ((source.equals("Durgapur") && destination.equals("Muri Junction")) || (source.equals("Muri Junction") && destination.equals("Durgapur"))) {
            cost = 40;
        }
        t1.setText(String.valueOf(cost));
        }
        @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}