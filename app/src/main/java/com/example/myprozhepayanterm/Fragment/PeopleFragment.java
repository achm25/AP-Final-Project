package com.example.myprozhepayanterm.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myprozhepayanterm.R;
import com.example.myprozhepayanterm.SettingTeacher;

public class PeopleFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.people_fragment, parent, false);
        setHasOptionsMenu(true);
        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_for_classpage_two, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if(id == R.id.refresh_classepage_two)
        {
            // نمونه ای از استفاده از اینتنت در فراگمنت
            Intent intent = new Intent(getActivity(), SettingTeacher.class);
            startActivity(intent);
        }
        else if(id == R.id.aboutus_classepage_two)
        {



        }else if(id == R.id.notif_classepage_two)
        {



        }else if(id == R.id.classes_classepage_two)
        {



        }


        return super.onOptionsItemSelected(item);
    }
}
