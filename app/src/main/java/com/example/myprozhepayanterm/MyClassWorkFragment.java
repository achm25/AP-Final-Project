package com.example.myprozhepayanterm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;


public class MyClassWorkFragment extends Fragment  {
    User user;
   myClass myclass;
MyHomeworkAdapter adapter;
    RecyclerView recyclerView;
    boolean isTeacher = false ;
Integer number = null;
    boolean isStudent = true ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.classwork_fragment, parent, false);





        user = (User) getArguments().getSerializable("user");
        myclass = (myClass) getArguments().getSerializable("myclass");
        System.out.println("okok : " + myclass.name);
        System.out.println("sizam1 " + user.homeworksTecher.size());

        recyclerView = v.findViewById(R.id.rec_homework);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(myclass.teachershomework != null)
        {
            if(myclass.teachershomework.size() >0)
            {
                System.out.println("kkkkkkk : " + myclass.teachershomework.size());
                adapter = new MyHomeworkAdapter(getContext(),myclass);
                recyclerView.setAdapter(adapter);
            }
        }




        for (User karbar:myclass.teacherOfClass) {
            if (karbar.username.equals(user.username))
                isStudent = false ;
        }
        setHasOptionsMenu(true);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_classworkfragment);


        if(isStudent)
        {
            fab.hide();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editclass(getActivity());
            }
        });

        return v;
    }

    private void editclass(Context context) {
        final CharSequence[] options = {"add homework","add model"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("add homework")) {
                    Intent i = new Intent(getActivity(),AddHomework.class);
                    i.putExtra("user" , user);
                    i.putExtra("myclass" , myclass);
                    startActivity(i);

                } else if (options[item].equals("add model")) {


                }
            }
        });
        builder.show();
    }











    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_for_classpage ,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }




    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        for (User karbar:myclass.teacherOfClass) {
            if (karbar.username.equals(user.username))
                isTeacher = true ;
        }

        if (isTeacher) {
            menu.findItem(R.id.about_classpage).setVisible(false);
        }else {
            menu.findItem(R.id.setting_classpage).setVisible(false) ;

        }
        super.onPrepareOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.refresh_classpage)
        {
            SocketToPC_refresh s = new SocketToPC_refresh(MyClassWorkFragment.this);

            s.execute(user.username,user.password);
        }
        else if(id == R.id.aboutus_classpage)
        {


        }else if(id == R.id.notif_classpage)
        {



        }else if(id == R.id.setting_classpage)
        {

            Intent i = new Intent(getActivity(),SettingTeacher.class);
            i.putExtra("user" , user);
            i.putExtra("myclass" , myclass);
            startActivity(i);

        }else if(id == R.id.about_classpage)
        {
            Intent i = new Intent(getActivity(),AboutClass.class);

            i.putExtra("myclass" , myclass);
            startActivity(i);


        }else if(id == R.id.aboutus_classpage)
        {



        }


        return super.onOptionsItemSelected(item);
    }
    //-------------------------------------------------------------------------------------------------------


    private class SocketToPC_refresh extends AsyncTask<String,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        User user ;
        WeakReference<MyClassWorkFragment> activityReference ;
        SocketToPC_refresh(MyClassWorkFragment context)
        {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(String... input) {
            ArrayList<String> arr = new ArrayList<>();
            for(String str : input)
            {
                arr.add(str);
            }
            try {


                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("refresh");
                objectOutputStream.flush();
                objectOutputStream.writeObject(arr);
                objectOutputStream.flush();
               user =(User)objectInputStream.readObject();
                for (int i = 0; i <user.teacherOfMyClasses.size() ; i++) {
if(myclass.id.equals(user.teacherOfMyClasses.get(i).id))
{
    number = i;
    break;
}

                }
                myclass=user.teacherOfMyClasses.get(number);
                System.out.println("sizr of "+myclass.teachershomework.size());
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

            MyClassWorkFragment activity = activityReference.get();
activity.user = user;


            adapter = new MyHomeworkAdapter(getContext(),myclass);
            recyclerView.setAdapter(adapter);

            super.onPostExecute(s);


        }

    }








}