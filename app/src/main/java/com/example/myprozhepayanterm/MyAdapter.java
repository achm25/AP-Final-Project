package com.example.myprozhepayanterm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    static class  ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        TextView txt2;

        ImageButton txt1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_adapter);
            txt2 = itemView.findViewById(R.id.txt2_adapter);

            txt1 = itemView.findViewById(R.id.imageButton_rec);

        }
    }






    /*private List<RecyclerItem> listItem;
    private List<RecyclerItem> listItem2;*/
    List<myClass> arrMyClasses;
    private Context mcontext;
    User user;
    boolean ischeck =true;
    public MyAdapter(Context mcontext,User u) {
        this.mcontext = mcontext;
arrMyClasses = u.teacherOfMyClasses;
        user = u;

        List<myClass> arr2 = u.studentOfMyClasses;
    arrMyClasses.addAll(arr2);

        for (int i = 0; i <arrMyClasses.size() ; i++) {
            System.out.println("adapterArr : " + arrMyClasses.get(i).name);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);

        ViewHolder pvh = new ViewHolder(v);
        return  pvh;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        System.out.println("injast 4");
        final myClass myclass = arrMyClasses.get(position) ;
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

                                    SocketToPC_remove socketToPC_remove = new SocketToPC_remove();
                                    socketToPC_remove.execute();

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

    @Override
    public int getItemCount() {
      //  return listItem.size();

       return arrMyClasses.size();

    }


    private class SocketToPC_remove extends AsyncTask<Void,Void,String> {
        Socket s;
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;

        @Override
        protected String doInBackground(Void... input) {

            try {

                System.out.println("shod");
                s = new Socket("10.0.2.2",6800);
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                objectInputStream= new ObjectInputStream(s.getInputStream());
                objectOutputStream.writeObject("remove");
                objectOutputStream.flush();
                objectOutputStream.writeObject(user);
                objectOutputStream.flush();
                System.out.println("zzzzz");
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

            super.onPostExecute(s);



        }

    }
}
