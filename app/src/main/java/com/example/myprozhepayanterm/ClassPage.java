package com.example.myprozhepayanterm;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.myprozhepayanterm.Fragment.ClassworkFragment;
import com.example.myprozhepayanterm.Fragment.PeopleFragment;
import com.example.myprozhepayanterm.Fragment.StreamFragment;
import android.support.annotation.NonNull;


public class ClassPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    Fragment fragment1 = new StreamFragment();
    Fragment fragment2 = new PeopleFragment();
    Fragment fragment3 = new ClassworkFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_classpage);
        toolbar.setTitle("Class");
        setSupportActionBar(toolbar);

        loadFragment(fragment1);

        BottomNavigationView navigation = findViewById(R.id.navigation_classpage);
        navigation.setOnNavigationItemSelectedListener(this);

    }
//دو حالتی بودن رو با ایف هندل بشه
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_one:

                fragment = fragment1;
                //fragment = new StreamFragment();
                break;

            case R.id.navigation_two:

             fragment = fragment2;

              // fragment = new PeopleFragment();

                break;

            case R.id.navigation_three:

                fragment =fragment3;

                //fragment = new ClassworkFragment();
                break;


        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_classpage, fragment)
                    .commit();

            return true;
        }
        return false;
    }







}


    /*

public class ClassPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    Fragment fragment1 = new StreamFragment();
    Fragment fragment2 = new PeopleFragment();
    Fragment fragment3 = new ClassworkFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_classpage);
        toolbar.setTitle("Class");
        setSupportActionBar(toolbar);

        loadFragment(fragment1);

        BottomNavigationView navigation = findViewById(R.id.navigation_classpage);
        navigation.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_one:
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.menu_for_classpage);
                fragment = fragment1;
                //fragment = new StreamFragment();
                break;

            case R.id.navigation_two:
             fragment = fragment2;
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.menu_for_classpage_two);
              // fragment = new PeopleFragment();

                break;

            case R.id.navigation_three:
                fragment =fragment3;
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.menu_class_classpage_three);
                //fragment = new ClassworkFragment();
                break;


        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_classpage, fragment)
                    .commit();

            return true;
        }
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_classpage, menu);
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
            Intent i = new Intent(getApplicationContext(),AboutUS.class);
            startActivity(i);
        }
        else if(id == R.id.aboutus_classes)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.notif_classes)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.classes_classes)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }


        return super.onOptionsItemSelected(item);
    }
}


     */