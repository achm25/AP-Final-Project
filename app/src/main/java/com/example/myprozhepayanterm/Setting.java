package com.example.myprozhepayanterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    TextView boilingpointK ;
    private Switch mySwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);



        mySwitch = (Switch) findViewById(R.id.switch_sync);
//برا سوییچ ها وقتی روش زدیم چه اتفاقی بیافته ایز چکد چک می کنه می بینه روی حالت ترو هست یا فالس
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),"false",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
