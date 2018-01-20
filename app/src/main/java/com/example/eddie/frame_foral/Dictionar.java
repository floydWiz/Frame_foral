package com.example.eddie.frame_foral;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by kuldeep on 19/1/18.
 */

public class Dictionar extends Service {
    private WindowManager mWindowManager;
    public View mFloatingView;
   public TextView te;
    EditText tv;
    public FloatingActionButton fab;
    Button b;

    //making constructor
    public Dictionar()
    {

    }


    //overiding abstract method
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //@Override
    public void onCreate() {
        setTheme(R.style.AppTheme
        );
        super.onCreate();
        //Inflate the floating view layout we created

         mFloatingView = LayoutInflater.from(this).inflate(R.layout.dictionar_lay, null);

        te=(TextView) mFloatingView.findViewById(R.id.editText);
        tv=(EditText) mFloatingView.findViewById(R.id.textView);
        b=(Button)mFloatingView.findViewById(R.id.button);




        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
               350,500,
                WindowManager.LayoutParams.TYPE_PHONE,0| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

                ,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);



        //mFloatingView.findViewById(R.id.button1).setOnTouchListener(new View.OnTouchListener()
       /* mFloatingView.setOnTouchListener(new View.OnTouchListener(){
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;


                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);


                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });*/


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CallbackTask().execute(inflections());
            }
        });
    }


    public String inflections() {
        final String language = "en";

        final String word = tv.getText().toString();
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }



    //in android calling network requests on the main thread forbidden by default
    //create class to do async job
    private class CallbackTask extends AsyncTask<String, Integer, ArrayList<DictinoryData>> {
        DictinoryData d1;
        public MediaPlayer mediaPlayer = new MediaPlayer();

        @Override
        protected ArrayList<DictinoryData> doInBackground(String... params) {

            //TODO: replace with your own app id and app key
            final String app_id = "adc0285b";
            final String app_key = "8135a43940bb5877b79b8c127bc1c99c";
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("app_id", app_id);
                urlConnection.setRequestProperty("app_key", app_key);

                // read the output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                urlConnection.disconnect();
                /////json parse

                String def = "eror";
                ArrayList<DictinoryData> dictinoryData = new ArrayList<>();
                try {
                    JSONObject js = new JSONObject(stringBuilder.toString());
                    JSONArray results = js.getJSONArray("results");

                    JSONObject lentries = results.getJSONObject(0);
                    //add
                    String id = (String) lentries.get("id");
                    JSONArray la = lentries.getJSONArray("lexicalEntries");

                    JSONObject entries = la.getJSONObject(0);
                    //retriving url
                    JSONArray urls = entries.getJSONArray("pronunciations");

                    JSONObject get_url = urls.getJSONObject(0);
                    String audio_url = get_url.getString("audioFile");

                    String Deriv = "bhai ja";

                 /*  JSONArray derivate = entries.getJSONArray("derivatives");
                    JSONObject jobjDer= derivate.getJSONObject(0);
                    //add
                    String Deriv = jobjDer.getString("text");*/

                    JSONArray e = entries.getJSONArray("entries");

                    JSONObject senses = e.getJSONObject(0);
                    JSONArray s = senses.getJSONArray("senses");//s==j4
                    for (int i = 0; i < s.length(); i++) {
                        JSONObject d = s.getJSONObject(i);
                        JSONArray de = d.getJSONArray("definitions");

                        String mean = de.getString(0);

                        try {
                            JSONArray examp = d.getJSONArray("examples");
                            ArrayList<String> expary = new ArrayList<>();
                            for (int j = 0; j < examp.length(); j++) {
                                JSONObject text = examp.getJSONObject(j);
                                expary.add(text.getString("text"));

                            }
                            dictinoryData.add(new DictinoryData(id, Deriv, mean, expary, audio_url));
                        } catch (Exception e1) {
                            Log.d(
                                    "Main_activity", "no examples");

                            dictinoryData.add(new DictinoryData(id, Deriv, mean, audio_url));
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

////json parse


                return dictinoryData;

            } catch (Exception e) {
                e.printStackTrace();
                ArrayList<DictinoryData> ki = null;
                return ki;
            }
        }


        @Override
        protected void onPostExecute(ArrayList<DictinoryData> arrayList) {
            super.onPostExecute(arrayList);

            if (arrayList.size() != 0 || arrayList != null) {
                String s = "";
                ArrayList<String> exple = new ArrayList<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    s = s + "Defination" + "\n" + arrayList.get(i).getMean_wrd() + "\n\n";

                    exple = arrayList.get(i).getExamples();
                    if (exple != null) {
                        s = s + "Examples" + "\n";
                        for (int j = 0; j < exple.size(); j++) {
                            s = s + exple.get(j) + "\n\n";
                        }
                    }
                    s = s + "\n\n";

                }


                te.setText(s);

                d1 = arrayList.get(0);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(d1.getAudio_url());
                    mediaPlayer.prepare();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                fab = mFloatingView.findViewById(R.id.floatingActionButton);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // mediaPlayer.start();

                        mediaPlayer.start();

                    }
                });


            } else {
                te.setText("word not found");

            }

        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }


}
