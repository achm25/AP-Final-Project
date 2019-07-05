package com.example.myprozhepayanterm;

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


//=========================================================================
        View v = inflater.inflate(R.layout.classwork_fragment, parent, false) ;


        return v;
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