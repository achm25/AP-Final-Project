package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;

public class InstructionsFragment extends Fragment {

    Button btnTimePicker;
    Button btnDatePicker;
    Button btnImage;
    Button btnSave;
    Button btnDelete;
    EditText txtDate;
    EditText txtTime;
    EditText txtName;
    EditText txtDep;
    EditText txtScore;
    EditText txtTopic;
    EditText txtComment;
    String time;
    String date;
    String name;
    String dep;
    String score;
    String topic;
    ImageView imageView;
    byte [] imgbyte;
    private int mHour, mMinute;
    private int mYear, mMonth, mDay;
    Integer num=0;
    int n;
    User user;
    myClass myclass;
    Homework homework;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.instuction_fragment, parent, false);
        setHasOptionsMenu(true);
        user = (User) getArguments().getSerializable("user");
        myclass = (myClass) getArguments().getSerializable("myclass");
        homework = (Homework) getArguments().getSerializable("homework");
        imageView = v.findViewById(R.id.imageView_teachermanagment);
        btnDatePicker=v.findViewById(R.id.btn_date_teachermanagment);
        txtDate=(EditText)v.findViewById(R.id.in_date_teachermanagment);
        btnDatePicker.setOnClickListener((View.OnClickListener) getActivity());
        btnTimePicker=(Button)v.findViewById(R.id.btn_time_teachermanagment);
        btnImage=(Button)v.findViewById(R.id.btn_teachermanagment);
        btnSave=(Button)v.findViewById(R.id.save_teachermanagment);
        btnDelete=(Button)v.findViewById(R.id.delete_teachermanagment);
        txtTime=(EditText)v.findViewById(R.id.in_time_teachermanagment);
        btnTimePicker.setOnClickListener((View.OnClickListener) getActivity());
        txtName = v.findViewById(R.id.myname_teachermanagment);
        txtDep = v.findViewById(R.id.dep_teachermanagment);
        txtScore = v.findViewById(R.id.score_teachermanagment);
        txtTopic = v.findViewById(R.id.topic_teachermanagment);
txtComment = v.findViewById(R.id.comment_teachermanagment);
txtName.setText(homework.name);
txtDate.setText(homework.date);
txtDep.setText(homework.dep);
txtScore.setText(homework.score);
txtTopic.setText(homework.Topic);
txtTime.setText(homework.Time);
btnSave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if ((txtName.getText().toString().trim().length() >= 3) && (txtName.getText().toString().trim().length() >= 1) && (txtName.getText().toString().trim().length() >= 3)) {
            homework.name = txtName.getText().toString();
            homework.date = txtDate.getText().toString();
            homework.dep = txtDep.getText().toString();
            homework.Time = txtTime.getText().toString();
            homework.Topic = txtTopic.getText().toString();
            homework.score = txtScore.getText().toString();
homework.comment = txtComment.getText().toString();



            ArrayList<Homework> arr2 = new ArrayList<>();
            ArrayList<Homework> arr = new ArrayList<>();
            ArrayList<User> arr1 = new ArrayList<>();



            arr = user.homeworksTecher;


            arr1 = homework.teacherOfHomework;

            arr2 = myclass.teachershomework;
            System.out.println("arr 2size : " + myclass.teachershomework.size());



            n =arr2.lastIndexOf(homework);
            arr2.set(n,homework);
            n = arr1.lastIndexOf(user);
            arr1.set(n,user);
            n = arr.lastIndexOf(homework);
            arr.set(n,homework);




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



SocketToPC_Instuctions s = new SocketToPC_Instuctions(InstructionsFragment.this);
s.execute();



        }
    }
});

btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        ArrayList<Homework> arr2 = new ArrayList<>();
        ArrayList<Homework> arr = new ArrayList<>();
        ArrayList<User> arr1 = new ArrayList<>();



        arr = user.homeworksTecher;


        arr1 = homework.teacherOfHomework;

        arr2 = myclass.teachershomework;
        System.out.println("arr 2size : " + myclass.teachershomework.size());



        n =arr2.lastIndexOf(homework);
        arr2.remove(n);
        n = arr1.lastIndexOf(user);
        arr1.remove(n);
        n = arr.lastIndexOf(homework);
        arr.remove(n);

        SocketToPC_Instuctions s = new SocketToPC_Instuctions(InstructionsFragment.this);
        s.execute();

        Intent i = new Intent(getActivity(),ClassPage.class);

        i.putExtra("myclass" , myclass);
        startActivity(i);


    }
});







        return v;
    }




    private class SocketToPC_Instuctions extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        WeakReference<InstructionsFragment> activityReference ;
        SocketToPC_Instuctions(InstructionsFragment context)
        {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(Void... input) {

            try {


                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("Instuctions");
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

            InstructionsFragment activity = activityReference.get();



            super.onPostExecute(s);


        }

    }




}
