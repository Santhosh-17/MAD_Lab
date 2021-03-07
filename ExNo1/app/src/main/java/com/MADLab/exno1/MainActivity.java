package com.MADLab.exno1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int textSize = 18;
    int ch = 1 , ch1 = 1;
    TextView label;
    Button fontClr,fontSize,font;
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = findViewById(R.id.label);
        fontSize = findViewById(R.id.fontSize);
        fontClr = findViewById(R.id.fontClr);
        font = findViewById(R.id.fontStyle);

        fontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textSize < 30){
                    textSize = textSize + 2;
                    label.setTextSize(textSize);
                }else{
                    textSize = 18;
                    label.setTextSize(textSize);
                }
            }
        });

        fontClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch == 7){
                    ch = 1;
                }
                switch (ch) {
                    case 1:
                        label.setTextColor(Color.RED);
                        break;
                    case 2:
                        label.setTextColor(Color.GREEN);
                        break;
                    case 3:
                        label.setTextColor(Color.BLUE);
                        break;
                    case 4:
                        label.setTextColor(Color.CYAN);
                        break;
                    case 5:
                        label.setTextColor(Color.YELLOW);
                        break;
                    case 6:
                        label.setTextColor(Color.MAGENTA);
                        break;
                }
                ch++;

            }
        });

        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch1==5){
                    ch1 = 0;
                }
                switch (ch1) {
                    case 0:
                        typeface = Typeface.createFromAsset(getAssets(), "font/sourcesanspro.ttf");
                        label.setTypeface(typeface);
                        ch1++;
                        break;
                    case 1:
                        typeface = Typeface.createFromAsset(getAssets(), "font/raleway.ttf");
                        label.setTypeface(typeface);
                        ch1++;
                        break;

                    case 2:
                        typeface = Typeface.createFromAsset(getAssets(), "font/pacifico.ttf");
                        label.setTypeface(typeface);
                        ch1++;
                        break;
                    case 3:
                        typeface = Typeface.createFromAsset(getAssets(), "font/greatvibes.ttf");
                        label.setTypeface(typeface);
                        ch1++;
                        break;
                    case 4:
                        typeface = Typeface.createFromAsset(getAssets(), "font/rajdhani.ttf");
                        label.setTypeface(typeface);
                        ch1++;
                        break;
                }


            }
        });

    }
}