package com.example.myprozhepayanterm;

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
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;

import com.example.myprozhepayanterm.*;
public class Register extends AppCompatActivity {
    byte [] imgbyte;

    ImageView imageView;
String username;
String password;
Button btn;
    EditText userText;
    EditText passText;
    boolean check =false;
    boolean checkPass =false;
    boolean checkUser = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        imageView = (ImageView) findViewById(R.id.imageView3);
        userText = findViewById(R.id.username_text);
        passText = findViewById(R.id.passwordEditText);
btn = findViewById(R.id.button_register);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(Register.this);
            }
        });


        userText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (userText.getText().toString().trim().length() < 4) {
                    userText.setError("کوتاه است!");
                }


            }
        });

        passText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (passText.getText().toString().trim().length() < 5) {
                    passText.setError("کوتاه است!");

                }

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getDrawable() !=null) {

                    Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    imgbyte = baos.toByteArray();

                }
                if((passText.getText().toString().trim().length() >= 5) && (userText.getText().toString().trim().length() >= 4))
                {

                    check = true;
                    SocketToPC socketToPC = new SocketToPC(Register.this);

username = userText.getText().toString();
password = passText.getText().toString();
                    socketToPC.execute(username,password,imgbyte);

                }

            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }







    private class SocketToPC extends AsyncTask<Object,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        WeakReference<Register> activityReference ;

        SocketToPC(Register context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected String doInBackground(Object... input) {
            String[] strings = {(String)input[0] , (String)input[1] } ;
            System.out.println((String)input[1]);
            byte[] imgByte = (byte[])input[2];

            try {

                System.out.println("shod");
                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("register");
                objectOutputStream.flush();
                objectOutputStream.writeObject(strings);
                objectOutputStream.flush();
                objectOutputStream.writeObject(imgByte);
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

            Register activity = activityReference.get();
            if (check) {
                User user = new User(username , password,imgbyte);

                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("user" , user);
                activity.startActivity(intent);
            }

        }

    }
}
//-----------------------------------------------------------------------------