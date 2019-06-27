package com.example.myprozhepayanterm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Intro_slider extends AppCompatActivity {

    ViewPager vp;
    Button btn_signin,btn_register;
    LinearLayout myLinear;


    int[] layout_id = {R.layout.intro1,R.layout.intro3,R.layout.intro2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        vp = findViewById(R.id.view_pager);
        btn_register = findViewById(R.id.btn_register);
        btn_signin = findViewById(R.id.btn_signin);
        myLinear = findViewById(R.id.layoutDots);
        vp.setAdapter(new SlidePageAdapter());

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        }); btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignIn.class);
                startActivity(i);
            }
        });
    }


    public class SlidePageAdapter extends PagerAdapter
    {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(Intro_slider.this).inflate(layout_id[position],container,false);
            container.addView(view);
            return view;
        }


        //tedad slide ma ro mi khad
        @Override
        public int getCount() {
            return layout_id.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }
}
