package com.gilvanstudios.gauntletodyssey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends AppCompatActivity {

    private InterstitialAd interstitial;

    private Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startGame = (Button)

                findViewById(R.id.play_game);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(SplashActivity.this, com.gilvanstudios.gauntletodyssey.MainActivity.class);
                startActivity(mainIntent);
            }
        });

        //Main AD
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-4957836647276069/7935259485");

        //Create request
        AdRequest adRequest = new AdRequest.Builder().build();

        //Loading start
        interstitial.loadAd(adRequest);

        //Once loaded, display ad:
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded(){
                displayInterstitial();
            }
        });

    }

    public void displayInterstitial(){

        if(interstitial.isLoaded()){
            interstitial.show();
        }

    }
}
