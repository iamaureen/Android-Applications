package cse535.com.assignment1;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Ishrat on 8/30/2016.
 */

public class MainActivity extends AppCompatActivity{

    //variable declaration
    GraphView gv;
    LinearLayout root;
    Handler mHandler = new Handler();

    float[] values = new float[10];
    String title="Assignment 1 - Moving Graph Demo";
    String[] verlabels = new String[]{"70", "60", "50", "40", "30", "20","10","0"};
    String[] horlabels = new String[]{"0", "10", "20", "30", "40", "50", "60","70"};

    EditText pid, age, name;
    RadioGroup gender;
    RadioButton g;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root=(LinearLayout) findViewById(R.id.graphview_layout);
        gv=new GraphView(this, values, title, horlabels, verlabels, GraphView.LINE);
        Button run_btn = (Button) findViewById(R.id.run_button);
        Button stop_btn = (Button) findViewById(R.id.stop_button);
        Button view_data = (Button) findViewById(R.id.data_view_btn);

        pid = (EditText) findViewById(R.id.pid_editText);
        age = (EditText) findViewById(R.id.age_editText);
        name = (EditText) findViewById(R.id.pname_editText);
        gender = (RadioGroup) findViewById(R.id.radioGroup);



        root.addView(gv);
        mHandler.post(moveChart);


        run_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "Run Pressed", Toast.LENGTH_SHORT).show();
                gv.run=true;
            }
        });

        stop_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "Stop Pressed", Toast.LENGTH_SHORT).show();
                gv.run=false;
            }
        });

        view_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected = gender.getCheckedRadioButtonId();
                g=(RadioButton) findViewById(selected);
                Toast toast=Toast.makeText(getApplicationContext(),"You entered: ID: "+pid.getText()+", Age: "+age.getText()+", Name: "+
                        name.getText()+", Gender: "+g.getText(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    Runnable moveChart = new Runnable() {
        public void run() {
            for (int i = 0; i < 10; i++) {
                values[i] = (float) 1 + (int) (Math.random() * 7);
            }

            gv.setValues(values);
            root.removeView(gv);
            root.addView(gv);
            mHandler.postDelayed(this, 1000);

        }
    };
}
