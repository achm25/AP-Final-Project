package com.example.myprozhepayanterm;

import android.content.Context;
import android.content.Intent;
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

public class MyHomeworkAdapter extends RecyclerView.Adapter<MyHomeworkAdapter.ViewHolderHomework>{

    List<Homework> arrMyHomework;
    private Context mcontext;
    User user;
    myClass myclass;
    boolean ischeck =true;



    public MyHomeworkAdapter(Context mcontext,myClass m) {
        this.mcontext = mcontext;
        myclass = m;
        arrMyHomework = m.teachershomework;
        System.out.println("111111");
        System.out.println("sizam : " + arrMyHomework.size());
        if (m.studentshomework != null && m.studentshomework.size() > 0) {
            ArrayList arr2 = m.studentshomework;
            System.out.println("22222222");

        }
    }


    @NonNull
    @Override
    public ViewHolderHomework onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_homeworkitem, parent, false);
        ViewHolderHomework pvh = new ViewHolderHomework(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderHomework holder, int position) {
        System.out.println("injam1");
        final Homework myHomework = arrMyHomework.get(position) ;
        holder.txt.setText(myHomework.name);
        holder.txt2.setText(myHomework.date);
        ischeck = false ;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(),StudentsWorkForTeacher.class);
                i.putExtra("user" , user);
                i.putExtra("myclass" , myclass);
                i.putExtra("homework" , myHomework);
                v.getContext().startActivity(i);
            }
        });

        holder.txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkkardan = false;
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

                                    // برا حذف


                                    break;


                            }
                            return false;
                        }
                    });
                    pop.show();
                }




            }
        });



















        /////////////
    }

    @Override
    public int getItemCount() {
        return arrMyHomework.size();
    }

    static class  ViewHolderHomework extends RecyclerView.ViewHolder {
        TextView txt;
        TextView txt2;

        ImageButton txt1;
        public ViewHolderHomework(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_homeworkadapter);
            txt2 = itemView.findViewById(R.id.txt2_homeworkadapter);

            txt1 = itemView.findViewById(R.id.imageButton_homeworkrec);

        }
    }



       /* System.out.println("size : " + arrMyHomework.size());
        String strTemp;
        ArrayList<String> strarr1 = new ArrayList<>();
        ArrayList<String> strarr2 = new ArrayList<>();
        ArrayList<String> strarr3 = new ArrayList<>();
        arrMyHomework.remove(0);
        for (int i = 0; i <arrMyHomework.size() ; i++) {
            final Homework tempHomework = arrMyHomework.get(i);
            System.out.println(tempHomework.name);
            System.out.println(tempHomework.date);
            final  String ss = tempHomework.date;
            System.out.println(ss);
            String[] parts = tempHomework.date.split("-");
            strarr1.add(parts[0]);
            strarr2.add(parts[1]);
            strarr3.add(parts[2]);
            System.out.println(parts[0]);
            System.out.println(parts[1]);
            System.out.println(parts[2]);

        }


        for (int i = 1; i <arrMyHomework.size() ; i++) {
            System.out.println("arr3 ::: "+arrMyHomework.get(i).date);
        }




        for (int i = 0; i < arrMyHomework.size()-1; i++)
        {
            for (int j = 0; j < arrMyHomework.size()-1-i; j++) {
                int adad1 = Integer.parseInt(strarr3.get(j));
                int adad2 = Integer.parseInt(strarr3.get(j+1));
                System.out.println(adad1+ "  i: "+ i + "   " + adad2);
                if (adad1 > adad2)
                {
Homework temp = arrMyHomework.get(j);
arrMyHomework.set(j,arrMyHomework.get(j+1));
arrMyHomework.set(j+1,temp);
                    System.out.println("ok" + adad1 + adad2);
                }

            }

        }

            // Last i elements are already in place

        for (int i = 1; i <arrMyHomework.size() ; i++) {
            System.out.println("arr3end ::: "+arrMyHomework.get(i).date);
        }



    }




*/

    }


