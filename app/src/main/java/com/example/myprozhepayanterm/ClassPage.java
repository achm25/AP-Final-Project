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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_classpage ,menu);
        return true;
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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.refresh_classpage)
        {
            SocketToPC_refresh s = new SocketToPC_refresh(ClassPage.this);

          s.execute(user.username,user.password);
        }
        else if(id == R.id.aboutus_classpage)
        {


        }else if(id == R.id.notif_classpage)
        {



        }else if(id == R.id.setting_classpage)
        {

            Intent i = new Intent(this,SettingTeacher.class);
            i.putExtra("user" , user);
            i.putExtra("myclass" , myclass);
            startActivity(i);

        }else if(id == R.id.about_classpage)
        {
            Intent i = new Intent(this,AboutClass.class);

            i.putExtra("myclass" , myclass);
            startActivity(i);


        }else if(id == R.id.aboutus_classpage)
        {



        }


        return super.onOptionsItemSelected(item);
    }
    //-------------------------------------------------------------------------------------------------------


    private class SocketToPC_refresh extends AsyncTask<String,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        WeakReference<ClassPage> activityReference ;
        SocketToPC_refresh(ClassPage context)
        {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(String... input) {
            ArrayList<String> arr = new ArrayList<>();
            for(String str : input)
            {
                arr.add(str);
            }
            try {


                s = new Socket("192.168.1.5",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("refresh");
                objectOutputStream.flush();
                objectOutputStream.writeObject(arr);
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

            ClassPage activity = activityReference.get();



            super.onPostExecute(s);


        }

    }

}


