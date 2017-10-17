package com.cse535.locationservice;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Ishrat on 11/13/2016.
 */

public class secondPage extends AppCompatActivity {

    Button callDonor, msgDonor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        callDonor = (Button) findViewById(R.id.callDonor);
        msgDonor = (Button) findViewById(R.id.msgDonor);

        final Context context = this;


        // add button listener for call
        callDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    // set the data, number has to be string
                    String phNum="6028027747";
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phNum));

                    //noinspection MissingPermission
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(),"Calling...",Toast.LENGTH_LONG).show();
                        startActivity(callIntent);
                        return;
                    }

                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"Your call has failed...",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        msgDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    // set the data, number has to be string


                    //noinspection MissingPermission
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(),"Sending...",Toast.LENGTH_LONG).show();

                        Uri uri = Uri.parse("smsto:6028027747");
                        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                        it.putExtra("sms_body", "The SMS text");
                        startActivity(it);
                        return;
                    }

                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"Your msg is not sent...",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }


}
