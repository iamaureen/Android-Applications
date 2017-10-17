package com.example.ishrat.firstapplication;


import android.app.*;
import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    final String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText name_editText;
        //get the input from the edit text
        name_editText = (EditText) findViewById(R.id.name_editText);


        //this button generates a toast message
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast toast=Toast.makeText(getApplicationContext(),"Hello Maureen, You clicked a button",Toast.LENGTH_SHORT);
                toast.show();
            }

        });

        //this button goes to the next page, passes value from the input
        Button next_pg_btn = (Button) findViewById(R.id.next_pg_btn);
        final Context context = this;
        next_pg_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, SecondPage.class);
                //passes value to the next screen
                intent.putExtra("name",name_editText.getText().toString());
                startActivity(intent);


            }
        });

        Button notification_btn = (Button) findViewById(R.id.notification_btn);
        notification_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //create notification builder
                NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.notification_logo)
                        .setContentTitle("New Notification")
                        .setContentText("You recieved a new notification");

                //define notification's action, create a java file and xml for notification
                Intent intent = new Intent(MainActivity.this, NotificationView.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set the notification's click behavior, what to do when the notification is clicked
                mBuilder.setContentIntent(pendingIntent);

                //issue the notification
                int notification_id = 001; //sets an id for the notification
                // Gets an instance of the NotificationManager service
                NotificationManager notification_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //removes the notification from the notification bar once pressed
                mBuilder.setAutoCancel(true);
                // Builds the notification and issues it.
                notification_manager.notify(notification_id,mBuilder.build());

            }
        });


        //this button checks for internet connectivity when pressed
        //but if we want to check for connectivity in the background we will have to use BroadcastReceiver
        //learn detail ab BroadcastReceiver --> http://www.grokkingandroid.com/android-tutorial-broadcastreceiver/
        Button connection_check = (Button) findViewById(R.id.connection_btn);
        connection_check.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean connected = false;
                ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) { // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // connected to the mobile provider's data plan
                        Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Not CONNECTED", Toast.LENGTH_SHORT).show();
                }



            }
        });

        Button database_btn = (Button) findViewById(R.id.db_demo);
        database_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, DatabaseDemo.class);
                startActivity(intent);


            }
        });


        Button listview_btn = (Button) findViewById(R.id.listview_btn);
        listview_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, ListviewDemo.class);
                startActivity(intent);



            }
        });


    }




}
