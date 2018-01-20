package com.example.eddie.frame_foral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by kuldeep on 16/1/18.
 */

public class Overlay_activity extends AppCompatActivity {

    Button buttonStop;

  @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.transparentbuttonactivity);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("buttonid", 0);
        if(intValue == 0)
        {
            Log.e("Overlay_activity","problem in intent parse");

        }
    else {
            if (intValue == R.id.b1) {

                Log.d("Overlay_activity","bingo 1" );
                //startService(new Intent(Overlay_activity.this, Overlay.class));

            }

            if (intValue == R.id.b2) {

                Log.d("Overlay_activity","bingo 2" );
               // startService(new Intent(Overlay_activity.this, Overlay.class));


            }

            if (intValue == R.id.b3) {

                Log.d("Overlay_activity","bingo 3" );
                //startService(new Intent(Overlay_activity.this, Overlay.class));

                }
                if(intValue==R.id.b4)
                {
                    Log.d("Overlay_activity","bingo 4" );
                    startService(new Intent(Overlay_activity.this, Dictionar.class));

                }
                if(intValue==R.id.b5)
                {
                    Log.d("Overlay_activity","bingo 5" );
                    startService(new Intent(Overlay_activity.this, Calce.class));


                }
                if(intValue==R.id.b6)
                {
                    //Todo:handlecalce
                    Log.d("Overlay_activity","bingo 6" );

                    startService(new Intent(Overlay_activity.this, web.class));// buttonStart = (Button) findViewById(R.id.button1);



                }

        }
        buttonStop = (Button) findViewById(R.id.button);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopService(new Intent(Overlay_activity.this, web.class));
                stopService(new Intent(Overlay_activity.this, Calce.class));
                stopService(new Intent(Overlay_activity.this,Dictionar.class));
                finish();

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(Overlay_activity.this, web.class));
        stopService(new Intent(Overlay_activity.this, Calce.class));
        stopService(new Intent(Overlay_activity.this,Dictionar.class));
        finish();


    }


}