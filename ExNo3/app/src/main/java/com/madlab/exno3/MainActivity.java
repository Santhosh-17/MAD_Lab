package com.madlab.exno3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Bitmap bitmap =  Bitmap.createBitmap(720,1280,Bitmap.Config.ARGB_8888);  //Bitmap created  A - Alpha - 8 , R - Red - 8 , G - Green - 8,B - Blue - 8

        imageView.setImageBitmap(bitmap); //Setting the Bitmap as ImageView background

        Canvas canvas = new Canvas(bitmap);


        Paint paint = new Paint(); //Creating the Paint Object and set its color and TextSize
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);


        //To draw a Circle
        canvas.drawText("Circle", 120, 150, paint);
        canvas.drawCircle(200, 350, 150, paint);


        //To draw a Rectangle
        canvas.drawText("Rectangle", 420, 150, paint);
        canvas.drawRect(400, 200, 650, 700, paint);


        //To draw a Square
        canvas.drawText("Square", 120, 800, paint);
        canvas.drawRect(50, 850, 350, 1150, paint);

        //To draw a Line
        canvas.drawText("Line", 480, 800, paint);
        canvas.drawLine(520, 850, 520, 1150, paint);

    }
}