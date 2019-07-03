package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class JoinClass extends AppCompatActivity {



    EditText classcode;
    Toolbar toolbar;
    User user;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);
        user = (User) getIntent().getSerializableExtra("user");

        classcode = findViewById(R.id.classcode_joinclass);
        classcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (classcode.getText().toString().trim().length() < 1) {
                    classcode.setError("پرشود!");
                }

            }
        });


        toolbar = findViewById(R.id.toolbar_joinclass);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Join myClass");

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_joinclass ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.aboutus_joinclass)
        {

            Intent i = new Intent(getApplicationContext(),AboutUS.class);
            startActivity(i);
        }
        else if(id == R.id.join_joinclass)
        {
            code = classcode.getText().toString();
            SocketToPC_join socketToPC_join =new SocketToPC_join();
            socketToPC_join.execute();

            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.cancle_joinclass)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }


        return super.onOptionsItemSelected(item);
    }




    private class SocketToPC_join extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        @Override
        protected String doInBackground(Void... input) {

            try {

                System.out.println("shod");
                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("join");
                objectOutputStream.flush();

                objectOutputStream.writeObject(user);
                objectOutputStream.flush();
                objectOutputStream.writeObject(code);
                objectOutputStream.flush();


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
