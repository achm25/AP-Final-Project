package com.example.myprozhepayanterm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MyClassWorkFragment extends Fragment {
    User user;
   myClass myclass;

    boolean isStudent = true ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.classwork_fragment, parent, false);
        user = (User) getArguments().getSerializable("user");
        myclass = (myClass) getArguments().getSerializable("myclass");

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