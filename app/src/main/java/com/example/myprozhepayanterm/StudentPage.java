package com.example.myprozhepayanterm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class StudentPage extends AppCompatActivity {
Toolbar toolbar;
ImageView imageView;
AnswerOfHomework answer;
byte [] imgbyte;
User user;
myClass myclass;
Homework homework;
EditText editText;
AnswerOfHomework answerOfHomework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        toolbar = findViewById(R.id.toolbar_studentpage);
        imageView = findViewById(R.id.image_studentpage);
       editText = findViewById(R.id.answer_studentpage);
        setSupportActionBar(toolbar);
        user = (User) getIntent().getSerializableExtra("user");
        myclass = (myClass)getIntent().getSerializableExtra("myclass");
        homework = (Homework) getIntent().getSerializableExtra("homework");


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_studentpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.send_studentpage) {
            Random r = new Random();
            Integer num = r.nextInt(10000);
            if (imageView.getDrawable() !=null) {

                Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imgbyte = baos.toByteArray();

answer = new AnswerOfHomework(imgbyte,myclass.id,user.username,num.toString());
            }
            else
            {
                answer = new AnswerOfHomework(myclass.id,user.username,editText.getText().toString(),num.toString());
            }

ArrayList<AnswerOfHomework> arr = new ArrayList<AnswerOfHomework>();
            if(homework.studentAnswer != null)
            {
                if(homework.studentAnswer.size() > 0 )
                {
                    arr=homework.studentAnswer;
                }
            }
            arr.add(answer);
            homework.studentAnswer = arr;

            ArrayList<AnswerOfHomework> arr2 = new ArrayList<>();

            if(user.myAnswer != null)
            {
                if(user.myAnswer.size() > 0 )
                {
                    arr2=user.myAnswer;
                }
            }
            arr2.add(answer);
            user.myAnswer = arr2;


SocketToPC_studentpage socketToPC_studentpage = new SocketToPC_studentpage(StudentPage.this);
socketToPC_studentpage.execute();


            return true;
        }
        else if (id == R.id.link_studentpage) {
            selectImage(StudentPage.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }









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


    private class SocketToPC_studentpage extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        WeakReference<StudentPage> activityReference ;
        SocketToPC_studentpage(StudentPage context)
        {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(Void... input) {

            try {


                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("studentpage");
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

            StudentPage activity = activityReference.get();



            super.onPostExecute(s);


        }

    }






}
