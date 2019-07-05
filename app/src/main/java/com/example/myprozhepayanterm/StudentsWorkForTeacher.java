package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;

public class StudentsWorkForTeacher extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    Fragment fragment1 = new InstructionsFragment();
    Fragment fragment2 = new StudentworkFragment();
    myClass myclass;
    User user;
    Homework homework;
boolean isTeacher = true;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_work_for_teacher);
        user = (User) getIntent().getSerializableExtra("user");
        myclass = (myClass)getIntent().getSerializableExtra("myclass");
        homework = (Homework) getIntent().getSerializableExtra("homework");

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putSerializable("myclass", myclass);
        bundle.putSerializable("homework", homework);
        fragment1.setArguments(bundle);
        fragment2.setArguments(bundle);

        toolbar = findViewById(R.id.toolbar_teachermanagment);
        toolbar.setTitle("myClass");
        setSupportActionBar(toolbar);
        loadFragment(fragment1);
        BottomNavigationView navigation = findViewById(R.id.navigation_teachermanagment);
        navigation.setOnNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_one_teachermanagment:

                fragment = fragment1;
                //fragment = new StreamFragment();
                break;

            case R.id.navigation_two_teachermanagment:

                fragment = fragment2;

                // fragment = new PeopleFragment();

                break;




        }

        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_teachermanagment, fragment)
                    .commit();

            return true;
        }
        return false;
    }








    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        for (User karbar:myclass.teacherOfClass) {
            if (karbar.username.equals(user.username))
                isTeacher = true ;
        }

        if (isTeacher) {
            menu.findItem(R.id.about_classpage).setVisible(false);
        }else {
            menu.findItem(R.id.setting_classpage).setVisible(false) ;

        }
        super.onPrepareOptionsMenu(menu);
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_teachermanagment ,menu);
        return true;
    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.refresh_teachermanagment)
        {
SocketToPC_teachermanagment s = new SocketToPC_teachermanagment(StudentsWorkForTeacher.this);
s.execute();
        }
        else if(id == R.id.aboutus_teachermanagment)
        {


        }else if(id == R.id.notif_teachermanagment)
        {



        }else if(id == R.id.aboutus_teachermanagment)
        {



        }


        return super.onOptionsItemSelected(item);
    }







    private class SocketToPC_teachermanagment extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        WeakReference<StudentsWorkForTeacher> activityReference ;
        SocketToPC_teachermanagment(StudentsWorkForTeacher context)
        {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(Void... input) {

            try {


                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("teachermanagment_refresh");
                objectOutputStream.flush();
                objectOutputStream.writeObject(user);
                objectOutputStream.flush();
                User updateUser=(User)objectInputStream.readObject();
                user = updateUser;
                objectOutputStream.close();
                objectInputStream.close();
                s.close();
            }
            catch (Exception e)
            {

            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {

            StudentsWorkForTeacher activity = activityReference.get();



            super.onPostExecute(s);


        }

    }






}
