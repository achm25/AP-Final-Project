package com.example.myprozhepayanterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AboutUS extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = findViewById(R.id.toolbar_aboutus);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About Us");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_classes, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.refresh_classes)
        {
            Toast.makeText(getApplicationContext(),"refresh",Toast.LENGTH_LONG);
        }
        else if(id == R.id.aboutus_classes)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.classes_classes)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.notif_classes)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }


        return super.onOptionsItemSelected(item);
    }
}
