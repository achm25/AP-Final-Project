package com.example.myprozhepayanterm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    ImageView imageView;
    byte [] imgbyte;
    private int mHour, mMinute;
    private int mYear, mMonth, mDay;
    Integer num=0;
    int n;
    User user;
myClass myclass;
Toolbar toolbar;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        user = (User) getIntent().getSerializableExtra("user");
        myclass = (myClass)getIntent().getSerializableExtra("myclass");
imageView = findViewById(R.id.imageView_addhomework);
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
toolbar = findViewById(R.id.toolbar_addhomework);

        setSupportActionBar(toolbar);



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
            if (imageView.getDrawable() !=null) {

                Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imgbyte = baos.toByteArray();

            }
            if ((txtName.getText().toString().trim().length() >= 3) && (txtName.getText().toString().trim().length() >= 1) && (txtName.getText().toString().trim().length() >= 3))
            {
                score = txtScore.getText().toString();
                name = txtName.getText().toString();
                topic = txtTopic.getText().toString();
                ArrayList<Homework> arr2 = new ArrayList<>();
                if(txtDep.getText().toString() != null)
                {
                    dep = txtDep.getText().toString();
                    homework = new Homework(name,score,time,topic,date,dep,imgbyte);
                    homework.depIsEmpty=false;
                    homework.classID = myclass.id;
                }
                else
                {
                    homework = new Homework(name,score,time,topic,date,imgbyte);
                    homework.classID = myclass.id;

                }
                System.out.println("arr 2size part1 : " + arr2.size());
                ArrayList<Homework> arr = new ArrayList<>();
                ArrayList<User> arr1 = new ArrayList<>();


                if(user.homeworksTecher != null)
                {
                    arr = user.homeworksTecher;
                }
if(homework.teacherOfHomework != null)
                {
                    arr1 = homework.teacherOfHomework;
                }if(myclass.teacherOfClass != null)
                {
                    arr2 = myclass.teachershomework;
                    System.out.println("arr 2size : " + myclass.teachershomework.size());

                }
                arr2.add(homework);
                n =arr2.lastIndexOf(homework);
                System.out.println(" n : " + n);
                arr.add(homework);
arr1.add(user);
                System.out.println(homework.name + " dsfdas");

homework.teacherOfHomework =arr1;
user.homeworksTecher = arr;


                myclass.teachershomework = arr2;
                System.out.println(myclass.teachershomework.size() + " vvvvvv");
                for (int i = 0; i <user.teacherOfMyClasses.size() ; i++) {
                    if(myclass.id.equals(user.teacherOfMyClasses.get(i).id))
                    {
                        user.teacherOfMyClasses.set(i,myclass);

                        System.out.println("succesful");
                        num=i;
                        break;
                    }
                }
                System.out.println(user.username);
                System.out.println(user.teacherOfMyClasses.get(num).name);
                System.out.println(user.teacherOfMyClasses.get(num).teachershomework.size() + " jjjjjj");

SocketToPC_addhomework socketToPC_addhomework = new SocketToPC_addhomework(AddHomework.this);
socketToPC_addhomework.execute();


            }
            return true;
        }
        else if (id == R.id.link_addhomework) {
            selectImage(AddHomework.this);
            return true;
        }
        else if (id == R.id.close_addhomework) {
           onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//-----------------------------------


    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap bmp;
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                        cursor.close();

                        try {

                            final Uri imageUri = data.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            bmp = BitmapFactory.decodeStream(imageStream);
                            imageView.setImageBitmap(bmp);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        //to know about the selected image width and height


                    } else {
                        Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    }











    //-----------------------



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

                objectOutputStream.writeObject(num.toString());
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
