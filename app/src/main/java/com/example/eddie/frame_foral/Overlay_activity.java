package com.example.eddie.frame_foral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by kuldeep on 16/1/18.
 */

public class Overlay_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transparentbuttonactivity);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("buttonid", 0);
        if(intValue == 0)
        {
            Log.e("Overlay_activity","problen in intent parse");

        }
    else {
            if (intValue == R.id.b1) {
                // Do work related to button 1
            }

            if (intValue == R.id.b2) {
                // Do work related to button 2
            }

            if (intValue == R.id.b3) {
                // Do work related to button 3
                }
                if(intValue==R.id.b4)
                {
                    //todo:handle
                }
                if(intValue==R.id.b5)
                {
                    //Todo:handlr

                }
                if(intValue==R.id.b6)
                {
                    //Todo:handlecalce
                    Log.d("Overlay_activity","bingo 6" +
                            "");
                    Intent i6 =new Intent(this,Web.class);
                    startService(i6);
                }

        }
    }

    @Override
    protected void onPause() {

        super.onPause();

    }


}