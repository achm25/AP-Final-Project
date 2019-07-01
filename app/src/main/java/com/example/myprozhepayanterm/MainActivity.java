package com.example.myprozhepayanterm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.fxn.cue.Cue;
import com.fxn.cue.enums.Type;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener  {
 Toolbar toolbar;
 NavigationView nv;
ImageView imageView ;
    MyAdapter adapter;
    User user;
    byte[] bitmapdata;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layput);
         user = (User) getIntent().getSerializableExtra("user");


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Classroom");
        toolbar.setSubtitle("powered by Java");
        List<RecyclerItem> listItems;

        nv = findViewById(R.id.nav);
recyclerView = findViewById(R.id.rec_mainactivity);
//-----------------------------------------

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItems = new ArrayList<>();
        listItems.add(new RecyclerItem("salammm" , "asd"));
        listItems.add(new RecyclerItem("lllalammm" , "asd"));
        listItems.add(new RecyclerItem("ggggmm" , "asd"));
        listItems.add(new RecyclerItem("jjjlammm" , "asd"));
        listItems.add(new RecyclerItem("lllllammm" , "asd"));

        adapter = new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);


        //---------------------------------------------
        View headerView = nv.getHeaderView(0);
        imageView = headerView.findViewById(R.id.imageview_header);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //drawer.addDrawerListener((DrawerLayout.DrawerListener) mToolbar);
        toggle.syncState();


        nv.setNavigationItemSelectedListener( this);




     /*   IntroSliderPref preman = new IntroSliderPref(this);
if(preman.startSlider())
{
    Intent i = new Intent(this,Intro_slider.class);
    startActivity(i);
    //finish();
}*/
        if(user.imagebyte != null)
        {
            byte[] imgByte = user.imagebyte;
            System.out.println(Arrays.toString(imgByte));
            Bitmap bmp= BitmapFactory.decodeByteArray(imgByte,0,imgByte.length);
            imageView.setImageBitmap(bmp);

        }
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.setting) {
            Intent i = new Intent(this,Setting.class);
           startActivity(i);
        } else if (id == R.id.classes) {
            Intent i = new Intent(this,ClassPage.class);
            startActivity(i);
        } else if (id == R.id.Calender) {
            Intent i = new Intent(this,SettingTeacher.class);
            startActivity(i);
        } else if (id == R.id.notifiction) {
            Intent i = new Intent(getApplicationContext(),AddHomework.class);
            startActivity(i);
        } else if (id == R.id.archivedclasses) {

        } else if (id == R.id.classroomfolders) {

        }else if (id == R.id.help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.refresh)
        {
            /*System.out.println("ffffffff");
            SocketToPC_mainClass socketToPC_mainClass = new SocketToPC_mainClass();
            socketToPC_mainClass.execute(user.username,user.password);*/
        }
        else if(id == R.id.aboutus)
        {

            Intent i = new Intent(getApplicationContext(),AboutUS.class);
            startActivity(i);
            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.notif)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.create)
        {


           Intent i = new Intent(getApplicationContext(),CreateClass.class);
            i.putExtra("user" , user);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }



    //-------------------
    private class SocketToPC_mainClass extends AsyncTask<String,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        @Override
        protected String doInBackground(String... input) {
            ArrayList<String> arr = new ArrayList<>();
            for(String str : input)
            {
                arr.add(str);
            }
            try {

                System.out.println("shodfffff");
                s = new Socket("192.168.1.5",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("main");
                objectOutputStream.flush();
                objectOutputStream.writeObject(arr);
                objectOutputStream.flush();
                User user=(User)objectInputStream.readObject();
                System.out.println(user.username + "  1");
                System.out.println(user.password);

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

            super.onPostExecute(s);


        }

    }

}
