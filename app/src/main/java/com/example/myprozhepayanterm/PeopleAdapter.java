package com.example.myprozhepayanterm;
import android.widget.ImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolderPeople>{

    List<User> arrUser;
    private Context mcontext;
    User user;
    myClass myclass;
    boolean ischeck =true;


    public PeopleAdapter(Context mcontext,ArrayList<User> arrUser) {
        this.mcontext = mcontext;

        this.arrUser = arrUser;
        for (int i = 0; i <arrUser.size() ; i++) {
            System.out.println("arr : " + arrUser.get(i));
        }



    }
//-----------------------


    @NonNull
    @Override
    public ViewHolderPeople onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyleview_people, parent, false);
        ViewHolderPeople pvh = new ViewHolderPeople(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderPeople holder, int position) {
        final User user = arrUser.get(position) ;
        final String temp = (String) user.username;
        System.out.println("temp : " + temp);
        holder.txt1.setText(temp);

        Bitmap bmp= BitmapFactory.decodeByteArray(user.imagebyte,0,user.imagebyte.length);
        holder.imageView.setImageBitmap(bmp);
        ischeck = false ;

    }

    @Override
    public int getItemCount() {
        return arrUser.size();
    }
//------------

    static class  ViewHolderPeople extends RecyclerView.ViewHolder {
        TextView txt1;
        ImageView imageView;


        public ViewHolderPeople(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.peopletxt);
           imageView = itemView.findViewById(R.id.image_peoplerec);

        }
    }








}
