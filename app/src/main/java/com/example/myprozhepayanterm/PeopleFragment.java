package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myprozhepayanterm.R;
import com.example.myprozhepayanterm.SettingTeacher;

public class PeopleFragment extends Fragment {

    User user;
    myClass myclass;
    PeopleAdapter studentAdapter;
    PeopleAdapter teacherAdapter;
    RecyclerView teacherRecyclerView;
    RecyclerView studentRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.people_fragment, parent, false);
        setHasOptionsMenu(true);

        user = (User) getArguments().getSerializable("user");
        myclass = (myClass) getArguments().getSerializable("myclass");



        teacherRecyclerView = v.findViewById(R.id.rec_teacher);
        teacherRecyclerView.setHasFixedSize(true);
        teacherRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(myclass.teachershomework != null)
        {
            if(myclass.teachershomework.size() >0)
            {

               teacherAdapter = new PeopleAdapter(getContext(),myclass.teacherOfClass);
                teacherRecyclerView.setAdapter(teacherAdapter);
            }
        }



        studentRecyclerView = v.findViewById(R.id.rec_teacher);
        studentRecyclerView.setHasFixedSize(true);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(myclass.studentshomework != null)
        {
            if(myclass.studentshomework.size() >0)
            {

               studentAdapter = new PeopleAdapter(getContext(),myclass.userOfClass);
                studentRecyclerView.setAdapter(teacherAdapter);
            }
        }





        return v;
    }



}
