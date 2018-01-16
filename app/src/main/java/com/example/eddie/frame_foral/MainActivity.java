package com.example.eddie.frame_foral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
ImageButton b1,b2,b3,b4,b5,b6;
int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frames);

        final ArrayList<ImageButton> buttons=new ArrayList<ImageButton>();


        buttons.add(b1=(ImageButton)findViewById(R.id.b1));
        buttons.add(b2=(ImageButton)findViewById(R.id.b2));
        buttons.add(b3=(ImageButton)findViewById(R.id.b3));
        buttons.add(b4=(ImageButton)findViewById(R.id.b4));
        buttons.add(b5=(ImageButton)findViewById(R.id.b5));
        buttons.add(b6=(ImageButton)findViewById(R.id.b6));


            buttons.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(MainActivity.this,Overlay_activity.class);

                    i.putExtra("buttonid",buttons.get(0).getId());
                    MainActivity.this.startActivity(i);
                }
            });

            buttons.get(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 =new Intent(MainActivity.this,Overlay_activity.class);

                    i1.putExtra("buttonid",buttons.get(1).getId());
                    MainActivity.this.startActivity(i1);
                }
            });
            buttons.get(2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 =new Intent(MainActivity.this,Overlay_activity.class);

                    i2.putExtra("buttonid",buttons.get(2).getId());
                    MainActivity.this.startActivity(i2);
                }
            });

            buttons.get(3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i3 =new Intent(MainActivity.this,Overlay_activity.class);

                    i3.putExtra("buttonid",buttons.get(3).getId());
                    MainActivity.this.startActivity(i3);
                }
            });

            buttons.get(4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i4 =new Intent(MainActivity.this,Overlay_activity.class);

                    i4.putExtra("buttonid",buttons.get(4).getId());
                    MainActivity.this.startActivity(i4);
                }
            });

            buttons.get(5).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i5 =new Intent(MainActivity.this,Overlay_activity.class);

                    i5.putExtra("buttonid",buttons.get(5).getId());
                    MainActivity.this.startActivity(i5);
                }
            });





    }
}
