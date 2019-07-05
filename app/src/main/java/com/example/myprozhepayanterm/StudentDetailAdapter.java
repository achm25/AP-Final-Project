package com.example.myprozhepayanterm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailAdapter extends RecyclerView.Adapter<StudentDetailAdapter.ViewHolderStudentDetail> {


    List<Homework> arrUser;
    private Context mcontext;
    User user;
    myClass myclass;

    boolean ischeck =true;


    public StudentDetailAdapter(Context mcontext, ArrayList<Homework> arrUser) {
        this.mcontext = mcontext;

        this.arrUser = arrUser;
        for (int i = 0; i <arrUser.size() ; i++) {
            System.out.println("arr : " + arrUser.get(i));
        }



    }
//-----------------------


    @NonNull
    @Override
    public ViewHolderStudentDetail onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyleview_studentdetail_item, parent, false);
        ViewHolderStudentDetail pvh = new ViewHolderStudentDetail(v);
        return pvh;
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolderStudentDetail holder, int position) {
        final Homework homework = arrUser.get(position) ;
        holder.nameTxt.setText(homework.name);
        holder.dateTxt.setText(homework.name);
        if(homework.teacherScore != null)
        {
            holder.scoreTxt.setText(homework.teacherScore);
        }
else
        {
            holder.scoreTxt.setText("No Score");
        }


    }

    @Override
    public int getItemCount() {
        return arrUser.size();
    }
//------------

    static class  ViewHolderStudentDetail extends RecyclerView.ViewHolder {
        TextView nameTxt;
        TextView scoreTxt;
        TextView dateTxt;



        public ViewHolderStudentDetail(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name__studentdetail);
            scoreTxt = itemView.findViewById(R.id.score_studentdetail);
            dateTxt = itemView.findViewById(R.id.date_studentdetail);


        }
    }




}
