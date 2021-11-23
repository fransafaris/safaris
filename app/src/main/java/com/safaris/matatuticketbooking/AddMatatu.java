package com.safaris.matatuticketbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMatatu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matatu);
        EditText Matatuid = findViewById(R.id.id);
        EditText from = findViewById(R.id.from);
        EditText to = findViewById(R.id.to);
        EditText dt = findViewById(R.id.date);
        EditText seats = findViewById(R.id.seats);
        Button AddMatatu = findViewById(R.id.Addmatatu);
        Button back1 =findViewById(R.id.button2);
        DBHelper DB = new DBHelper(this);


        AddMatatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Matatuid.getText().toString();
                String departure = from.getText().toString();
                String arrival = to.getText().toString();
                String date = dt.getText().toString();
                String total_seats = seats.getText().toString();

                if (id.equals("") || departure.equals("") || arrival.equals("") || date.equals("") || total_seats.equals(""))
                {
                    Toast.makeText(AddMatatu.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkid= DB.checkid(id);
                    if (checkid==false){
                        Boolean insert = DB.insertBus(id,departure,arrival,date,total_seats);
                        if (insert==true){
                            Toast.makeText(AddMatatu.this, "Matatu has been added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Adminpanel.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(AddMatatu.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(AddMatatu.this, "ID already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /*back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminpanel.class);
                startActivity(intent);
            }
        });*/

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminpanel.class);
                startActivity(intent);
            }
        });

    }
}