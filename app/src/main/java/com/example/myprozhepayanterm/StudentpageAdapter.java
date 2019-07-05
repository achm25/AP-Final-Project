package com.example.myprozhepayanterm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentpageAdapter extends RecyclerView.Adapter<StudentpageAdapter.ViewHolderPeople> {

    List<User> arrUser;
    private Context mcontext;
    User user;
    myClass myclass;
    Homework homework;
    boolean ischeck =true;


    public StudentpageAdapter(Context mcontext, Homework homework) {
        this.mcontext = mcontext;

        this.homework = homework;
        arrUser = homework.userOfHomework;


    }



    @NonNull
    @Override
    public StudentpageAdapter.ViewHolderPeople onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyleview_studentdetail_item, parent, false);
        StudentpageAdapter.ViewHolderPeople pvh = new StudentpageAdapter.ViewHolderPeople(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentpageAdapter.ViewHolderPeople holder, int position) {
        final User user = arrUser.get(position) ;
        final String temp = (String) user.username;

        holder.name.setText(temp);
holder.scoreAll.setText(homework.score);
        Bitmap bmp= BitmapFactory.decodeByteArray(user.imagebyte,0,user.imagebyte.length);
        holder.imageView.setImageBitmap(bmp);
        ischeck = false ;
        if(holder.studentScore.getText().toString() != null)
        {
            homework.studentAnswer.get(position).teacherScore = holder.studentScore.getText().toString();
        }
else
        {
            homework.studentAnswer.get(position).teacherScore = "No Score";
        }

    }

    @Override
    public int getItemCount() {
        return arrUser.size();
    }
//------------

    static class  ViewHolderPeople extends RecyclerView.ViewHolder {
        EditText scoreAll;
        EditText studentScore;
        TextView name;
        ImageView imageView;


        public ViewHolderPeople(@NonNull View itemView) {
            super(itemView);
            scoreAll = itemView.findViewById(R.id.allscore_studentwork);
            studentScore = itemView.findViewById(R.id.studentscore_studentwork);
            name = itemView.findViewById(R.id.name_studentwork);
            imageView = itemView.findViewById(R.id.profileimage_studentwork);

        }
    }





}
