package com.example.myprozhepayanterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myprozhepayanterm.AboutUS;
import com.example.myprozhepayanterm.MyAdapter;
import com.example.myprozhepayanterm.R;
import com.example.myprozhepayanterm.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

public class StreamFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.streamf_fragment, parent, false);
        setHasOptionsMenu(true);
        return v;
    }



}














/*





 */