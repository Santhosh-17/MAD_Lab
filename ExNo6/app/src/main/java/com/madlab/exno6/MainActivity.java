package com.madlab.exno6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<=100;i=i+10){
                            final int value = i;

                            doSomeWork();
                            progressBar.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(value==100){
                                        progressBar.setProgress(value);
                                        textView.setText("100 % Completed!");
                                    }else{
                                        textView.setText(value+" % "+"Updating..");
                                        progressBar.setProgress(value);
                                    }


                                }
                            });

                        }
                    }
                };

                new Thread(runnable).start();
            }

            private void doSomeWork() {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}