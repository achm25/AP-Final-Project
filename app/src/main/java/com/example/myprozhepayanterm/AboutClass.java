package com.example.myprozhepayanterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutClass extends AppCompatActivity {
TextView nameTxt;
TextView roomTxt;
TextView depTxt;
myClass myclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_class);
        nameTxt = findViewById(R.id.title_aboutclass);
        roomTxt = findViewById(R.id.room_aboutclass);
        depTxt = findViewById(R.id.dep_aboutclass);
        myclass = (myClass)getIntent().getSerializableExtra("myclass");
        nameTxt.setText(myclass.name);
        roomTxt.setText(myclass.room);
        depTxt.setText(myclass.dep);
    }
}
