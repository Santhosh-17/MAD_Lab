package com.madlab.exno4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.madlab.exno4.Model.Contacts;
import com.madlab.exno4.data.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    Context mainContext;


    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button SaveButton,CancelButton;
    private EditText addName,addNo;
    private DatabaseHandler databaseHandler;

    Button add,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Contact Manager");

        databaseHandler = new DatabaseHandler(this);

        mainContext  = getApplicationContext();
        add = findViewById(R.id.insertButton);
        view = findViewById(R.id.viewButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopUpDialogue(v);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

    }

    private void createPopUpDialogue(final View v1) {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup,null);

        addName = view.findViewById(R.id.addName);
        addNo = view.findViewById(R.id.addNo);

        SaveButton = view.findViewById(R.id.saveButton);
        CancelButton = view.findViewById(R.id.cancelb);

        builder.setView(view);
        dialog = builder.create(); //creating dialogue object
        dialog.show();

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!addName.getText().toString().isEmpty() && !addNo.getText().toString().isEmpty() ){

                    saveContact();
                    dialog.dismiss();

                }else{
                    Snackbar.make(v, "Empty Fields are not allowed!", Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void saveContact() {

        Contacts contacts = new Contacts();

        String name = addName.getText().toString().trim();
        String number = addNo.getText().toString().trim();

        contacts.setName(name);
        contacts.setPhoneNumber(number);

        String m = databaseHandler.addContact(contacts);
        if(m.isEmpty()){
            Toast.makeText(getApplicationContext(),"Contact Added",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();
        }

    }


}