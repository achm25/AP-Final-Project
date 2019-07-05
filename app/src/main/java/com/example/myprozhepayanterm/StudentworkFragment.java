package com.example.myprozhepayanterm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StudentworkFragment extends Fragment {
StudentpageAdapter studentpageAdapter;
RecyclerView recyclerView;
User user;
myClass myclass;
Homework homework;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.studentwork_fragment, parent, false);
        setHasOptionsMenu(true);

        user = (User) getArguments().getSerializable("user");
        myclass = (myClass) getArguments().getSerializable("myclass");

homework = (Homework) getArguments().getSerializable("myclass");

        recyclerView = v.findViewById(R.id.rec_studentwork);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        studentpageAdapter = new StudentpageAdapter(getContext(),homework);
        recyclerView.setAdapter(studentpageAdapter);
        return v;
    }





}
