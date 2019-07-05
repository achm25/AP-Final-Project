package com.example.myprozhepayanterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class StudentDetail extends AppCompatActivity {

    StudentDetailAdapter studentDetailAdapter;
    RecyclerView recyclerView;
User user;
myClass myclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        user = (User) getIntent().getSerializableExtra("user");
        myclass = (myClass)getIntent().getSerializableExtra("myclass");

        recyclerView.findViewById(R.id.rec_studentdetail);

        if(user.homeworksStudent != null)
        {
            if(user.homeworksStudent.size()>0)
            {

                ArrayList<Homework> myArr = new ArrayList<>();
                for (int i = 0; i <user.homeworksStudent.size() ; i++) {
                    if(user.homeworksStudent.get(i).classID.equals(myclass.id))
                    {
                        final Homework homework = user.homeworksStudent.get(i);
                        myArr.add(homework);
                    }
                }
                if(myArr != null)
                {
                    if(myArr.size()>0)
                    {
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        studentDetailAdapter = new StudentDetailAdapter(this, myArr);
                        recyclerView.setAdapter(studentDetailAdapter);
                    }
                }

            }
        }





    }
}
