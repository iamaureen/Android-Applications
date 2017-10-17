package com.cse535.locationservice;

//reference: https://www.youtube.com/watch?annotation_id=annotation_2922802295&feature=iv&src_vid=QNb_3QKSmMk&v=lvcGh2ZgHeA
//reference: https://developer.android.com/training/permissions/requesting.html
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_start, btn_stop, nxtBtn;
    TextView longitude;
    TextView latitude;
    TextView tv;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nxtBtn = (Button) findViewById(R.id.nextBtn);
        final Context context = this;

        btn_start = (Button) findViewById(R.id.button);
        btn_stop = (Button) findViewById(R.id.button2);
        longitude = (TextView) findViewById(R.id.lnTV);
        latitude = (TextView) findViewById(R.id.ltTV);
        tv = (TextView) findViewById(R.id.loc);
        //if we dont need permissions, we enable the two buttons
        //if we need permission checking, then we must handle users response. we do that onRequestPermissionsResult method
        if(!runtimePermissions())
            enable_buttons();

        nxtBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, secondPage.class);
                startActivity(intent);

            }

        });

    }

    //broadcast receiver is registered in onResume method
    @Override
    protected void onResume() {
        super.onResume();
        //if the broadcast receiver doesnt exist, we create one
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                //when broadcast will receive content it will be captured on onReceive method
                @Override
                public void onReceive(Context context, Intent intent) {

                    tv.append("\n" +intent.getExtras().get("coordinates"));
                    //clear previous values
                    longitude.setText(" ");
                    latitude.setText(" ");

                    longitude.setText(intent.getExtras().get("longitude").toString());
                    latitude.setText(intent.getExtras().get("latitude").toString());

                }
            };
        }

        registerReceiver(broadcastReceiver,new IntentFilter("locationUpdate"));
    }

    //unregister the receiver
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if receiver exits, destroy it
        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }



    private void enable_buttons() {

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),locationService.class);
                Toast toast = Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_LONG);
                toast.show();
                startService(i);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),locationService.class);
                Toast toast = Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_LONG);
                toast.show();
                stopService(i);

            }
        });

    }

    //check user permissions. if sdk is under 23, we dont need this check
    private boolean runtimePermissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            //if we need permission, returns true
            return true;
        }
        //if we dont need permission, returns false
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            //if permissionGranted enable buttons, else ask for permission again
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }else {
                runtimePermissions();
            }
        }
    }
}
