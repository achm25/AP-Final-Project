package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SignIn extends AppCompatActivity {
Button btn;
String username;
String password;
    boolean check1;// for equals from server
    boolean check2;
    EditText userText;
    EditText passText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn = findViewById(R.id.button2);
        userText = findViewById(R.id.username_signin);
        passText = findViewById(R.id.password_signin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=userText.getText().toString().trim();
                password=passText.getText().toString().trim();
                SocketToPC_signin s = new SocketToPC_signin();
                s.execute(username,password);


            }
        });
    }



    private class SocketToPC_signin extends AsyncTask<String,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        @Override
        protected String doInBackground(String... strings) {
            ArrayList arr = new ArrayList();
            for(String s : strings)
            {
                arr.add(s);
            }
            try {


                s = new Socket("192.168.1.5",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("signin");
                objectOutputStream.writeObject(arr);
                objectOutputStream.flush();
                check1=(boolean) objectInputStream.readObject();

                check2=(boolean) objectInputStream.readObject();

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
            if((check1==true) &&(check2 ==true))
            {

                System.out.println("yes");
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
    /*Intent i = new Intent(getApplicationContext(),MainActivity.class);
    startActivity(i);*/
            }
            else
            {

                System.out.println("no");
                Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_LONG);
            }
        }
    }

}
