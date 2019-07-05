package com.example.myprozhepayanterm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

public class AdapteMain extends RecyclerView.Adapter<AdapteMain.ViewHolderMain> {




    List<User> arrUser;
    private Context mcontext;
    User user;
    myClass myclass;
    Homework homework;
    boolean ischeck =true;
    List<myClass> arrMyClasses;

    public AdapteMain(Context mcontext, User user) {
        this.mcontext = mcontext;

        this.user = user;

        this.mcontext = mcontext;
        arrMyClasses = user.teacherOfMyClasses;


        List<myClass> arr2 = user.studentOfMyClasses;
        arrMyClasses.addAll(arr2);

        for (int i = 0; i <arrMyClasses.size() ; i++) {
            System.out.println("adapterArr99 : " + arrMyClasses.get(i).name);
        }



    }



    @NonNull
    @Override
    public AdapteMain.ViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);
        AdapteMain.ViewHolderMain pvh = new AdapteMain.ViewHolderMain(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapteMain.ViewHolderMain holder, int position) {
        System.out.println("aaaaaaaa");
        final myClass myclass = arrMyClasses.get(position) ;
        final String temp = (String) myclass.name;
        holder.txt.setText(myclass.name);
        holder.txt2.setText(myclass.room);
        ischeck = false ;
        System.out.println("injast 1");
        for (User karbar:myclass.teacherOfClass) {
            if (karbar.username.equals(user.username))
            {
                System.out.println("ka : " + karbar.username);
                System.out.println("ka1 : " + user.username);
                ischeck = true ;
            }

        }
        System.out.println("injast 2");
        if (ischeck) {
            String text = myclass.userOfClass.size() + " Student" ;
            holder.txt2.setText(text);
        }
        else {

            holder.txt2.setText(myclass.teacherOfClass.get(0).username);
            System.out.println("injast 3");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),ClassPage.class);
                i.putExtra("user" , user);
                i.putExtra("myclass" , myclass);
                System.out.println("llll ::: "+myclass.name);
                v.getContext().startActivity(i);
            }
        });

        holder.txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                boolean checkkardan = false;
                for (User karbar:myclass.teacherOfClass) {
                    if (karbar.username.equals(user.username))
                    {
                        System.out.println("sa : " + karbar.username);
                        System.out.println("sa1 : " + user.username);
                        checkkardan = true ;
                    }

                }
                if(checkkardan)
                {

                    PopupMenu pop  = new PopupMenu(mcontext,holder.txt1);
                    pop.inflate(R.menu.menu_for_recycleview_teacher);
                    pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId())
                            {

                                case R.id.edit_rec:

                                    Intent i = new Intent(v.getContext(),SettingTeacher.class);
                                    i.putExtra("user" , user);
                                    i.putExtra("myclass" , myclass);
                                    v.getContext().startActivity(i);


                                    break;


                            }
                            return false;
                        }
                    });
                    pop.show();
                }
                else
                {
                    PopupMenu pop  = new PopupMenu(mcontext,holder.txt1);
                    pop.inflate(R.menu.menu_for_recyleview);
                    pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId())
                            {



                                case R.id.delete_rec:

                                    // برا حذف

/*listItem.remove(position);
listItem2.remove(position);*/
                                    String temp1 = myclass.name;
                                    String temp2 = myclass.id;
                                    for (int i = 0; i < arrMyClasses.size(); i++) {
                                        System.out.println("arrclass11111 : " + arrMyClasses.get(i).name);
                                    }

                                    for (int i = 0; i < arrMyClasses.size() ; i++) {
                                        if(arrMyClasses.get(i).id.equals(temp2))
                                        {
                                            arrMyClasses.remove(i);
                                            System.out.println("delete fuck1");
                                            break;
                                        }
                                    }


                                    for (int i = 0; i <user.studentOfMyClasses.size() ; i++) {
                                        for (int j = 0; j <user.studentOfMyClasses.get(i).userOfClass.size() ; j++) {
                                            if(user.studentOfMyClasses.get(i).userOfClass.get(j).username.equals(user.username) && user.studentOfMyClasses.get(i).userOfClass.get(j).password.equals(user.password))
                                            {
                                                System.out.println("delete fuck 1.5");
                                                user.studentOfMyClasses.get(i).userOfClass.remove(j);
                                                break;
                                            }
                                        }

                                    }
                                    for (int i = 0; i <user.studentOfMyClasses.size() ; i++) {
                                        if(user.studentOfMyClasses.get(i).id.equals(temp2))
                                        {
                                            user.studentOfMyClasses.remove(i);
                                            System.out.println("delete fuck2");
                                            break;
                                        }
                                    }
                                    for (int i = 0; i < arrMyClasses.size(); i++) {
                                        System.out.println("arrclass22222 : " + arrMyClasses.get(i).name);
                                    }

                                    notifyDataSetChanged();
//--------------

                                    //-------------
                                    break;

                            }
                            return false;
                        }
                    });
                    pop.show();
                }


            }
        });
    }














    public int getItemCount() {
        return arrMyClasses.size();
    }
//------------

    static class  ViewHolderMain extends RecyclerView.ViewHolder {
        TextView txt;
        TextView txt2;

        ImageButton txt1;


        public ViewHolderMain(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_adapter);
            txt2 = itemView.findViewById(R.id.txt2_adapter);

            txt1 = itemView.findViewById(R.id.imageButton_rec);

        }
    }













}
