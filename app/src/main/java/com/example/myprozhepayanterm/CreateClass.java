package com.example.myprozhepayanterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateClass extends AppCompatActivity {
    EditText name;
    EditText room;
    EditText dep;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        name = findViewById(R.id.classname_creatclass);
        room  = findViewById(R.id.room_creatclass);
        dep = findViewById(R.id.description_creatclass);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (name.getText().toString().trim().length() < 1) {
                    name.setError("پرشود!");
                }

            }
        });

        room.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (room.getText().toString().trim().length() < 1) {
                    room.setError("پرشود!");
                }
            }
        });

        toolbar = findViewById(R.id.toolbar_createclass);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Class");


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_creatclass ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.aboutus_createclass)
        {
            Intent i = new Intent(getApplicationContext(),AboutUS.class);
            startActivity(i);
        }
        else if(id == R.id.create_createclass)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.cancle_createclass)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }


        return super.onOptionsItemSelected(item);
    }

}
