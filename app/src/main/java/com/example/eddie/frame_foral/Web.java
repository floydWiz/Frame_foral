package com.example.eddie.frame_foral;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by kuldeep on 16/1/18.
 */

public class Web extends Service {
    private WindowManager wm;
    private LinearLayout ll;
    private Button stop;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void onCreate() {
        super.onCreate();


        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        ll = new LinearLayout(this);
        stop = new Button(this);

        ViewGroup.LayoutParams btnparametrs =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        stop.setText("close");
        stop.setLayoutParams(btnparametrs);

        ll.addView(stop);

        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setBackgroundColor(Color.argb(66,255,0,0));
        ll.setLayoutParams(llparams);

        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(400, 400, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

        parameters.x=0;
        parameters.y=0;

        parameters.gravity= Gravity.CENTER|Gravity.CENTER;

        wm.addView(ll,parameters);

        ll.setOnTouchListener(new View.OnTouchListener() {

            private   WindowManager.LayoutParams updateparam = parameters;

            int x,y;
            float touchx,touchy;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x=updateparam.x;
                        y=updateparam.y;

                        touchx=event.getRawX();
                        touchy=event.getRawY();

                    case MotionEvent.ACTION_MOVE:
                        updateparam.x=(int)(x+(event.getRawX()-touchx));

                        updateparam.y=(int)(y+(event.getRawY()-touchy));

                        wm.updateViewLayout(ll,updateparam);
                        break;

                    case MotionEvent.FLAG_WINDOW_IS_OBSCURED:


                    default:
                        break;

                }

                if(MotionEvent.FLAG_WINDOW_IS_OBSCURED!=1)
                {
                    wm.removeView(ll);
                    stopSelf();
                }
                return  false;

            }


        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(ll);
                stopSelf();
            }
        });



    }

    @Override
    public void onDestroy() {
        ll.removeAllViews();
        wm.removeView(ll);
        super.onDestroy();
    }
}
