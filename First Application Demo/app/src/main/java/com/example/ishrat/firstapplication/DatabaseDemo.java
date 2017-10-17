package com.example.ishrat.firstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ishrat on 7/20/2016.
 */
public class DatabaseDemo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        final EditText name_editText;
        final EditText ph_editText;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_demo);

        final DatabaseHandler db = new DatabaseHandler(this);
        name_editText = (EditText) findViewById(R.id.name_db_edittext);
        ph_editText = (EditText) findViewById(R.id.ph_db_edittext);

        final Context context = this;
        Button db_submit_btn = (Button) findViewById(R.id.db_submit_btn);
        db_submit_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                String name = name_editText.getText().toString();
                String ph_num = ph_editText.getText().toString();

                try {
                    db.addContact(new contacts(name, ph_num));
                    Toast.makeText(context, "Successful in inserting Data", Toast.LENGTH_SHORT).show();
                }catch(Exception ex){
                    Toast.makeText(context, "Error in inserting Data", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        });

        Button show_db_btn=(Button) findViewById(R.id.show_db_btn);
        show_db_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                List<contacts> contacts = db.getAllContacts();
                for (contacts cn : contacts) {
                       String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                       Toast.makeText(context, log , Toast.LENGTH_SHORT).show();

       }

            }
        });



//
//        // Reading all contacts
//        Log.d("Reading: ", "Reading all contacts..");
//        List<contacts> contacts = db.getAllContacts();
//
//        for (contacts cn : contacts) {
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//            // Writing Contacts to log
//            Log.d("Name: ", log);
//        }

    }
}
