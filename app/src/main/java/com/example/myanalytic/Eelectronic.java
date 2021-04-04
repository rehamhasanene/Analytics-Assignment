package com.example.myanalytic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.UUID;

public class Eelectronic extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Button buttlaptop  ;
    Button buttiphone  ;
    Button buttsmart ;

    long timestart;
    long timeend;
    long timeresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eelectronic);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        buttlaptop = findViewById(R.id.butlaptop);
        buttiphone = findViewById(R.id.butiphone);
        buttsmart = findViewById(R.id.butSamart);

        timestart=System.currentTimeMillis();

        buttlaptop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detailelec.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttlaptop.getText().toString());

            }
        });
        buttiphone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detailelec.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttiphone.getText().toString());

            }
        });
        buttsmart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detailelec.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttsmart.getText().toString());

            }
        });
        trackScreen("Eelectronic screen");


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
    public void  SelectCunter (String id ,String name){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "prodect item");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }
    void trackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Eelectronic");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}