package com.example.myprozhepayanterm;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddHomework extends AppCompatActivity {
TimePicker timePicker;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        timePicker.setIs24HourView(true);
showTime();
    }


    public void showTime()
    {
        timePicker = findViewById(R.id.timePicker);
        btn = findViewById(R.id.btnset);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"a "+timePicker.getCurrentHour(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
