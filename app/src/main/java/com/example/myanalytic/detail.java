package com.example.myanalytic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.UUID;

public class detail extends AppCompatActivity {

    long timestart;
    long timeend;
    long timeresult;

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        trackScreen("detail screen");
        timestart=System.currentTimeMillis();
    }
    @Override
    protected void onDestroy() {
        timeend = System.currentTimeMillis();
        timeEvent(timestart, timeend);
        super.onDestroy();
    }

    public void timeEvent(long tim1,long time2){
        timeresult=(tim1 - time2);
        Bundle bundletime= new Bundle();
        bundletime.putString("ueerid", UUID.randomUUID().toString());
        bundletime.putLong("timespend",timeresult);
        mFirebaseAnalytics.logEvent("time",bundletime);

    }


    void trackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "detail");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}