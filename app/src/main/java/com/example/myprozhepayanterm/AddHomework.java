package com.example.myprozhepayanterm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class AddHomework extends AppCompatActivity implements View.OnClickListener {
TimePicker timePicker;

    Button btnTimePicker;
    Button btnDatePicker;
    EditText txtDate;
    EditText txtTime;
    EditText txtName;
    EditText txtDep;
    EditText txtScore;
    EditText txtTopic;
    String time;
    String date;
    String name;
    String dep;
    String score;
    String topic;
    Homework homework;
    private int mHour, mMinute;
    private int mYear, mMonth, mDay;
User user;
myClass myclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        user = (User) getIntent().getSerializableExtra("user");
        myclass = (myClass)getIntent().getSerializableExtra("myclass");

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        txtDate=(EditText)findViewById(R.id.in_date);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtTime=(EditText)findViewById(R.id.in_time);
        btnTimePicker.setOnClickListener(this);
txtName = findViewById(R.id.myname_homework);
txtDep = findViewById(R.id.dep_homework);
txtScore = findViewById(R.id.score_homework);
txtTopic = findViewById(R.id.topic_homework);

        txtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (txtName.getText().toString().trim().length() < 3) {
                    txtName.setError("کوتاه است!");
                }


            }
        });

        txtScore.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (txtName.getText().toString().trim().length() < 1) {
                    txtName.setError("کوتاه است!");
                }


            }
        });
        txtTopic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (txtName.getText().toString().trim().length() < 3) {
                    txtName.setError("کوتاه است!");
                }


            }
        });


    }




    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {


                            txtTime.setText(hourOfDay + ":" + minute);
                            time = hourOfDay + ":" + minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_addhomework, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.send_addhomework) {
            if ((txtName.getText().toString().trim().length() >= 3) && (txtName.getText().toString().trim().length() >= 1) && (txtName.getText().toString().trim().length() >= 3))
            {
                score = txtScore.getText().toString();
                name = txtName.getText().toString();
                topic = txtTopic.getText().toString();
                if(txtDep.getText().toString() != null)
                {
                    dep = txtDep.getText().toString();
                    homework = new Homework(name,score,time,topic,date,dep);
                    homework.depIsEmpty=false;
                }
                else
                {
                    homework = new Homework(name,score,time,topic,date);
                }
                ArrayList<Homework> arr = new ArrayList<>();
                ArrayList<User> arr1 = new ArrayList<>();

                if(user.homeworksTecher != null)
                {
                    arr = user.homeworksTecher;
                }
if(homework.teacherOfHomework != null)
                {
                    arr1 = homework.teacherOfHomework;
                }

                arr.add(homework);
arr1.add(user);
                homework.teacherOfHomework =arr1;
                user.homeworksTecher = arr;


SocketToPC_addhomework socketToPC_addhomework = new SocketToPC_addhomework(AddHomework.this);
socketToPC_addhomework.execute();


            }
            return true;
        }
        else if (id == R.id.link_addhomework) {
            return true;
        }
        else if (id == R.id.close_addhomework) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private class SocketToPC_addhomework extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        WeakReference<AddHomework> activityReference ;
        SocketToPC_addhomework(AddHomework context)
        {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(Void... input) {

            try {


                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("addhomework");
                objectOutputStream.flush();
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

            AddHomework activity = activityReference.get();



            super.onPostExecute(s);


        }

    }







}
