package com.example.myprozhepayanterm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MyClassWorkFragment extends Fragment  {
    User user;
   myClass myclass;
MyHomeworkAdapter adapter;
    RecyclerView recyclerView;


    boolean isStudent = true ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.classwork_fragment, parent, false);





        user = (User) getArguments().getSerializable("user");
        myclass = (myClass) getArguments().getSerializable("myclass");
        System.out.println("okok : " + myclass.name);


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



















}