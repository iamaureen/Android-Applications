package com.example.ishrat.firstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;


/**
 * Created by Ishrat on 7/16/2016.
 */
public class SecondPage extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        //get the data from previous screen
        Intent intent = getIntent();
        if(intent!=null){
            String name = intent.getStringExtra("name");
            TextView textView = (TextView) findViewById(R.id.value_pass);
            textView.setText(name);
        }

        Button back_btn = (Button) findViewById(R.id.back_btn);
        final Context context = this;
        back_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);


            }
        });
    }



}
