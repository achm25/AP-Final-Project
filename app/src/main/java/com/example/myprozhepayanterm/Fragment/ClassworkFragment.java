package com.example.myprozhepayanterm.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.myprozhepayanterm.AboutUS;
import com.example.myprozhepayanterm.MyAdapter;
import com.example.myprozhepayanterm.R;
import com.example.myprozhepayanterm.RecyclerItem;
import com.example.myprozhepayanterm.Register;
import com.example.myprozhepayanterm.SettingTeacher;

import java.util.ArrayList;
import java.util.List;

public class ClassworkFragment extends Fragment {

    private RecyclerView recyclerView;
    MyAdapter adapter;
    List<RecyclerItem> listItems;

// باسه تاپیک ها با کانستراکتور می ریم

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.classwork_fragment, parent, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_classworkfragment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editclass(getActivity());
            }
        });

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();
        listItems.add(new RecyclerItem("salammm" , "asd"));
        listItems.add(new RecyclerItem("lllalammm" , "asd"));
        listItems.add(new RecyclerItem("ggggmm" , "asd"));
        listItems.add(new RecyclerItem("jjjlammm" , "asd"));
        listItems.add(new RecyclerItem("lllllammm" , "asd"));

        adapter = new MyAdapter(listItems,getContext());
        recyclerView.setAdapter(adapter);

        //View v = inflater.inflate(R.layout.streamf_fragment, parent, false);
        setHasOptionsMenu(true);

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_class_classpage_three, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.refresh_classepage_three)
        {

        }
        else if(id == R.id.aboutus_classepage_three)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.notif_classepage_three)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }else if(id == R.id.classes_classepage_three)
        {


            //Toast.makeText(getApplicationContext(),"google feedback",Toast.LENGTH_LONG);
        }
else if(id == R.id.setting_classepage_three)
        {
            // نمونه ای از استفاده از اینتنت در فراگمنت
            Intent intent = new Intent(getActivity(), SettingTeacher.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }






    private void editclass(Context context) {
        final CharSequence[] options = {"add homework","add model"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("add homework")) {


                } else if (options[item].equals("add model")) {


                }
            }
        });
        builder.show();
    }



}