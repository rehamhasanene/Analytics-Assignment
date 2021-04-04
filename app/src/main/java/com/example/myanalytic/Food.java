package com.example.myanalytic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.UUID;

public class Food extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Button buttburger  ;
    Button buttshawarma  ;
    Button buttsushi  ;

    long timestart;
    long timeend;
    long timeresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        buttburger = findViewById(R.id.butburger);
        buttshawarma = findViewById(R.id.butshawarma);
        buttsushi = findViewById(R.id.butsuhi);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        timestart=System.currentTimeMillis();

        buttburger.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detail.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttburger.getText().toString());

            }
        });
        buttshawarma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detail.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttshawarma.getText().toString());

            }
        });

        buttsushi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detail.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttsushi.getText().toString());

            }
        });
        trackScreen("Food screen");
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
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Food");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}