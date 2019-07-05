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
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;

public class CreateClass extends AppCompatActivity {
    EditText name;
    EditText room;
    EditText dep;
    Toolbar toolbar;
    String shomareClass;
User user;
    myClass temp;
    ArrayList<myClass> arr1 = new ArrayList<>();
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
        System.out.println("user : " + user.username);
        if(user.teacherOfMyClasses != null)
        {
            System.out.println("null nist");
            arr1.addAll(user.teacherOfMyClasses);
            if (arr1.size()>0)
            {
                for (int i = 0 ; i<arr1.size() ; i++)
                {
                    System.out.println("  arr1 :" + arr1.get(i).name);
                }
            }

        }

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
        getSupportActionBar().setTitle("Create myClass");


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
if((room.getText().toString().trim().length() >= 1) && (name.getText().toString().trim().length() >= 1))
{


     shomareClass = "No number";
   temp = new myClass(name.getText().toString(),room.getText().toString(),dep.getText().toString(),shomareClass,user);





   /* User  baba = user;
            baba.arrClass[0]=(new myClass(name.getText().toString(),room.getText().toString(),dep.getText().toString(),shomareClass,user));*/
SocketToPC_creatClass socketToPC_creatClass = new SocketToPC_creatClass(CreateClass.this);
socketToPC_creatClass.execute();



   /* Intent i = new Intent(getApplicationContext(), MainActivity.class);
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



   private class SocketToPC_creatClass extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

       WeakReference<CreateClass> activityReference ;
       SocketToPC_creatClass(CreateClass context)
       {
           activityReference = new WeakReference<>(context);
       }


        @Override
        protected String doInBackground(Void... input) {

            try {

                System.out.println("shod");
                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("createClass");
                objectOutputStream.flush();
                shomareClass=(String) objectInputStream.readObject();
                System.out.println("bash1");
temp.id = shomareClass;

                System.out.println("te : " + temp.id);


                System.out.println("1111");
               ArrayList<myClass> arr = new ArrayList<myClass>();
               arr = user.teacherOfMyClasses;
                System.out.println("2222");
                if(arr.isEmpty() == false)
                {
                    for (int i = 0 ; i<arr.size() ; i++)
                    {
                        System.out.println("  clasarr :" + arr.get(i).name);
                    }
                }

                arr.add(temp);
                System.out.println("3333");
                user.teacherOfMyClasses =arr;
                for (int i = 0 ; i<arr.size() ; i++)
                {
                    System.out.println("  clasarr2 :" + arr.get(i).name + arr.get(i).id);
                }
                objectOutputStream.writeObject(user);
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
            CreateClass activity = activityReference.get();
            super.onPostExecute(s);



        }

    }
}
