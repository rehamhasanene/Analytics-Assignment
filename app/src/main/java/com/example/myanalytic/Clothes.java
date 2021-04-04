package com.example.myanalytic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.UUID;

public class Clothes extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Button buttshirt  ;
    Button buttrouser  ;
    Button butHoodies  ;

    long timestart;
    long timeend;
    long timeresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        buttshirt = findViewById(R.id.butShirt);
        buttrouser = findViewById(R.id.butTrouser);
        butHoodies = findViewById(R.id.buthoodies);

        timestart=System.currentTimeMillis();


        buttshirt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detailclothes.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttshirt.getText().toString());

            }
        });
        buttrouser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detailclothes.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),buttrouser.getText().toString());

            }
        });
        butHoodies.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), detailclothes.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),butHoodies.getText().toString());

            }
        });
        trackScreen("Clothes screen");

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
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Clothes");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}