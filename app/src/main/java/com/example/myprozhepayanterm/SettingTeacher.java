package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;

public class SettingTeacher extends AppCompatActivity {
    Toolbar toolbar;
User user;
myClass myclass;
TextView codetxt;
EditText nametxt;
EditText roomtxt;
int num;
EditText deptxt;
    RadioButton only,both;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_teacher);
codetxt = findViewById(R.id.code_settingteacher);
nametxt = findViewById(R.id.myname_teachersetting);
roomtxt = findViewById(R.id.myroom_teachersetting);
deptxt = findViewById(R.id.mydep_teachersetting);
only = findViewById(R.id.radio_only);
both = findViewById(R.id.radio_both);
        user = (User) getIntent().getSerializableExtra("user");
        myclass = (myClass)getIntent().getSerializableExtra("myclass");
codetxt.setText(myclass.id);
nametxt.setText(myclass.name);
roomtxt.setText(myclass.room);
deptxt.setText(myclass.dep);

        for (int i = 0; i <user.teacherOfMyClasses.size() ; i++) {
if(myclass.id.equals(user.teacherOfMyClasses.get(i).id))
{
    num = i;
}
        }

        //ست تایتل برا مقدار پیش فرض فراموش نشه

        toolbar = findViewById(R.id.toolbar_teachersetting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Setting");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_teachersetting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.save_settingteacher)
        {
if(only.isChecked())
{
    myclass.studentCanComment=false;
}
else
{
myclass.studentCanComment=true;
}
myclass.name = nametxt.getText().toString();
myclass.dep = deptxt.getText().toString();
myclass.room = deptxt.getText().toString();
user.teacherOfMyClasses.get(num).name = myclass.name;
user.teacherOfMyClasses.get(num).dep = myclass.dep;
user.teacherOfMyClasses.get(num).room = myclass.room;
            System.out.println("ersal : "+myclass.name + myclass.dep + myclass.room);
SocketToPC_setting s = new SocketToPC_setting(SettingTeacher.this);

s.execute();


        }

        return super.onOptionsItemSelected(item);
    }












    private class SocketToPC_setting extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        WeakReference<SettingTeacher> activityReference ;
        SocketToPC_setting(SettingTeacher context)
        {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(Void... input) {

            try {


                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("setting");
                objectOutputStream.flush();

                objectOutputStream.writeObject(user);
                objectOutputStream.flush();
                System.out.println("ersal : "+user.teacherOfMyClasses.get(num).name + user.teacherOfMyClasses.get(num).room + user.teacherOfMyClasses.get(num).dep);

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

            SettingTeacher activity = activityReference.get();



            super.onPostExecute(s);


        }

    }


}
