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
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class CreateClass extends AppCompatActivity {
    EditText name;
    EditText room;
    EditText dep;
    Toolbar toolbar;
User user;
    boolean checkname = false;
    boolean checkroom = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        name = findViewById(R.id.classname_creatclass);
        room  = findViewById(R.id.room_creatclass);
        dep = findViewById(R.id.description_creatclass);
        user = (User) getIntent().getSerializableExtra("user");
        System.out.println(user.username);
        System.out.println(user.password);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (name.getText().toString().trim().length() < 1) {
                    name.setError("پرشود!");
                }
                else {
                    checkname = true;
                }

            }
        });

        room.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (room.getText().toString().trim().length() < 1) {
                    room.setError("پرشود!");
                }
                else {
                    checkroom = true;
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
if((checkname == true) && (checkroom == true))
{
    Random random = new Random();
    Integer num = random.nextInt(10000);
    String shomareClass = num.toString();
    Class temp = new Class(name.getText().toString(),room.getText().toString(),dep.getText().toString(),shomareClass);
    user.creatClass(temp);
SocketToPC_creatClass socketToPC_creatClass = new SocketToPC_creatClass();
String name1 = name.getText().toString();
String name2 = room.getText().toString();
String name3 = dep.getText().toString();
String name4 = shomareClass;

socketToPC_creatClass.execute(name1,name2,name3,name4,user.username,user.password);
    System.out.println(name4);
    /*Intent i = new Intent(getApplicationContext(), MainActivity.class);
    i.putExtra("user" , user);
    startActivity(i);*/
}
else
{
    Toast.makeText(getApplicationContext(),"ناموفق",Toast.LENGTH_LONG);

}

            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.cancle_createclass)
        {

            onBackPressed();
            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }


        return super.onOptionsItemSelected(item);
    }




    private class SocketToPC_creatClass extends AsyncTask<String,Void,String> {
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

                System.out.println("shod");
                s = new Socket("192.168.1.5",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("createClass");
                objectOutputStream.flush();
                objectOutputStream.writeObject(arr);
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
            Toast.makeText(getApplicationContext(),"موفق",Toast.LENGTH_LONG);
onBackPressed();

        }

    }

}
