package com.madlab.exno4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.madlab.exno4.Model.Contacts;
import com.madlab.exno4.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private AlertDialog dialog;
    String tempName = "";
    private Button update,Cancel,del,back;
    EditText viewName,viewNo;
    ListView listView;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tempName ="";
        setTitle("Contacts");
        db = new DatabaseHandler(this);
        listView  = (ListView) findViewById(R.id.listView);


        List<String> contactArray = new ArrayList<String>();

        List<Contacts> contactsList = db.getAllContacta();
        for(Contacts c : contactsList){
            contactArray.add(c.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactArray);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                tempName = selectedItem;
                Contacts c = db.getContact(selectedItem);

                createPopUpDialogue(view,c);

            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    private void createPopUpDialogue(final View v1, Contacts c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup2,null);

        viewName = view.findViewById(R.id.viewName);
        viewNo = view.findViewById(R.id.viewNo);

        update = view.findViewById(R.id.update);
        del = view.findViewById(R.id.delete);
        Cancel = view.findViewById(R.id.cancel);

        builder.setView(view);
        dialog = builder.create(); //creating dialogue object
        dialog.show();

        showData(c);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!viewName.getText().toString().isEmpty() && !viewNo.getText().toString().isEmpty() ){

                    saveContact();
                    dialog.dismiss();
                    Snackbar.make(v1, "Contact Saved", Snackbar.LENGTH_SHORT)
                            .show();

                }else{
                    Snackbar.make(v, "Empty Fields are not allowed!", Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = viewName.getText().toString().trim();
              //  String number = viewNo.getText().toString().trim();

                db.deleteContact(name);
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Contact Deleted",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    private void showData(Contacts contacts) {
        viewName.setText(contacts.getName());
        viewNo.setText(contacts.getPhoneNumber());
    }

    private void saveContact() {

        Contacts contacts = new Contacts();

        String name = viewName.getText().toString().trim();
        String number = viewNo.getText().toString().trim();

        contacts.setName(name);
        contacts.setPhoneNumber(number);

        db.update(contacts,tempName);

    }


}