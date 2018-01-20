package com.example.eddie.frame_foral;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

/**
 * Created by eddie on 15/1/18.
 */

public class web extends Service {
    private WindowManager mWindowManager;
    private View mFloatingView;
    RelativeLayout rl;
    WebView wb;

    public web() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Inflate the floating view layout we created
               mFloatingView = LayoutInflater.from(this).inflate(R.layout.webx, null);

        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                300,400,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);



         wb = new WebView(this);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setJavaScriptEnabled(true);

        wb.setVerticalScrollBarEnabled(false);

        wb.setHorizontalScrollBarEnabled(false);

        wb.loadUrl("https://www.google.co.in/");
        WebView.LayoutParams wvp;
        wvp = new WebView.LayoutParams(200,350,10,30);

        wb.setLayoutParams(wvp);
        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;


        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(wb, params);

        //mFloatingView.findViewById(R.id.button1).setOnTouchListener(new View.OnTouchListener()

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wb != null) mWindowManager.removeView(wb);

        //if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }

}
      /*  WebView wb = (WebView)findViewById(R.id.webid);
                wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setJavaScriptEnabled(true);

                wb.setVerticalScrollBarEnabled(false);

                wb.setHorizontalScrollBarEnabled(false);

                wb.loadUrl("https://www.google.co.in/");

    }
}
*/