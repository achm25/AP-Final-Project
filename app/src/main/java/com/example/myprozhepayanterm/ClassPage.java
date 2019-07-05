package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import android.support.annotation.NonNull;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;


public class ClassPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    Fragment fragment1 = new StreamFragment();
    Fragment fragment2 = new PeopleFragment();
    Fragment fragment3 = new MyClassWorkFragment();

    boolean isTeacher = false ;
    myClass myclass;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page);
        user = (User) getIntent().getSerializableExtra("user");
        myclass = (myClass)getIntent().getSerializableExtra("myclass");


        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putSerializable("myclass", myclass);
        fragment3.setArguments(bundle);
fragment2.setArguments(bundle);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_classpage);
        toolbar.setTitle("myClass");
        setSupportActionBar(toolbar);

        loadFragment(fragment3);

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


//-------------------------------------------------------------------------------------------------------


}


