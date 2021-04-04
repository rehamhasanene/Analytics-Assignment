package com.example.myanalytic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Button btnfood ;
    Button butclothes  ;
    Button butelectronic  ;

    long timestart;
    long timeend;
    long timeresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the FirebaseAnalytics instance.

        btnfood = findViewById(R.id.butfood);
        butclothes = findViewById(R.id.butclothes);
        butelectronic = findViewById(R.id.butelectronic);
        timestart=System.currentTimeMillis();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btnfood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Food.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),btnfood.getText().toString());

            }
        });

        butclothes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Clothes.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),butclothes.getText().toString());

            }
        });

        butelectronic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Eelectronic.class);
                startActivity(intent);
                SelectCunter(UUID.randomUUID().toString(),butelectronic.getText().toString());

            }
        });



        trackScreen("main screen");
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
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "prodectitem");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }
    void trackScreen(String screenName){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}