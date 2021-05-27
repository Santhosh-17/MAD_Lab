package com.madlab.exno11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextView toAddressInput,bodyInput,subjectInput;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         toAddressInput = findViewById(R.id.toAddress);
         bodyInput = findViewById(R.id.body);
         subjectInput = findViewById(R.id.subject);
         sendButton = findViewById(R.id.sendButton);

         sendButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String toAddress = toAddressInput.getText().toString();
                 String subject = subjectInput.getText().toString();
                 String messageBody = bodyInput.getText().toString();

                 sendEmail(toAddress, subject, messageBody);
             }
         });

    }

    private void sendEmail(String toAddress, String subject, String messageBody) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ toAddress });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, messageBody);

        emailIntent.setType("message/rfc822");

        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.addFlags(Intent.FLAG_FROM_BACKGROUND);

        try {
            startActivity(emailIntent);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "No email application detected", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }
}